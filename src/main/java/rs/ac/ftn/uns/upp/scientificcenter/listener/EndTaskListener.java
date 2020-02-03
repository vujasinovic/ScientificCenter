package rs.ac.ftn.uns.upp.scientificcenter.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;

public class EndTaskListener implements ExecutionListener {
    private final static Logger LOGGER = LogManager.getLogger(EndTaskListener.class);

    @Override
    public void notify(DelegateExecution execution) {
        LOGGER.info("finished");
    }
}
