package practice.board.dto;

import practice.board.domain.Article;
import practice.board.domain.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        ArticleDto articleId,
        UserAccountDto userId, //TODO: 유저 엔티티 작성 시 변경
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                ArticleDto.from(entity.getArticleId()),
                UserAccountDto.from(entity.getUserId()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }


}
