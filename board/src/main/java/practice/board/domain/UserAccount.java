package practice.board.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserAccount {

    @Id
    private String userId;

    private String userPassword;

    private String email;

    private String nickname;

    private String phone;

    private String address;

    //TODO: 게시글 엔티티, 댓글 엔티티 작성 시 변경
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<Article> articles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<Comment> comments = new LinkedHashSet<>();

    @CreatedDate private LocalDateTime createdAt;

    @LastModifiedDate private LocalDateTime modifiedAt;

    private UserAccount(String userId, String userPassword, String email, String nickname,
                        String phone, String address) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;

    }

    public static UserAccount of(String userId, String password, String email,
                                 String nickname, String phone, String address) {
        return new UserAccount(userId, password, email, nickname, phone, address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(!(obj instanceof UserAccount userAccount)) return false;
        return userId != null && userId.equals(userAccount.userId);

    }

    protected UserAccount() {

    }




}
