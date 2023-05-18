package com.auctionapp.user;

import com.auctionapp.common.AppException;
import com.auctionapp.db.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private AttachmentRepository attachmentRepository;


}
