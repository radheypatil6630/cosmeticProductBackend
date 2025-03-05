package com.product.cosmeticProduct.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@Document(collection = "Products")
public class Product {

    @Id
    ObjectId id;


    @NonNull
    private String productName;

    @NonNull
    private String productPrice;

    @NonNull
    private String productDescription;


    @NonNull
    private String imgUrl;


}
