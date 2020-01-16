package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;
import rs.ac.ftn.uns.upp.scientificcenter.repository.ScientificAreaRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.ScientificAreaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScientificAreaServiceImpl implements ScientificAreaService {
    private final ScientificAreaRepository scientificAreaRepository;

    @Override
    public ScientificArea save(ScientificArea scientificArea) {
        return scientificAreaRepository.save(scientificArea);
    }

    @Override
    public ScientificArea getOne(Long id) {
        return scientificAreaRepository.getOne(id);
    }

    @Override
    public List<ScientificArea> findAll() {
        return scientificAreaRepository.findAll();
    }
}
