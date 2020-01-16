package rs.ac.ftn.uns.upp.scientificcenter.service;

import org.camunda.bpm.engine.runtime.ProcessInstance;

public interface ProcessService {
    ProcessInstance start(String name);
}
