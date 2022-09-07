package practice.board.repository;

import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import practice.board.domain.QUserAccount;
import practice.board.domain.UserAccount;

import java.util.List;

public interface UserRepository extends
        JpaRepository<UserAccount, Long>,
        QuerydslPredicateExecutor<UserAccount>,
        QuerydslBinderCustomizer<QUserAccount>
{


    UserAccount findByUserId(String UserId);
    List<UserAccount> findByUserPassword(String password);
    UserAccount findByEmail(String email);

    void deleteByUserId(String userId);



    @Override
    default void customize(QuerydslBindings bindings, QUserAccount root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.userId, root.userPassword, root.email);
        bindings.bind(root.userId).first(StringExpression::eq);
        bindings.bind(root.userPassword).first(StringExpression::eq);
        bindings.bind(root.email).first(StringExpression::eq);
    }
}
