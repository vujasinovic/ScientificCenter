package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.User;
import rs.ac.ftn.uns.upp.scientificcenter.repository.UserRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RuntimeService runtimeService;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void activateUser(String processId) {
        runtimeService.setVariable(processId, "userActivated", true);
    }
}
