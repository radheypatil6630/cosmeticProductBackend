package com.product.cosmeticProduct.service;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.product.cosmeticProduct.entity.Product;
import com.product.cosmeticProduct.entity.User;
import com.product.cosmeticProduct.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {



    @Autowired
    private ProductRepository productRepository;

    @Autowired
    UserService userService;

        @Autowired
    private Cloudinary cloudinary;


    public Product saveProducts(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProducts(User user){
        return user.getProductEntries();
    }

    public String uploadImage(MultipartFile imageFile) throws IOException {
        System.out.println("Uploading image to Cloudinary: " + imageFile.getOriginalFilename());
        Map<?, ?> uploadResult = cloudinary.uploader().upload(imageFile.getBytes(), ObjectUtils.emptyMap());
        String imageUrl = uploadResult.get("url").toString();
        System.out.println("Image uploaded successfully. URL: " + imageUrl);
        return imageUrl;
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
            removed = user.getProductEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveRegisterDetails(user); // Save user without modifying password
                productRepository.deleteById(id);

                userService.updateUserJournalEntries(username, user.getProductEntries());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting journal entry: ", e);
        }
        return removed;
    }
}
