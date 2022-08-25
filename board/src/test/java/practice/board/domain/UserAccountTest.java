package practice.board.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.board.dto.UserAccountDto;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("UserAccount Entity - TEST")
class UserAccountTest {

    @DisplayName("UserAccount Entity <-> DTO")
    @Test
    void givenNothing_whenChangeUserAccountDtoFromDto_thenReturnDto() {

        //Given
        String userId = "woojin";
        String password= "1234";
        String email = "test@email.com";
        String nickname = "woojin";
        String phone = "01012345678";
        String address = "seoul";
        Set<String> articles = Set.of();
        Set<String> comments = Set.of();

        //When
        UserAccount userAccount = UserAccount.of(userId, password, email, nickname, phone, address);
        UserAccountDto Dto = UserAccountDto.from(userAccount);

        //Then
        assertThat(userAccount.getUserId()).isEqualTo(Dto.userId());
        assertThat(userAccount.getPassword()).isEqualTo(Dto.password());
        assertThat(userAccount.getEmail()).isEqualTo(Dto.email());
        assertThat(userAccount.getNickname()).isEqualTo(Dto.nickname());
        assertThat(userAccount.getPhone()).isEqualTo(Dto.phone());
        assertThat(userAccount.getAddress()).isEqualTo(Dto.address());
    }

}