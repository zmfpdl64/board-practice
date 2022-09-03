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
import practice.board.repository.ArticleRepository;
import practice.board.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("ArticleService - Test")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ArticleService articleService;


    @DisplayName("SearchArticles - ArticleService")
    @Test
    void searchArticles() {
        //TODO: 검색기능 테스트 아직 구현 안함
    }

    @DisplayName("GetArticle - ArticleService")
    @Test
    void getArticle() {


        //Given
        Article article = createArticle();
        given(articleRepository.getReferenceById(1L)).willReturn(article);

        //When
        Article getArticle = articleService.getArticle(1L);

        //Then
        assertThat(article).isEqualTo(getArticle);
        then(articleRepository).should().getReferenceById(1L);

    }

    @DisplayName("GetArticleDto - ArticleService")
    @Test
    void getArticleDto() {


        //Given
        Article article = createArticle();
        given(articleRepository.findById(1L)).willReturn(Optional.of(article));

        //When
        ArticleDto articleDto = articleService.getArticleDto(1L);

        //Then
        assertThat(articleDto)
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent())
                .hasFieldOrPropertyWithValue("hashtag", article.getHashtag());

        then(articleRepository).should().findById(1L);
    }


    @Test
    void saveArticle() {

        //Given
        long count = articleRepository.count();
        ArticleDto dto = createArticleDto();
        UserAccount user = createUser();
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());

        //When
        articleService.saveArticle(dto, user);
        long count1 = articleRepository.count();

        //Then
        assertThat(count).isEqualTo(count1);
        then(articleRepository).should().save(any(Article.class));
        System.out.println();

    }

    @Test
    void updateArticle() {
        //Given
        Article createArticle = createArticle();
        ArticleDto articleDto = updateArticleDto();
        UserAccount user = createUser();
        given(articleRepository.getReferenceById(articleDto.id())).willReturn(createArticle);
        //TODO: article의 ID가 null로 출력되는 현상이 있는데 왜 그런지 궁금함

        //When
        Article updateArticle = articleService.updateArticle(articleDto, user);

        //Then
        assertThat(updateArticle.getTitle()).isEqualTo("updatetitle");
        then(articleRepository).should().getReferenceById(articleDto.id());

    }

    @DisplayName("deleteArticle - ArticleService")
    @Test
    void deleteArticle() {

        //Given
        long articleId = 1L;
        Article article = createArticle();
        UserAccount user = createUser();
        willDoNothing().given(articleRepository).delete(article);
        given(articleRepository.getReferenceById(articleId)).willReturn(article);

        //When
        articleService.deleteArticle(articleId, user);

        //Then
        then(articleRepository).should().delete(article);
        then(articleRepository).should().getReferenceById(articleId);

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