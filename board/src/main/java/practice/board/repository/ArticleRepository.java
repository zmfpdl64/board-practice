package practice.board.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import practice.board.domain.Article;
import practice.board.domain.QArticle;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.repository.querydsl.ArticleRepositoryCustom;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{

    Page<Article> findByHashtag(String hashtag, Pageable pageable);
    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserId(String userId, Pageable pageable);
    Page<Article> findByCreatedBy(String createdBy, Pageable pageable);


    public void deleteById(Long id);
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy, root.userAccount);
//        bindings.bind(root.title).first(StringExpression::likeIgnoreCase);  // like '${v}'  수동으로 넣어주고 싶을 때
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);  // like '%${v}$%' 자동으로 %를 넣어준다.
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::eq);
//        bindings.bind(root.userAccount).first();
    }
}
