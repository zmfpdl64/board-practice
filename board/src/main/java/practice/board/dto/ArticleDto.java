package practice.board.dto;

import practice.board.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;

public record ArticleDto(
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Set<String> comments
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
                Set.of()    //TODO: Set<Comment>로 변경
        );
    }




}
