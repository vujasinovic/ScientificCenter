package rs.ac.ftn.uns.upp.scientificcenter.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Magazine;
import rs.ac.ftn.uns.upp.scientificcenter.repository.MagazineRepository;
import rs.ac.ftn.uns.upp.scientificcenter.service.MagazineService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MagazineServiceImpl implements MagazineService {

    private final MagazineRepository magazineRepository;

    @Override
    public Magazine save(Magazine magazine) {
        return magazineRepository.save(magazine);
    }

    @Override
    public Magazine getOne(Long id) {
        return magazineRepository.getOne(id);
    }

    @Override
    public List<Magazine> findAll() {
        return magazineRepository.findAll();
    }
}
