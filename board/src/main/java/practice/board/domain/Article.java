package practice.board.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table( indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "content"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ARTICLE_ID")
    private Long id;

    //TODO: 유저 엔티티 작성시 변경
    @ManyToOne
    @JoinColumn(name = "USERACCOUNT_ID")
    private UserAccount userAccount;

    //TODO: 댓글 엔티티 작성시 변경
    @OneToMany
    @JoinColumn(name = "COMMENT_ID")
    private final Set<Comment> comments = new LinkedHashSet<>();

     private String title;

     private String content;

     private String hashtag;

     @CreatedDate private LocalDateTime createdAt;

     @CreatedBy private String createdBy;

     @LastModifiedDate private LocalDateTime modifiedAt;

     @LastModifiedBy private String modifiedBy;



    private Article(UserAccount userAccount, String title, String content, String hashtag) {
            this.userAccount = userAccount;
            this.title = title;
            this.content = content;
            this.hashtag = hashtag;
    }

    protected Article() {

    }

    public static Article of(UserAccount userAccount, String title, String content, String hashtag) {
        return new Article(userAccount, title, content, hashtag );
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //Study:
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(!(obj instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }
}


