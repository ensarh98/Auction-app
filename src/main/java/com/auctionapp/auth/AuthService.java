package com.auctionapp.auth;

import com.auctionapp.common.AppException;
import com.auctionapp.db.model.UserRecord;
import com.auctionapp.db.repository.UserRepository;
import com.auctionapp.user.User;

import javax.transaction.Transactional;

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

	public User register(User user) {

		if (user == null || user.getFirstName() == null || user.getLastName() == null
				|| user.getEmail() == null) {
			throw new AppException(AppException.VALIDATION_ERROR, "Nedostaju obavezni podaci.");
		}

		var isExistsUserByEmail = userRepository.isExistsUserByEmail(user.getEmail()) > 0;
		if (isExistsUserByEmail) {
			throw new AppException(AppException.VALIDATION_ERROR,
					"Korisnik sa unesenom email adresom postoji u sistemu.");
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
