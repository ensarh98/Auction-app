package com.auctionapp.auth;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.UserRecord;
import com.auctionapp.db.repository.UserRepository;
import com.auctionapp.user.User;

import javax.transaction.Transactional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Transactional
@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CryptoService cryptoService;

	@Autowired
	private MessageSource messageSource;
	public User register(User user) {

		if (user == null || user.getFirstName() == null || user.getLastName() == null
				|| user.getEmail() == null) {
			throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("MISSING_DATA", null, LocaleContextHolder.getLocale()));
		}

		var isExistsUserByEmail = userRepository.isExistsUserByEmail(user.getEmail()) > 0;
		if (isExistsUserByEmail) {
			throw new AppException(AppException.VALIDATION_ERROR, messageSource.getMessage("USER_EMAIL_EXISTS", null, LocaleContextHolder.getLocale()));
		}

		var userRecord = new UserRecord();
		userRecord.setPassword(cryptoService.hashPassword(user.getPassword()));
		userRecord.setFirstName(user.getFirstName());
		userRecord.setLastName(user.getLastName());
		userRecord.setEmail(user.getEmail().toLowerCase());
		userRecord.setPhone(user.getPhone());
		userRecord.setSecret(cryptoService.generateSecret());
		userRecord.setAddress(user.getAddress());
		userRecord.setCity(user.getCity());
		userRecord.setRegistrationDate(new Date());

		var userRecordId = userRepository.save(userRecord).getId();

		user.setId(userRecordId);
		return user;
	}

}
