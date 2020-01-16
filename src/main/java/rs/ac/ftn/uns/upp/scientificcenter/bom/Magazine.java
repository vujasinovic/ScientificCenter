package rs.ac.ftn.uns.upp.scientificcenter.bom;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Magazine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String issn;

    private String title;

    private Integer number;

    private Date date;

    private String paymentMethod;

    @ManyToMany
    private List<ScientificArea> scientificAreas;
}
