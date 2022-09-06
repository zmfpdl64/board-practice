package practice.board.repository.querydsl;

import org.springframework.data.querydsl.binding.QuerydslBindings;
import practice.board.domain.QArticle;

import java.util.List;

public interface ArticleRepositoryCustom {
    List<String> findAllDistinctHashtags();

}
