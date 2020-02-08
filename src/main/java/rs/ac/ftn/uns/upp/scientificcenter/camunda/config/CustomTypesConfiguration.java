package rs.ac.ftn.uns.upp.scientificcenter.camunda.config;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.configuration.CamundaProcessEngineConfiguration;
import org.springframework.stereotype.Component;
import rs.ac.ftn.uns.upp.scientificcenter.camunda.type.ListFormFieldType;
import rs.ac.ftn.uns.upp.scientificcenter.camunda.type.ListValueSerializer;

@Component
public class CustomTypesConfiguration implements CamundaProcessEngineConfiguration {

    @Override
    public void preInit(ProcessEngineConfigurationImpl processEngineConfiguration) {
        processEngineConfiguration.getCustomPostVariableSerializers().add(new ListValueSerializer());
        processEngineConfiguration.getCustomFormTypes().add(new ListFormFieldType());
    }
}
