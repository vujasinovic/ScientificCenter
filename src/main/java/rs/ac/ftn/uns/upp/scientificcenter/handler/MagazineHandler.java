package rs.ac.ftn.uns.upp.scientificcenter.handler;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MagazineHandler {

    public static List<String> getAvailableMagazines() {
        return List.of("Open-Access Magazine (active membership)", "Open-Access magazine", "Non-open-access magazine");
    }
}
