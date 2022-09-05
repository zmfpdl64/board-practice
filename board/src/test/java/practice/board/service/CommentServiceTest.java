package practice.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.util.ReflectionTestUtils;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.dto.CommentDto;
import practice.board.repository.CommentRepository;
import practice.board.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@DisplayName("CommentService - Test")
@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock private CommentRepository commentRepository;
    @Mock private UserRepository userRepository;
    @InjectMocks private CommentService sut;


    @DisplayName("SearchComment - CommentService")
    @Test
    void searchComments() {
        //TODO: 검색기능 및 페이징 구현시 작성
    }

    @DisplayName("GetComment - CommentService")
    @Test
    void getComment() {
        //Given
        Comment comment = createComment();
        given(commentRepository.getReferenceById(comment.getCommentId())).willReturn(comment);

        //When
        sut.getComment(comment.getCommentId());

        //Then
        then(commentRepository).should().getReferenceById(comment.getCommentId());
    }



    @DisplayName("getCommentDto - CommentService")
    @Test
    void getCommentDto() {

        //Given
        CommentDto commentDto = createCommentDto();
        given(commentRepository.findById(commentDto.commentId())).willReturn(Optional.of(createComment()));

        //When
        CommentDto getCommentDto = sut.getCommentDto(commentDto.commentId());

        //Then
        then(commentRepository).should().findById(commentDto.commentId());

    }

    @DisplayName("SaveComment - CommentService")
    @Test
    void saveComment() {

        //Given
        Comment comment = createComment();
        CommentDto commentDto = createCommentDto();

        given(commentRepository.save(any(Comment.class))).willReturn(comment);

        //When
        sut.saveComment(commentDto, comment.getArticleId(), comment.getUserId());

        //Then
        then(commentRepository).should().save(any(Comment.class));
    }

    @DisplayName("UpdateComment - CommentService")
    @Test
    void updateComment() {

        //Given
        Comment comment = createComment();
        CommentDto commentDto = createCommentDto();
        comment.setContent("update Content");
        given(commentRepository.save(any(Comment.class))).willReturn(comment);
        given(commentRepository.getReferenceById(comment.getCommentId())).willReturn(comment);

        //When
        sut.updateComment(commentDto,comment.getUserId());

        //Then
        then(commentRepository).should().save(any(Comment.class));

    }
    @DisplayName("UpdateComment Throw Exception - CommentService")
    @Test
    void updateCommentButNotExist() {

        //Given
        Long commentId = 0L;
        CommentDto commentDto = createCommentDto();
        UserAccount user = createUser();
        given(commentRepository.getReferenceById(commentId)).willThrow(new EntityNotFoundException("댓글 업데이트 실패, 댓글을 찾을 수 없습니다. - dto: "+ commentId));

        //When
       Throwable t = catchThrowable(() -> sut.getComment(commentId));

        //Then
        assertThat(t)
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("댓글 업데이트 실패, 댓글을 찾을 수 없습니다. - dto: "+ commentId);
        then(commentRepository).should().getReferenceById(commentId);

    }

    @DisplayName("DeleteComment - CommentService")
    @Test
    void deleteComment() {

        //Given
        Comment comment = createComment();
        given(commentRepository.getReferenceById(comment.getCommentId())).willReturn(comment);

        //When
        sut.deleteComment(comment.getCommentId(), comment.getUserId());

        //Then
        then(commentRepository).should().getReferenceById(comment.getCommentId());

    }

    @DisplayName("deleteComment Throw Exception - CommentService")
    @Test
    void deleteCommentButNotExists() {

        //Given
        Comment comment = createComment();
        given(commentRepository.getReferenceById(comment.getCommentId())).willReturn(comment);

        //When
        sut.deleteComment(comment.getCommentId(), comment.getUserId());

        //Then
        then(commentRepository).should().getReferenceById(comment.getCommentId());

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

    protected CommentDto createCommentDto() {
        Comment comment = createComment();
        return CommentDto.from(comment);
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