package rs.ac.ftn.uns.upp.scientificcenter.handler;

import java.util.List;

public class EvaluationHandler {
    public static List<String> getEvaluations() {
        return List.of("Accept", "Accept (small adjustments)", "Accept (improve)", "Deny");
    }
}
