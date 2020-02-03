package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import rs.ac.ftn.uns.upp.scientificcenter.dto.CamundaUserDto;
import rs.ac.ftn.uns.upp.scientificcenter.utils.VariableHelper;

public class CreateUserService implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        IdentityService identityService = execution.getProcessEngineServices().getIdentityService();

        CamundaUserDto camundaUserDto = VariableHelper.writeVariablesToObject(execution.getVariables(), CamundaUserDto.class);

        User user = identityService.newUser(camundaUserDto.getUsername());
        user.setEmail(camundaUserDto.getEmail());
        user.setFirstName(camundaUserDto.getName());
        user.setLastName(camundaUserDto.getSurname());
        user.setPassword(camundaUserDto.getPassword());

        identityService.saveUser(user);
    }
}
