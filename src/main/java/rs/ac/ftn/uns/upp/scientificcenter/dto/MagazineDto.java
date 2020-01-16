package rs.ac.ftn.uns.upp.scientificcenter.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class MagazineDto {
    private String issn;

    private String title;

    private Integer number;

    private Date date;

    private String paymentMethod;
}
