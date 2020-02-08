package rs.ac.ftn.uns.upp.scientificcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;

@Repository
public interface ScientificAreaRepository extends JpaRepository<ScientificArea, Long> {
    ScientificArea findByTitleIgnoreCase(String title);
}
