package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.UserEntity;
import rs.ac.ftn.uns.upp.scientificcenter.repository.UserEntityRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserEntityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityRepository userEntityRepository;

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userEntityRepository.save(userEntity);
    }

    @Override
    public UserEntity getOne(Long id) {
        return userEntityRepository.getOne(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }
}
