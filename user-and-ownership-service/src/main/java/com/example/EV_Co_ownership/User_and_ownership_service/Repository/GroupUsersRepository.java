package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUsersRepository extends JpaRepository<GroupUsers, Integer> {
}
