package practice.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import practice.board.domain.Article;
import practice.board.domain.Comment;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.dto.CommentDto;
import practice.board.repository.ArticleRepository;
import practice.board.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public Page<Comment> searchComments() {
        return Page.empty();
        //TODO: 검색 기능 추후에 구현 예정
    }

    public Comment getComment(Long id) {
        return commentRepository.getReferenceById(id);
    }

    public CommentDto getCommentDto(Long id) {
        return commentRepository.findById(id)
                .map(CommentDto::from)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 없습니다 - commentId: "+ id));
    }

    public void saveComment(CommentDto dto, Article article, UserAccount userAccount) {
        commentRepository.save(dto.toEntity(article, userAccount));
    }

    public Comment updateComment(CommentDto dto, UserAccount userAccount) {

        try{
            Comment updateComment = commentRepository.getReferenceById(dto.commentId());
            if(updateComment.getUserId().equals(userAccount)) {
                if (dto.content() != null) updateComment.setContent(dto.content());
                commentRepository.save(updateComment);
            }
            return updateComment;
        }
        catch(EntityNotFoundException e){
            log.warn("댓글 업데이트 실패, 댓글을 찾을 수 없습니다. - dto: {}", dto.commentId());
            return null;
        }
    }

    public void deleteComment(Long commentId, UserAccount userAccount) {
        try {
            Comment findComment = commentRepository.getReferenceById(commentId);
            if(findComment.getUserId().equals(userAccount))
                commentRepository.delete(findComment);
        }
        catch(EntityNotFoundException e) {
            log.warn("삭제할 댓글이 존재하지 않습니다 - commentId - {}", commentId);
        }


    }

}
