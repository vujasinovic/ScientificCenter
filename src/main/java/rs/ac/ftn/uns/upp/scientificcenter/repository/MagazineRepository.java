package rs.ac.ftn.uns.upp.scientificcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Magazine;

@Repository
public interface MagazineRepository extends JpaRepository<Magazine, Long> {
}
