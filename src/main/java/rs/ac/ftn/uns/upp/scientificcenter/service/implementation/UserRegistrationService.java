package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.UserEntity;
import rs.ac.ftn.uns.upp.scientificcenter.dto.UserEntityDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserEntityService;
import rs.ac.ftn.uns.upp.scientificcenter.utils.VariableHelper;

public class UserRegistrationService implements JavaDelegate {

    private static final String TRUE = "confirm";

    @Autowired
    private UserEntityService userEntityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        UserEntityDto userEntityDto = VariableHelper.writeVariablesToObject(execution.getVariables(), UserEntityDto.class);

        UserEntity userEntity = new UserEntity();
        userEntity.setName(userEntityDto.getName());
        userEntity.setSurname(userEntityDto.getSurname());
        userEntity.setAcademicTitle(userEntityDto.getAcademicTitle());
        userEntity.setCity(userEntityDto.getCity());
        userEntity.setPassword(userEntityDto.getPassword());
        userEntity.setUsername(userEntityDto.getUsername());
        userEntity.setState(userEntityDto.getState());

        boolean reviewer = userEntityDto.getConfirmReviewer() != null;

        assert userEntityDto.getConfirmReviewer() != null;

        userEntity.setReviewer(userEntityDto.getConfirmReviewer().equalsIgnoreCase(TRUE));

        userEntityService.save(userEntity);
    }
}
