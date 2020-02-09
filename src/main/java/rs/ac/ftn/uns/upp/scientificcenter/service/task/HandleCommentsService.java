package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import static java.util.Objects.nonNull;

public class HandleCommentsService implements JavaDelegate {
    private static final String COMMENTS_TO_EDITOR = "commentsToEditor";
    private static final String EVALUATIONS = "evaluations";
    private static final String COMMENTS = "comments";
    private static final String REVIEWER = "reviewer";

    @Override
    public void execute(DelegateExecution execution) {
        handleEvaluations(execution);
        handleAllCommentsToEditor(execution);
        handleComments(execution);
    }

    private void handleAllCommentsToEditor(DelegateExecution execution) {
        String commentsToEditor;
        StringBuilder commentsBuilder;

        commentsToEditor = (String) execution.getVariable(COMMENTS_TO_EDITOR);

        if (nonNull(commentsToEditor)) {
            commentsBuilder = new StringBuilder(commentsToEditor);
        } else {
            commentsBuilder = new StringBuilder();
        }

        String commentToEditor = (String) execution.getVariable("commentToEditor");
        String reviewer = (String) execution.getVariable(REVIEWER);

        commentsBuilder.append("-").append(reviewer).append(" : ").append(commentToEditor).append("\n");

        execution.setVariable(COMMENTS_TO_EDITOR, commentsBuilder.toString());
    }

    private void handleEvaluations(DelegateExecution execution) {
        String evaluations;
        StringBuilder evaluationsBuilder;

        evaluations = (String) execution.getVariable(EVALUATIONS);

        if (nonNull(evaluations)) {
            evaluationsBuilder = new StringBuilder(evaluations);
        } else {
            evaluationsBuilder = new StringBuilder();
        }
        String evaluation = (String) execution.getVariable("evaluation");
        String reviewer = (String) execution.getVariable(REVIEWER);

        evaluationsBuilder.append("-").append(reviewer).append(" : ").append(evaluation).append("\n");

        execution.setVariable(EVALUATIONS, evaluationsBuilder.toString());
    }

    private void handleComments(DelegateExecution execution) {
        String comments;
        StringBuilder commentsBuilder;

        comments = (String) execution.getVariable(COMMENTS);

        if (nonNull(comments)) {
            commentsBuilder = new StringBuilder(comments);
        } else {
            commentsBuilder = new StringBuilder();
        }
        String comment = (String) execution.getVariable("comment");

        commentsBuilder.append("-").append(comment).append("\n");

        execution.setVariable(COMMENTS, commentsBuilder.toString());
    }
}
