package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.InitialMagazine;

import java.util.List;

public interface InitialMagazineService {
    InitialMagazine getOne(Long id);

    List<InitialMagazine> findAll();
}
