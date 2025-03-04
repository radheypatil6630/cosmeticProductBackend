package com.product.cosmeticProduct.repository;

import com.product.cosmeticProduct.entity.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public interface ProductRepository extends MongoRepository<Product,ObjectId  > {

}
