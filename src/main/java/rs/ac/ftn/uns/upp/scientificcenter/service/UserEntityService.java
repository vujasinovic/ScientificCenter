package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.UserEntity;

import java.util.List;

public interface UserEntityService {
    UserEntity save(UserEntity userEntity);

    UserEntity getOne(Long id);

    List<UserEntity> findAll();
}
