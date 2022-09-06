package practice.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.board.domain.Article;
import practice.board.domain.UserAccount;
import practice.board.dto.ArticleDto;
import practice.board.repository.ArticleRepository;

import javax.persistence.EntityNotFoundException;

@Slf4j
@RequiredArgsConstructor  //생성자 관련 어노테이션
@Service    //Bean
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<Article> searchArticles() {
        return Page.empty();
        //TODO: 검색 기능 추후에 구현 예정
    }

    public Article getArticle(Long id) {
        return articleRepository.getReferenceById(id);
    }

    public ArticleDto getArticleDto(Long id) {
        return articleRepository.findById(id)
                .map(ArticleDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 없습니다 - articleId: "+ id));
    }

    @Transactional(readOnly = true)
    public Page<Article> searchArticleViaHashtag(String hashtag, Pageable pageable) {
        if( hashtag == null || hashtag.isBlank()) {
            return Page.empty(pageable);
        }
        return articleRepository.findByHashtag(hashtag, pageable);
    }


    public void saveArticle(ArticleDto dto, UserAccount userAccount) {
        articleRepository.save(dto.toEntity(userAccount));
    }

    public Article updateArticle(ArticleDto dto, UserAccount userAccount) {

        try{
            Article updateArticle = articleRepository.getReferenceById(dto.id());
            if(updateArticle.getUserAccount().equals(userAccount)) {
                if (dto.title() != null) updateArticle.setTitle(dto.title());
                if (dto.content() != null) updateArticle.setContent(dto.content());
                if (dto.content() != null) updateArticle.setHashtag(dto.hashtag());
            }
            return updateArticle;
        }
        catch(EntityNotFoundException e){
            log.warn("게시글 업데이트 실패, 게시글을 찾을 수 없습니다. - dto: {}", dto);
            return null;
        }
    }

    public void deleteArticle(Long articleId, UserAccount userAccount) {
        try {
            Article findArticle = articleRepository.getReferenceById(articleId);
            if(findArticle.getUserAccount().equals(userAccount))
                articleRepository.delete(findArticle);
        }
        catch(EntityNotFoundException e) {
            log.warn("삭제할 게시글이 존재하지 않습니다 - articleId - {}", articleId);
        }


    }


}
