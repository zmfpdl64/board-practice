package practice.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practice.board.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    public void deleteByCommentId(Long id);

}
