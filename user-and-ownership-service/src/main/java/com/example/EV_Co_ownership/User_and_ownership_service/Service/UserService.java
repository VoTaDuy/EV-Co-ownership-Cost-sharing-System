package com.example.EV_Co_ownership.User_and_ownership_service.Service;

import com.example.EV_Co_ownership.User_and_ownership_service.DTO.UserDTO;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Roles;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.Users;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.RolesRepository;
import com.example.EV_Co_ownership.User_and_ownership_service.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        List<Users> users = userRepository.findAllActive();

        return users.stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertUserToDTO(Users user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (user.getRoles() != null) {
            userDTO.setRole_id(user.getRoles().getRole_id());
        }
        userDTO.setVerified(user.isVerified());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    }

    public Users getUserById(UUID id) {
        return userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    public Users createUser(UserDTO userDTO) {
        Users user = modelMapper.map(userDTO, Users.class);

        if (userDTO.getRole_id() != 0) {
            Roles role = rolesRepository.findById(userDTO.getRole_id())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + userDTO.getRole_id()));
            user.setRoles(role);
        }

        return userRepository.save(user);
    }

    public Users updateUser(UUID id, UserDTO userDTO) {
        Users user = getUserById(id);
        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword());
        }
        if (userDTO.getRole_id() != 0) {
            Roles role = rolesRepository.findById(userDTO.getRole_id())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + userDTO.getRole_id()));
            user.setRoles(role);
        }

        user.setVerified(userDTO.isVerified());
        user.setDeleted(userDTO.isDeleted());

        return userRepository.save(user);
    }

    public Users removeUserById(UUID id) {
        Users user = getUserById(id);
        user.setDeleted(true);
        return userRepository.save(user);
    }
}