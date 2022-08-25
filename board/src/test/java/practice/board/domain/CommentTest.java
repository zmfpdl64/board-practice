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
        Long articleId = 1L;
        String userId = "woojin";
        String content = "content";
        Comment comment = Comment.of(articleId, userId, content);

        //When
        CommentDto commentDto = CommentDto.from(comment);

        //Then
        assertThat(comment.getContent()).isEqualTo(commentDto.content());
        //내용 동일한가



    }
}