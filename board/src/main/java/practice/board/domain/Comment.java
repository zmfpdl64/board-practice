package practice.board.domain;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Entity
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
public class Comment extends AuditingFileds{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    //TODO: 게시글 엔티티로 변경해주기
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "article_id")
    private Article articleId;

    //TODO: 유저 엔티티로 작성시 유저 계정으로 변경
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "useraccount_userid")
    private UserAccount userId;

    @Column(length=1000) @Setter
    private String content;


    protected Comment(){

    }

    private Comment( Article articleId, UserAccount userId, String content) {
        this.articleId = articleId;
        this.userId = userId;
        this.content = content;
    }

    public static Comment of( Article articleId, UserAccount userId, String content) {
        return new Comment(articleId, userId, content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj) return true;
        if(!(obj instanceof Comment comment)) return false;
        return commentId != null && commentId.equals(comment.commentId);

    }
}
