package rs.ac.ftn.uns.upp.scientificcenter.bom;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String academicTitle;

    private String city;

    private String state;

    private String username;

    private String password;

    private Boolean reviewer;
}
