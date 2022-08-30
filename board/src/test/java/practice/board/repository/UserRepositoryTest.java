package practice.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@DisplayName("UserRepository - Test")
class UserRepositoryTest {

    private final UserRepository userRepository;

    public UserRepositoryTest(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @DisplayName("Create UserAccount - Test")
    @Test
    void givenNothing_whenCreateUserAccount_thenReturnCreateUserAccount() {

        //Given
        long previous = userRepository.count();
        UserAccount user = createUser();

        //When
        userRepository.save(user);
        long saveDataSize = userRepository.count();

        //Then
        assertThat(previous).isEqualTo(saveDataSize-1L);
    }

    @DisplayName("Delete UserAccount - Test")
    @Test
    void givenUserId_whenCreateUserAccount_thenReturnCreateUserAccount() {
        //TODO: 유저를 삭제할 때 삭제가 되지 않는 현상이 있다. 해결해야 함
        //Given
        long saveDataSize = userRepository.count();



        //When

        long deletedSize = userRepository.count();
        List<UserAccount> result = userRepository.findAll();

        for(UserAccount user : result) {
            System.out.println(user);
            userRepository.deleteByUserId(user.getUserId());

        }

        //Then
        assertThat(saveDataSize).isEqualTo(userRepository.count());
    }




    protected ArticleDto createArticleDto() {
        return new ArticleDto(
                "dtotitle",
                "dtocontent",
                "dtohashtag",
                LocalDateTime.now(),
                "woojin",
                LocalDateTime.now(),
                "woojin",
                Set.of()
        );
    }

    protected Comment createComment() {

        Article article = createArticle();

        return Comment.of(
                article,
                article.getUserAccount(),
                "content"
        );
    }

    protected UserAccount createUser() {
        return UserAccount.of(
                "woojin2",
                "1234",
                "test@email.com",
                "woojin2",
                "01012345678",
                "home"
        );
    }
    protected Article createArticle() {
        return Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
        );
    }


}