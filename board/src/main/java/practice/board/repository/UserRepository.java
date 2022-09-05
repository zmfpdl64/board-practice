package practice.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.board.domain.UserAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUserId(String UserId);
    void deleteByUserId(String userId);

}
