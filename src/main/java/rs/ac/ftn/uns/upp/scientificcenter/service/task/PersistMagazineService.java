package rs.ac.ftn.uns.upp.scientificcenter.service.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import rs.ac.ftn.uns.upp.scientificcenter.bom.Magazine;
import rs.ac.ftn.uns.upp.scientificcenter.bom.ScientificArea;
import rs.ac.ftn.uns.upp.scientificcenter.bom.User;
import rs.ac.ftn.uns.upp.scientificcenter.dto.PersistMagazineDto;
import rs.ac.ftn.uns.upp.scientificcenter.service.MagazineService;
import rs.ac.ftn.uns.upp.scientificcenter.service.ScientificAreaService;
import rs.ac.ftn.uns.upp.scientificcenter.service.UserService;
import rs.ac.ftn.uns.upp.scientificcenter.utils.MapperUtils;

import java.util.ArrayList;
import java.util.List;

public class PersistMagazineService implements JavaDelegate {
    @Autowired
    private MagazineService magazineService;

    @Autowired
    private ScientificAreaService scientificAreaService;

    @Autowired
    private UserService userService;

    @Override
    public void execute(DelegateExecution execution) {
        PersistMagazineDto persistMagazineDto = MapperUtils.writeVariablesToObject(execution.getVariables(), PersistMagazineDto.class);

        Magazine magazine = new Magazine();
        magazine.setTitle(persistMagazineDto.getTitle());
        magazine.setDate(persistMagazineDto.getDate());
        magazine.setIssn(persistMagazineDto.getIssn());
        magazine.setNumber(persistMagazineDto.getNumber());
        magazine.setPaymentMethod(persistMagazineDto.getPaymentMethod());

        User mainEditor = new User();
        mainEditor.setUsername(persistMagazineDto.getMainEditor());

        magazine.setEditor(userService.save(mainEditor));

        List<ScientificArea> scientificAreas = new ArrayList<>();
        List<User> editors = new ArrayList<>();
        List<User> reviewers = new ArrayList<>();

        for (String sa : persistMagazineDto.getScientificAreas()) {
            ScientificArea scientificArea = new ScientificArea();
            scientificArea.setTitle(sa);

            scientificAreas.add(scientificAreaService.save(scientificArea));
        }

        for (String ed : persistMagazineDto.getEditors()) {
            User editor = new User();
            editor.setUsername(ed);

            editors.add(userService.save(editor));
        }

        for (String rev : persistMagazineDto.getReviewers()) {
            User reviewer = new User();
            reviewer.setUsername(rev);

            reviewers.add(userService.save(reviewer));
        }

        magazine.setScientificAreas(scientificAreas);
        magazine.setEditors(editors);
        magazine.setReviewers(reviewers);

        magazineService.save(magazine);
    }
}
