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

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Lấy tất cả user active
    public List<Users> getAllUsers() {
        return userRepository.findAllActive();
    }

    // Lấy user theo UUID
    public Users getUserById(UUID id) {
        return userRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    // Tạo mới user từ DTO
    public Users createUser(UserDTO userDTO) {
        Users user = modelMapper.map(userDTO, Users.class);

        if (userDTO.getRole_id() != 0) {
            Roles role = rolesRepository.findById(userDTO.getRole_id())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + userDTO.getRole_id()));
            user.setRoles(role);
        }

        return userRepository.save(user);
    }

    // Cập nhật user
    public Users updateUser(UUID id, UserDTO userDTO) {
        Users user = getUserById(id);

        if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
            user.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(userDTO.getPassword()); // TODO: Hash password
        }
        if (userDTO.getRole_id() != 0) {
            Roles role = rolesRepository.findById(userDTO.getRole_id())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + userDTO.getRole_id()));
            user.setRoles(role);
        }

        user.setVerified(userDTO.isIs_verified());
        user.setDeleted(userDTO.isDeleted());

        return userRepository.save(user);
    }

    // Soft delete
    public Users removeUserById(UUID id) {
        Users user = getUserById(id);
        user.setDeleted(true);
        return userRepository.save(user);
    }
}
