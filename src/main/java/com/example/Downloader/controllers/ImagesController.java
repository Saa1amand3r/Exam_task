package com.example.Downloader.controllers;

import com.example.Downloader.models.Images;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@Controller
public class ImagesController {
    // public Images convert(MultipartFile image) throws SQLException, IOException {
    //     byte[] byteData = image.getBytes();
    //     Blob img = new SerialBlob(byteData);
    //     Images avatar = new Images(img);
    //     avatar.setImage_name(image.getOriginalFilename());
    //     avatar.setImage_size(image.getSize());
    //     avatar.setImage_type(image.getContentType());
    //     return avatar;
    // }


    // public Images saveImage(MultipartFile image) {
    //     Images avatar = new Images();
    //     avatar.setImage_name(image.getOriginalFilename());
    //     avatar.setImage_size(image.getSize());
    //     avatar.setImage_type(image.getContentType());
        
    //     return null;
    // }
}
