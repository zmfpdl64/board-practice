package practice.board.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import practice.board.domain.Comment;
import practice.board.domain.QComment;
import practice.board.domain.UserAccount;

import java.time.LocalDateTime;

public interface CommentRepository extends
        JpaRepository<Comment, Long>,
    QuerydslPredicateExecutor<Comment>,
        QuerydslBinderCustomizer<QComment>
{

    Page<Comment> findByUserId_UserId(String userId, Pageable pageable);
    Page<Comment> findByContentContaining(String content, Pageable pageable);
    Page<Comment> findByCreatedByContaining(String createdBy, Pageable pageable);

    public void deleteByCommentId(Long id);

    @Override
    default void customize(QuerydslBindings bindings, QComment root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::eq);

    }
}
