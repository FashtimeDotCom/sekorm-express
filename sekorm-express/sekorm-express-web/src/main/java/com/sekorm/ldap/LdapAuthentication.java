package com.sekorm.ldap;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sekorm.entity.LdapVO;

/**
 * AD域校验登录
 * LDAP(目录访问协议)
 * @author noah_yang
 *
 */
@Component
public class LdapAuthentication {

	private Logger logger = LoggerFactory.getLogger(LdapAuthentication.class);

	private String URL = "ldap://172.16.1.1/";
	private String BASEDN = "OU=世强电讯,OU=世强集团,DC=sekorm,DC=com";
	private String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private LdapContext ctx = null;
	private Hashtable<String, String> env = null;
	private Control[] connCtls = null;

	private boolean authenricate(String email, String password) {
		env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
		env.put(Context.PROVIDER_URL, URL + BASEDN);// LDAP server
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL, email);
		env.put(Context.SECURITY_CREDENTIALS, password);
		// 此处若不指定用户名和密码,则自动转换为匿名登录
		try {
			ctx = new InitialLdapContext(env, connCtls);
			return true;
		} catch (javax.naming.AuthenticationException e) {
			logger.error("Authentication faild: " + e);
			return false;
		} catch (NamingException e) {
			logger.error("Something wrong while authenticating: "
					+ e.toString());
			return false;
		}
	}

	public LdapVO getLdapVO(String email, String password) {
		boolean authentication = authenricate(email.toUpperCase(), password);
		LdapVO entity = null;
		if (authentication) {
			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			try {
				NamingEnumeration<SearchResult> en = ctx.search("", "mail="
						+ email, constraints);
				if (en == null) {
					logger.error("Have no NamingEnumeration.");
				}
				if (!en.hasMoreElements()) {
					logger.error("Have no element.");
				}
				while (en != null && en.hasMoreElements()) {
					// maybe more than one element
					Object obj = en.nextElement();
					if (obj instanceof SearchResult) {
						entity = new LdapVO();
						SearchResult si = (SearchResult) obj;
						Attributes attrs = si.getAttributes();
						entity.setCn(getAttribute(attrs, "cn"));
						entity.setDisplayName(getAttribute(attrs, "displayName"));
						entity.setMobile(getAttribute(attrs, "mobile"));
						entity.setName(getAttribute(attrs, "name"));
						entity.setsAMAccountName(getAttribute(attrs,
								"sAMAccountName"));
						entity.setTelephoneNumber(getAttribute(attrs,
								"telephoneNumber"));
						entity.setTitle(getAttribute(attrs, "title"));
						entity.setUserPrincipalName(getAttribute(attrs,
								"userPrincipalName"));
						entity.setWhenChanged(getAttribute(attrs, "whenChanged"));
						entity.setWhenCreated(getAttribute(attrs, "whenCreated"));
					} else {
						System.out.println(obj);
					}
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}

	public boolean isLogin(String email, String password) {
		boolean authentication = authenricate(email.toUpperCase(), password);
		return authentication;
	}

	private String getAttribute(Attributes attrs, String name) {
		Attribute attribute = attrs.get(name);
		if (attribute != null) {
			try {
				Object obj = attribute.get();
				return obj.toString();
			} catch (NamingException e) {
				System.out.println(e.toString());
			}
		}
		return null;
	}

}
