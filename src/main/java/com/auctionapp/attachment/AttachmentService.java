package com.auctionapp.attachment;

import com.auctionapp.db.model.AttachmentRecord;
import com.auctionapp.db.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

    @Service
    @Transactional
    public class AttachmentService {

        @Autowired
        private AttachmentRepository attachmentRepository;

        public AttachmentRecord uploadImage(MultipartFile file, Integer id) throws IOException {
            AttachmentRecord pImage = new AttachmentRecord();
            pImage.setFilename(file.getName());
            pImage.setType(file.getContentType());
            pImage.setOriginalFilename(ImageUtil.compressImage(file.getBytes()));
            pImage.setId(id);
            return attachmentRepository.save(pImage);
        }

        public byte[] downloadImage(Integer id){
            AttachmentRecord imageData = attachmentRepository.findAttachmentRecordById(id);
            return ImageUtil.decompressImage(imageData.getOriginalFilename());
        }
    }


