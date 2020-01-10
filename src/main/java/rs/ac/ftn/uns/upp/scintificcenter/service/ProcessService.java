package rs.ac.ftn.uns.upp.scintificcenter.service;

import org.camunda.bpm.engine.runtime.ProcessInstance;

public interface ProcessService {
    ProcessInstance start(String name);
}
