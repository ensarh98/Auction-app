package com.auctionapp.web.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * @author Ensar Horozović
 */
public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

	private static final long serialVersionUID = 1L;

	public CustomWebAuthenticationDetails(HttpServletRequest request) {

		super(request);
	}
}
