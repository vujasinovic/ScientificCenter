package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User getOne(Long id);

    List<User> findAll();
}
