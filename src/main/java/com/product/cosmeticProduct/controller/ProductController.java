package com.product.cosmeticProduct.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.cosmeticProduct.entity.Product;
import com.product.cosmeticProduct.entity.User;
import com.product.cosmeticProduct.service.ProductService;
import com.product.cosmeticProduct.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    public ProductService productService;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/{userId}/create-product", consumes = "multipart/form-data")
    public ResponseEntity<?> createProduct(
            @PathVariable ObjectId userId,
            @RequestPart("product") String productJson,
            @RequestPart("image") MultipartFile imageFile
    ) {
        try {

            System.out.println("Received request to create product for user: " + userId);
            System.out.println("Product JSON: " + productJson);
            System.out.println("Image file: " + imageFile.getOriginalFilename());


            Optional<User> userOptional = userService.findUserById(userId);
            if (userOptional.isEmpty()) {

                return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
            }


            ObjectMapper objectMapper = new ObjectMapper();
            Product product = objectMapper.readValue(productJson, Product.class);


            String imageUrl = productService.uploadImage(imageFile);

            product.setImgUrl(imageUrl);

            User user = userOptional.get();
            user.getProductEntries().add(product);

            productService.saveProducts(product);
            userService.saveNewUser(user);


            System.out.println("Product saved successfully: " + product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            System.err.println("Error uploading image or saving product: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>("Error uploading image or saving product", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/{userId}/getProducts")  //
    public ResponseEntity<?> getProducts(@PathVariable ObjectId userId){

        Optional<User> id = userService.findUserById(userId);
        Product product1;
        if(id.isEmpty() ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


            User user = id.get();
            List<Product> productEntries = user.getProductEntries();
//        List<Product> productEntries = productService.findAllProducts();

            if (productEntries != null){
                return new ResponseEntity<>(productEntries, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
