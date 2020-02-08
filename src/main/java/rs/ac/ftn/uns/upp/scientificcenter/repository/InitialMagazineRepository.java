package rs.ac.ftn.uns.upp.scientificcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ftn.uns.upp.scientificcenter.bom.InitialMagazine;

@Repository
public interface InitialMagazineRepository extends JpaRepository<InitialMagazine, Long> {
    InitialMagazine findByNameIgnoreCase(String name);
}
