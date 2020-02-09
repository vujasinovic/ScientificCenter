package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Article;
import rs.ac.ftn.uns.upp.scientificcenter.service.ArticleService;

public class ChangeArticleStatusService implements JavaDelegate {
    @Autowired
    private ArticleService articleService;

    @Override
    public void execute(DelegateExecution execution) {
        Article article = articleService.findByTitle((String) execution.getVariable("title"));

        article.setDoi(String.valueOf(System.currentTimeMillis()));
        article.setActive(true);

        articleService.save(article);
    }
}
