package practice.board.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import practice.board.dto.CommentDto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Comment Entity, Dto TEST")
class CommentTest {

    @Test
    @DisplayName("Comment Entity <-> Dto Conversion")
    void givenNothing_whenChangeDtoFromEntity_thenReturnDto() {

        //Given
        Comment comment = createCommet();

        //When
        CommentDto commentDto = CommentDto.from(comment);

        //Then
        assertThat(comment.getContent()).isEqualTo(commentDto.content());
        //내용 동일한가



    }


    protected Article createArticle() {
        return Article.of(
                createUser(),
                "title",
                "content",
                "hashtag"
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