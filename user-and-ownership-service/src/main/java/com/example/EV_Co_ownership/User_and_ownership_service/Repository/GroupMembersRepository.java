
package com.example.EV_Co_ownership.User_and_ownership_service.Repository;

import com.example.EV_Co_ownership.User_and_ownership_service.Embeddable.GroupMemberId;
import com.example.EV_Co_ownership.User_and_ownership_service.Entity.GroupMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMembersRepository extends JpaRepository<GroupMembers, GroupMemberId> {
    // Cũng giống như trên, chúng ta chưa cần hàm tùy chỉnh
}