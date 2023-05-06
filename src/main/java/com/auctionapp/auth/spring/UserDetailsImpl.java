package com.auctionapp.auth.spring;

import com.auctionapp.db.model.UserRecord;
import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Implements UserDetails interface provided by the Spring Security. <br>
 * This object is stored into user session and it should contain only minimal
 * set of data necessary for authentication/authorization(to preserve server
 * memory).
 *
 */
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private boolean enabled;
	private Date lockedUntil;
	private Integer userId;
	private int failedLoginCount;
	private Collection<GrantedAuthority> authorities;

	public UserDetailsImpl(UserRecord user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.userId = user.getId();
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return lockedUntil == null || lockedUntil.before(new Date());
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getFailedLoginCount() {
		return failedLoginCount;
	}

}
