package  com.product.cosmeticProduct.entity;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Document(collection ="userData")
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @Indexed(unique = true)
    @NonNull
    private String email;
    @NonNull
    private String mobileno;


    private List<String> roles = new ArrayList<>();
    private List<Product> productEntries = new ArrayList<>();



}
