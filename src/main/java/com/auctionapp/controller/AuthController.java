package com.auctionapp.controller;

import com.auctionapp.auth.AuthService;
import com.auctionapp.user.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication API specification for Swagger documentation. Implemented by
 * Spring Security.
 */
@RestController
@RequestMapping("/")
public class AuthController extends BaseController {

	@Autowired
	private AuthService authService;

	@ApiResponses({ @ApiResponse(code = 401, message = "Unauthorized") })
	@PostMapping("/login")
	public void login(@RequestParam String username, @RequestParam String password) {
		throw new IllegalStateException("Add Spring Security to handle authentication");
	}

	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return authService.register(user);
	}

	@PostMapping("/logout")
	public void logout() {
		throw new IllegalStateException("Add Spring Security to handle authentication");
	}

	@GetMapping("/login")
	public String showLoginPage() {

		return "forward:/";
	}

	@GetMapping("/csrf")
	public void csrf() {

		throw new IllegalStateException();
	}

}
