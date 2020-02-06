package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.UserEntity;
import rs.ac.ftn.uns.upp.scientificcenter.dto.UserEntityDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserEntityService;
import rs.ac.ftn.uns.upp.scientificcenter.utils.MapperUtils;

public class UserRegistrationService implements JavaDelegate {
    private static final String TRUE = "confirm";

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public void execute(DelegateExecution execution) {
        UserEntityDto userEntityDto = MapperUtils.writeVariablesToObject(execution.getVariables(), UserEntityDto.class);
        UserEntity userEntity = MapperUtils.map(userEntityDto, UserEntity.class);
        userEntity.setReviewer(userEntityDto.getConfirmReviewer().equalsIgnoreCase(TRUE));

        userEntityService.save(userEntity);
    }
}
