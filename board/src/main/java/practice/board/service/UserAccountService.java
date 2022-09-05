package practice.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import practice.board.domain.UserAccount;
import practice.board.dto.UserAccountDto;
import practice.board.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAccountService {

    private UserRepository userRepository;

    public UserAccount saveUser(UserAccountDto dto) {
        return userRepository.save(
                UserAccountDto.toEntity(dto)
        );
    }

    public void deleteUser(String userId, UserAccount userAccount) {
        UserAccount findUser = userRepository.findByUserId(userId);
        if( userAccount.equals(findUser)) userRepository.delete(findUser);

    }

}
