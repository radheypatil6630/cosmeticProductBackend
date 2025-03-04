package  com.product.cosmeticProduct.service;

import com.product.cosmeticProduct.entity.User;
import com.product.cosmeticProduct.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public  User saveNewUser(User user){
        if (user.getUsername() == null || user.getUsername().replaceAll("\\s", "").isEmpty()|| user.getPassword() == null || user.getPassword().replaceAll("\\s", "").isEmpty() ||
                user.getEmail() == null ||user.getEmail().replaceAll("\\s", "").isEmpty() || user.getMobileno() == null || user.getMobileno().replaceAll("\\s", "").isEmpty()) {
            System.out.println("data not  empty");
            throw new IllegalArgumentException("User fields cannot be null");

        }
        if (user.getMobileno().length() != 10 || !Character.isDigit(Integer.parseInt(user.getMobileno())) ){
            System.out.println("phone no error");
            throw new IllegalArgumentException("length not equal to 10 and require digits only");
        }

        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }

    public boolean findByUsername(String username, String password) {
            boolean res = false; /// radhey
              User user =  userRepository.findByUsername(username);
              if(user != null && user.getPassword().equals(password)){

                  res = true;

              }
        return res;
    }


    public List<User> getALlUser() {
        return  userRepository.findAll();
    }

    public Optional<User> findUserById(ObjectId id){
        return userRepository.findById(id);
    }
}
