package practice.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.board.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    public void deleteById(Long id);

}
