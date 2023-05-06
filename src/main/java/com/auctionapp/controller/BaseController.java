package com.auctionapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auctionapp.common.AppException;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = {
		@ApiResponse(code = 400, message = "Client Error", response = BaseController.ErrorDetails.class),
		@ApiResponse(code = 401, message = "Unauthorized"),
		@ApiResponse(code = 403, message = "Forbidden", response = BaseController.ErrorDetails.class),
		@ApiResponse(code = 500, message = "Internal Server Error", response = BaseController.ErrorDetails.class) })
public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);
	private static final String INTERNAL_ERR_MSG = "Internal error.";

	@ExceptionHandler({ Exception.class })
	public final ResponseEntity<ErrorDetails> handleException(Exception ex) {
		if (ex instanceof AppException) {
			var e = (AppException) ex;

			if (AppException.INTERNAL_ERROR.equals(e.getCode())) {
				// log real cause and return generic error message
				LOG.error(e.getMessage());

				var details = new ErrorDetails(e.getCode(), INTERNAL_ERR_MSG);
				return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			var details = new ErrorDetails(e.getCode(), e.getMessage());
			return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
		}

		// unhandled exception, log stacktrace for further analysis
		LOG.error(AppException.INTERNAL_ERROR, ex);

		var details = new ErrorDetails(AppException.INTERNAL_ERROR, INTERNAL_ERR_MSG);
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	public final ResponseEntity<ErrorDetails> handleException(AccessDeniedException ex) {

		var details = new ErrorDetails(AppException.UNAUTHORIZED_ERROR, ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.FORBIDDEN);
	}

	public static class ErrorDetails {

		private String code;
		private String message;

		public ErrorDetails(String code, String message) {
			this.code = code;
			this.message = message;
		}

		public String getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}

	}
}
