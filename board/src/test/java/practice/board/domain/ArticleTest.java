package practice.board.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.board.dto.ArticleDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Article Entity TEST")
class ArticleTest {

    @DisplayName("Article entity, dto Conversion")
    @Test
    void givenNothing_whenCreateArticleEntity_returnArticleEntity() {

        //Given
        Article article = createArticle();

        ArticleDto articleDto = ArticleDto.from(article);
        //When

        //Then
        assertThat(articleDto.createdBy()).isEqualTo(article.getCreatedBy());

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