package rs.ac.ftn.uns.upp.scintificcenter.service.implementation;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scintificcenter.service.ProcessService;

@Service
public class ProcessServiceImpl implements ProcessService {
    private final RuntimeService runtimeService;

    public ProcessServiceImpl(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public ProcessInstance start(String name) {
        return runtimeService.startProcessInstanceByKey(name);
    }
}
