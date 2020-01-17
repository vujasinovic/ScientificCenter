package rs.ac.ftn.uns.upp.scientificcenter.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class PersistMagazineDto {
    private String issn;

    private String title;

    private Integer number;

    private Date date;

    private String paymentMethod;

    private List<String> scientificAreas;

    private List<String> editors;

    private List<String> reviewers;

    private String mainEditor;
}
