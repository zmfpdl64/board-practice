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
@Table(indexes = {
        @Index(columnList = "userId", unique = true),
        @Index(columnList = "email"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "modifiedAt")
})
public class UserAccount extends AuditingFileds{

    @Id
    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)

    private String userPassword;

    @Column(unique = true)
    private String email;

    private String nickname;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "userAccount",  cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<Article> articles = new LinkedHashSet<>();

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<Comment> comments = new LinkedHashSet<>();


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
