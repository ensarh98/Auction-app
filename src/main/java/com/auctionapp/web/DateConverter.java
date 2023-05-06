package com.auctionapp.web;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;


public final class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
	if (source == null) {
	    return null;
	}

	return new Date(Long.parseLong(source));
    }
}
