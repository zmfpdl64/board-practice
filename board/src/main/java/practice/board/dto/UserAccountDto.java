package practice.board.dto;

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

        Set<ArticleDto> articles,
        Set<CommentDto> comments,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt

) {

    public static UserAccountDto from(UserAccount entity) {
        return new UserAccountDto(
                entity.getUserId(),
                entity.getUserPassword(),
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
                entity.getModifiedAt()
        );
    }

    public static UserAccount toEntity(UserAccountDto dto) {
        return UserAccount.of(
                dto.userId,
                dto.password,
                dto.email,
                dto.nickname,
                dto.phone,
                dto.address
        );
    }

}
