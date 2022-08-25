package practice.board.dto;

import practice.board.domain.Comment;

import java.time.LocalDateTime;

public record CommentDto(
        Long articleId,
        String userId, //TODO: 유저 엔티티 작성 시 변경
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getArticleId(),
                entity.getUserId(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }


}
