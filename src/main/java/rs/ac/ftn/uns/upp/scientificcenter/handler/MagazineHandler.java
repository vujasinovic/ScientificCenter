package rs.ac.ftn.uns.upp.scientificcenter.handler;

import org.springframework.stereotype.Component;
import rs.ac.ftn.uns.upp.scientificcenter.bom.InitialMagazine;
import rs.ac.ftn.uns.upp.scientificcenter.repository.InitialMagazineRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class MagazineHandler {
    private static InitialMagazineRepository initialMagazineRepository;

    private MagazineHandler(InitialMagazineRepository initialMagazineRepository) {
        MagazineHandler.initialMagazineRepository = initialMagazineRepository;
    }

    public static List<String> getAvailableMagazines() {
        List<InitialMagazine> magazines = initialMagazineRepository.findAll();
        List<String> availableMagazines = new ArrayList<>();

        for (InitialMagazine magazine : magazines) {
            availableMagazines.add(magazine.getName());
        }

        return availableMagazines;
    }
}
