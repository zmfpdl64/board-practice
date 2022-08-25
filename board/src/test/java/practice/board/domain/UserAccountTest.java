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


        //When
        UserAccount userAccount = createUser();
        UserAccountDto Dto = UserAccountDto.from(userAccount);

        //Then
        assertThat(userAccount.getUserId()).isEqualTo(Dto.userId());
        assertThat(userAccount.getPassword()).isEqualTo(Dto.password());
        assertThat(userAccount.getEmail()).isEqualTo(Dto.email());
        assertThat(userAccount.getNickname()).isEqualTo(Dto.nickname());
        assertThat(userAccount.getPhone()).isEqualTo(Dto.phone());
        assertThat(userAccount.getAddress()).isEqualTo(Dto.address());
    }

    protected Article createArticle() {
        return Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
        );
    }

    protected UserAccount createUser() {
        return UserAccount.of(
                "woojin",
                "1234",
                "test@email.com",
                "woojin",
                "01012345678",
                "home"
        );
    }

}