package practice.board.dto;

import practice.board.domain.UserAccount;

import java.time.LocalDateTime;
import java.util.Set;

public record UserAccountDto(
        String userId,
        String password,
        String email,
        String nickname,
        String phone,
        String address,
        //TODO: 게시글 엔티티, 댓글 엔티티 작성시 변경해야함
        Set<String> articles,
        Set<String> comments,
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
                Set.of(),
                Set.of(),
                entity.getCreatedAt(),
                entity.getModifiedBy()
        );
    }

}
