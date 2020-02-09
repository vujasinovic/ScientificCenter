package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.Article;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    Article getOne(Long id);

    List<Article> findAll();

    Article findByTitle(String title);
}
