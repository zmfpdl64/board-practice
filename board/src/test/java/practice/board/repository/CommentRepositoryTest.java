package practice.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        //Comment 엔티티에 user와 연관 맵핑하는 곳에서 orphanRemoval = true로 설정하여
        //자동으로 연관되어있는 객체를 삭제해서 오류가 발생했었다.

        //Then
        assertThat(previousecount).isEqualTo(deletedSize+1L);
    }

    @DisplayName("SearchContainingContent - CommentRepository")
    @Test
    void searchContainingContent() {
        //Given
        Pageable pageable = Pageable.ofSize(10);

        //When
        Page<Comment> PageComment = commentRepository.findByContentContaining("Nam", pageable);


        //Then
        for(Comment Comment : PageComment) {
            System.out.println("Comment: "+ Comment.getContent());
        }
    }

    @DisplayName("SearchContainingUserId - CommentRepository")
    @Test
    void searchContainingUserId() {
        //Given
        Pageable pageable = Pageable.ofSize(10);
        String userId = "uno";

        //When
        Page<Comment> PageComments = commentRepository.findByUserId_UserId(userId, pageable);

        //Then
        for(Comment comment: PageComments) {
            System.out.println("UserId: "+comment.getUserId());
        }
    }

    @DisplayName("SearchEqualCreatedBy - CommentRepository")
    @Test
    void searchEqualCreatedBy() {

        //Given
        Pageable pageable = Pageable.ofSize(10);
        String createdBy = "uno";

        //When
        Page<Comment> PageComment = commentRepository.findByCreatedByContaining(createdBy, pageable);

        //Then
        for(Comment comment: PageComment){
            System.out.println("createdBy: " + comment.getCreatedBy());
        }

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