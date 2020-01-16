package rs.ac.ftn.uns.upp.scientificcenter.service;

import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;

import java.util.List;

public interface ScientificAreaService {
    ScientificArea save(ScientificArea scientificArea);

    ScientificArea getOne(Long id);

    List<ScientificArea> findAll();
}
