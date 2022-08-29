package practice.board.dto;

import practice.board.domain.Article;
import practice.board.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Set<CommentDto> comments
) {

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy(),
                entity.getComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))//TODO: Set<Comment>로 변경
        );
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.of(
                userAccount,
                title,
                content,
                hashtag
        );
    }




}
