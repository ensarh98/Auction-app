package com.auctionapp.auth;

import com.auctionapp.auth.spring.UserDetailsImpl;
import java.util.Locale;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * A thin wrapper around Spring Security's context to avoid direct dependency on
 * it in the rest of the application.
 *
 */
@Component
public class AuthenticationInfo {

	public Authentication getAuthentication() {

		return SecurityContextHolder.getContext().getAuthentication();
	}

	public String getUsername() {

		if (getAuthentication() == null) {
			return "ANONYMOUS";
		}

		return getAuthentication().getName().toUpperCase(Locale.ENGLISH);
	}

	public Boolean isAuthenticated() {

		return getAuthentication().isAuthenticated();
	}

	public Integer getUserId() {

		return ((UserDetailsImpl) getAuthentication().getPrincipal()).getUserId();
	}

}
