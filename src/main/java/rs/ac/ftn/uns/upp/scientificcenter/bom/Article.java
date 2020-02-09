package rs.ac.ftn.uns.upp.scientificcenter.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String coauthor;

    private String abstractText;

    private String keywords;

    private String scientificArea;

    private String pdf;

    private Boolean active;

    private String doi;
}
