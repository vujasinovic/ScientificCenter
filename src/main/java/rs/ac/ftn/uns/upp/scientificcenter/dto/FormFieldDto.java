package rs.ac.ftn.uns.upp.scientificcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldDto {
    private String id;

    private String label;

    private String type;

    private String typeName;

    private List<String> availableValues;
}
