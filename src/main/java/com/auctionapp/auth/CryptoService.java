package com.auctionapp.auth;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;

/**
 * Cryptographic service.
 *
 */
@Service
public class CryptoService {

	private static final String SAFE_ALPHABET = "346789BCDFGHJKMPQRTVWXYbcdfghjkmpqrtvwxy";
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * Generates the password hash. <br>
	 * Note that depending on underlying algorithm being used, the same input value
	 * can produce different hashes for successive invocations of this method.
	 *
	 * @param value Password.
	 * @return Password hash (bcrypt by default).
	 */
	public String hashPassword(String value) {

		return passwordEncoder.encode(value);
	}

	/**
	 * Verifies the plain-text password against the encoded password.
	 *
	 * @param rawPassword     Plain-text password.
	 * @param encodedPassword Encoded password.
	 * @return True if the passwords are equal.
	 */
	public boolean verifyPassword(String rawPassword, String encodedPassword) {

		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

	/**
	 * General purpose hashing function.
	 *
	 * @param value Input value.
	 * @return SHA-256 hash.
	 */
	public String hash(String value) {
		if (value == null) {
			return null;
		}

		return Hashing.sha256().hashString(value, StandardCharsets.UTF_8).toString();
	}

	/**
	 * Generates 256-bit secret (so it can be used for HMAC-SHA256 hash)
	 *
	 * @return 256-bit random array.
	 */
	public byte[] generateSecret() {

		var secret = new byte[32];
		SECURE_RANDOM.nextBytes(secret);
		return secret;
	}

	/**
	 * Generates random string. <br>
	 * Note that these characters will not appear in the generated string: 0 1 2 5 A
	 * E I O U L N S Z
	 *
	 * @param length String length.
	 * @return Random string.
	 */
	public String randomString(int length) {

		var sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(SAFE_ALPHABET.charAt(SECURE_RANDOM.nextInt(SAFE_ALPHABET.length())));
		}
		return sb.toString();
	}
}
