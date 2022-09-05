package practice.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.dto.UserAccountDto;
import practice.board.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("UserAccountService - Test")
@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @Mock private UserRepository userRepository;

    @InjectMocks private UserAccountService sut;

    @DisplayName("SaveUser - UserService")
    @Test
    void saveUser() {

        //Given
        UserAccount user = createUser();
        UserAccountDto userDto = UserAccountDto.from(user);

        given(userRepository.save(user)).willReturn(user);

        //When

        UserAccount saveUser = sut.saveUser(userDto);

        //Then
        assertThat(saveUser.toString()).isEqualTo(user.toString());
        then(userRepository).should().save(user);

    }

    @DisplayName("DeleteUser - UserService")
    @Test
    void deleteUser() {

        //Given
        UserAccount user = createUser();

        willDoNothing().given(userRepository).delete(user);
        given(userRepository.findByUserId(user.getUserId())).willReturn(user);

        //When
        sut.deleteUser(user.getUserId(), user);

        //Then
        then(userRepository).should().delete(user);

    }


    protected ArticleDto createArticleDto() {
        return new ArticleDto(
                1L,
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
    protected ArticleDto updateArticleDto() {
        return new ArticleDto(
                1L,
                "updatetitle",
                "updatecontent",
                "updatehashtag",
                LocalDateTime.now(),
                "woojin",
                LocalDateTime.now(),
                "woojin",
                Set.of()
        );
    }

    protected Comment createComment() {

        Article article = createArticle();

        Comment comment = Comment.of(
                article,
                article.getUserAccount(),
                "content"
        );
        ReflectionTestUtils.setField(comment, "commentId", 1L);
        return comment;

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
        Article article = Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
        );
        ReflectionTestUtils.setField(article, "id", 1L);
        return article;
    }
}