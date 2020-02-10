package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Article;
import rs.ac.ftn.uns.upp.scientificcenter.service.ArticleService;
import rs.ac.ftn.uns.upp.scientificcenter.utils.MapperUtils;

import static java.util.Objects.isNull;

public class PersistArticleService implements JavaDelegate {
    @Autowired
    private ArticleService articleService;

    @Override
    public void execute(DelegateExecution execution) {
        Article article = articleService.findByTitle((String) execution.getVariable("title"));

        if (isNull(article)) {
            article = MapperUtils.map(execution.getVariables(), Article.class);
            article.setAbstractText((String) execution.getVariable("abstract"));
            article.setActive(false);
        } else {
            article.setPdf((String) execution.getVariable("pdf"));
        }

        articleService.save(article);
    }
}
