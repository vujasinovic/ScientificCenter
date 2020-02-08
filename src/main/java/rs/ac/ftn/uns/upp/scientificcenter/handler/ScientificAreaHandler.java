package rs.ac.ftn.uns.upp.scientificcenter.handler;

import org.springframework.stereotype.Component;
import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;
import rs.ac.ftn.uns.upp.scientificcenter.repository.ScientificAreaRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScientificAreaHandler {
    private static ScientificAreaRepository scientificAreaRepository;

    private ScientificAreaHandler(ScientificAreaRepository scientificAreaRepository) {
        ScientificAreaHandler.scientificAreaRepository = scientificAreaRepository;
    }

    public static List<String> getScientificAreas() {
        List<ScientificArea> scientificAreas = scientificAreaRepository.findAll();
        List<String> availableScientificAreas = new ArrayList<>();

        for (ScientificArea sc : scientificAreas) {
            availableScientificAreas.add(sc.getTitle());
        }

        return availableScientificAreas;
    }
}
