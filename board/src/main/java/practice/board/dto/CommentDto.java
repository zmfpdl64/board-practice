package practice.board.dto;

import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;

import java.time.LocalDateTime;

public record CommentDto(
        ArticleDto articleId,
        UserAccountDto userId,
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

    public Comment toEntity(Article article, UserAccount userAccount){
        return Comment.of(
                article,
                userAccount,
                content
        );
    }


}
