package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.InitialMagazine;
import rs.ac.ftn.uns.upp.scientificcenter.repository.InitialMagazineRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.InitialMagazineService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InitialMagazineServiceImpl implements InitialMagazineService {
    private final InitialMagazineRepository initialMagazineRepository;

    @Override
    public InitialMagazine getOne(Long id) {
        return initialMagazineRepository.getOne(id);
    }

    @Override
    public List<InitialMagazine> findAll() {
        return initialMagazineRepository.findAll();
    }
}
