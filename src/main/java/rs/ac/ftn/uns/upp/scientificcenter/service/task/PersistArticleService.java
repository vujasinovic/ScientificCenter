package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Article;
import rs.ac.ftn.uns.upp.scientificcenter.service.ArticleService;
import rs.ac.ftn.uns.upp.scientificcenter.utils.MapperUtils;

public class PersistArticleService implements JavaDelegate {
    @Autowired
    private ArticleService articleService;

    @Override
    public void execute(DelegateExecution execution) {
        Article article = MapperUtils.map(execution.getVariables(), Article.class);
        article.setAbstractText((String) execution.getVariable("abstract"));
        article.setActive(false);

        articleService.save(article);
    }
}
