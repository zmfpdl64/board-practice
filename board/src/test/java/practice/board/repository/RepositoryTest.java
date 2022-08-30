package practice.board.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import practice.board.config.JpaConfig;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalTime.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;


@Import(JpaConfig.class)
@DisplayName("ArticleRepository Test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //실제 디비를 사용하기 위한 설정
class RepositoryTest {

    private final ArticleRepository articleRepository;

    public RepositoryTest(@Autowired ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @DisplayName("Save Article")
    @Test
    void givenNothing_whenSaveArticle_thenSaveArticleAndReturnArticle() {

        //Given
        long count = articleRepository.count();
        Article article = createArticle();


        //When
        Article cmp = articleRepository.save(article);
        long cmpcount = articleRepository.count();

        //Then
        assertThat(article.getClass()).isEqualTo(cmp.getClass());

    }
    @DisplayName("Update Article")
    @Test
    void givenNothing_whenUpdateArticle_thenUpdateArticle() {

        //Given
        Article article = createArticle();
        ArticleDto articleDto = createArticleDto();

        //When
        articleRepository.save(article);
        article.update(articleDto);

        Article findArticle = articleRepository.getReferenceById(article.getId());


        //Then
        assertThat(findArticle.getTitle()).isEqualTo(articleDto.title());



    }
    @DisplayName("Delete Article")
    @Test
    void givenArticleId_whenDeleteArticle_thenReturnNothing() {

        //Given
        long count = articleRepository.count();

        //When
        articleRepository.deleteById(1L);

        Optional<Article> article = articleRepository.findById(1L);
        Article findArticle = articleRepository.getReferenceById(2L);
        articleRepository.count();

        //Then
        assertThat(article).isEqualTo(Optional.empty());
        assertThat(findArticle.getId()).isEqualTo(2L);
    }

    protected Article createArticle() {
        return Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
        );
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

    protected Comment createCommet() {
        return Comment.of(
                createArticle(),
                createArticle().getUserAccount(),
                "content"
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