package com.product.cosmeticProduct.controller;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
public class testController {

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping("/test-upload")
    public String testUpload(@RequestParam("image") MultipartFile imageFile) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
            return "Image uploaded successfully! URL: " + uploadResult.get("url");
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading image: " + e.getMessage();
        }
    }
}