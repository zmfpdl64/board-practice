package practice.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import practice.board.config.JpaConfig;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.dto.CommentDto;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@Import(JpaConfig.class)
@DisplayName("CommentRepository - Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    public CommentRepositoryTest(@Autowired CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    private final CommentRepository commentRepository;

    @DisplayName("Create Comment And Check Comments")
    @Test
    void givenNewComment_whenSaveComment_thenReturnSaveCommentAndCheckComments() {

        //Given
        long count = commentRepository.count();
        Comment comment = createComment();

        //When
        commentRepository.save(comment);
        long count1 = commentRepository.count();

        //Then
        assertThat(count+1L).isEqualTo(count1);

    }

    @DisplayName("Update Comment And Check Comments")
    @Test
    void givenCommentId_whenUpdateComment_thenReturnUpdateCommentAndCheckComments() {

        //Given
        Comment comment = commentRepository.getReferenceById(1L);


        //When
        comment.setContent("update Content");
        Comment updatecomment = commentRepository.save(comment);


        //Then
        assertThat(comment).isEqualTo(updatecomment);

    }

    @DisplayName("Delete Comment And Check Comments")
    @Test
    void givenCommentId_whenDeleteComment_thenReturnNothing() {

        //Given
        long previousecount = commentRepository.count();
        //When
        commentRepository.deleteByCommentId(1L);
        long deletedSize = commentRepository.count();

        //Then
        assertThat(previousecount).isEqualTo(deletedSize+1L);
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