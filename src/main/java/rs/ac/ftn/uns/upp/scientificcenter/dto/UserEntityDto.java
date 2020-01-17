package rs.ac.ftn.uns.upp.scientificcenter.dto;

import lombok.Data;

@Data
public class UserEntityDto {
    private String name;

    private String surname;

    private String academicTitle;

    private String city;

    private String state;

    private String username;

    private String password;

    private String confirmReviewer;
}
