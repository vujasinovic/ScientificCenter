package rs.ac.ftn.uns.upp.scintificcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskDto {
    private String id;

    private String name;

    private String assignee;
}
