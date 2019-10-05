package com.demo.demo.controller;

import com.demo.demo.response.LoginSignUpResponse;
import com.demo.demo.entity.User;
import com.demo.demo.request.LoginRequest;
import com.demo.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUser/{id}")
    public Optional<User> getUserById(@PathVariable(value = "id") Long id) {
        return userRepository.findById(id);
    }

    @PutMapping("/updateUser")
    public User updateUser(User userEntity) {

        Optional<User> originalUser = userRepository.findById(userEntity.getUserId());
        originalUser.get().setName(userEntity.getName());
        originalUser.get().setPassword(userEntity.getPassword());
        return userRepository.save(originalUser.get());
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Object> deleteUser(User userEntity) {

        Optional<User> originalUserEntity = userRepository.findById(userEntity.getUserId());
        userRepository.delete(originalUserEntity.get());

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public LoginSignUpResponse login(@RequestBody LoginRequest loginRequest){

        LoginSignUpResponse response = new LoginSignUpResponse();
        User user = userRepository.findByEmail(loginRequest.getEmail());
        
        if (user != null){
            if (loginRequest.getPassword().length() == 0){
                String savedSocialUserId = user.getSocial_user_id();
                if (savedSocialUserId.equals(loginRequest.getSocial_user_id())){
                    response.setStatusCode(200);
                    response.setMessage("Login successful");
                    response.setUser(user);
                    return response;
                }else{
                    response.setMessage("Something went wrong. Please try again later");
                    return response;
                }
            }else {
                String savedPassword = user.getPassword();
                if (savedPassword.equals(loginRequest.getPassword())) {
                    response.setStatusCode(200);
                    response.setMessage("Login successful");
                    response.setUser(user);
                    return response;
                } else {
                    response.setMessage("Please check email/password and try again");
                    return response;
                }
            }
        }else{
            response.setMessage("User does not exist");
            return response;
        }
    }

    @PostMapping("/signUp")
    public LoginSignUpResponse signUp(@RequestBody User signUpRequest) {
        LoginSignUpResponse signUpResponse = new LoginSignUpResponse();

        User userExists = userRepository.findByEmail(signUpRequest.getEmail());

        if (userExists != null){
            signUpResponse.setMessage("Email is already taken. Try another.");
            return signUpResponse;
        }

        User user = userRepository.save(signUpRequest);

        if (user != null){
            signUpResponse.setStatusCode(200);
            signUpResponse.setMessage("Registration successful");
            signUpResponse.setUser(user);
        }else{
            signUpResponse.setMessage("Registration failed");
        }

        return signUpResponse;
    }

}
