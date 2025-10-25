package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.UserDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Roles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Payloads.Request.RegisterRequest;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  {
    @Autowired
    private UserRepository userRepository;


    public List<UserDTO> getAllUsers() {
        List<Users> usersList = userRepository.findAllByIsDeletedFalse();;
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users users : usersList){
            UserDTO userDTO = new UserDTO();
            userDTO.setUser_id(users.getUser_id());
            userDTO.setRole_id(users.getRoles().getRole_id());
            userDTO.setPassword(users.getPassword());
            userDTO.setEmail(users.getEmail());
            userDTO.setCreated_at(users.getCreated_at());
            userDTOList.add(userDTO);
            System.out.println(users.getEmail());
        }
        return userDTOList;
    }

    public Users findUserById(long id) {
        return userRepository.findById((int) id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

    }

    public Users removeUserById(Integer id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setDeleted(true);
        userRepository.save(user);
        return user;
    }

}
