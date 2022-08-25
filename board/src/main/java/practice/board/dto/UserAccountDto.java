package practice.board.dto;

import practice.board.domain.Comment;
import practice.board.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record UserAccountDto(
        String userId,
        String password,
        String email,
        String nickname,
        String phone,
        String address,
        //TODO: 게시글 엔티티, 댓글 엔티티 작성시 변경해야함
        Set<ArticleDto> articles,
        Set<CommentDto> comments,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getNickname(),
                entity.getPhone(),
                entity.getAddress(),
                entity.getArticles().stream()
                        .map(ArticleDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getCreatedAt(),
                entity.getModifiedBy()
        );
    }

}
