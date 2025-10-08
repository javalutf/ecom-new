package com.application.ecom_applicationNew.service;


import com.application.ecom_applicationNew.dto.AddressDTO;
import com.application.ecom_applicationNew.dto.UserResponse;
import com.application.ecom_applicationNew.repository.UserRepository;
import com.application.ecom_applicationNew.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private Long nextId = 1L;

    public List<UserResponse> fetchAllUser(){
        return userRepository.findAll().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void createUser(User user){
        userRepository.save(user);
    }


    public Optional<User> fetchUserById(Long id) {
//       return userRepository.findAll().stream()
//               .filter(user -> user.getId().equals(id))
//               .findFirst();
        return userRepository.findById(id);
    }

    public boolean updateUser(Long id, User updatedUser){
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updatedUser.getFirstName());
                    existingUser.setLastName(updatedUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }

    private UserResponse mapToUserResponse(User user){
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress() !=null){
            AddressDTO address = new AddressDTO();
            address.setStreet(user.getAddress().getStreet());
            address.setCity(user.getAddress().getCity());
            address.setState(user.getAddress().getState());
            address.setCountry(user.getAddress().getCountry());
            address.setZipcode(user.getAddress().getZipcode());
            response.setAddress(address);
        }
        return response;
    }
}
