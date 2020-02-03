package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.Magazine;

import java.util.List;

public interface MagazineService {
    Magazine save(Magazine magazine);

    Magazine getOne(Long id);

    List<Magazine> findAll();
}
