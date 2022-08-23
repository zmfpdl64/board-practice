package practice.board.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import practice.board.dto.ArticleDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Article Entity 테스트")
class ArticleTest {

    @DisplayName("Article entity, dto 비교")
    @Test
    void givenNothing_whenCreateArticleEntity_returnArticleEntity() {

        //Given
        Article article = Article.of("woojin", Set.of(), "title", "content",
                "hashtag");
        
        ArticleDto articleDto = ArticleDto.from(article);
        //When

        //Then
        assertThat(articleDto.createdBy()).isEqualTo(article.getCreatedBy());

    }

}