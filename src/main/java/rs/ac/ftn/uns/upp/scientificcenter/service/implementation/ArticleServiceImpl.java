package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Article;
import rs.ac.ftn.uns.upp.scientificcenter.repository.ArticleRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.ArticleService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;
    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article getOne(Long id) {
        return articleRepository.getOne(id);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
