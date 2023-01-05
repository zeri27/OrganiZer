package app.zeri.organizer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Table(name = "Projects")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String projectName;
    private String companyName;
    private String projectDescription;
    private int tasks = 0;
    @ElementCollection
    private List<String> usersAssigned = new ArrayList<>();
    @ElementCollection
    private List<String> taskNames = new ArrayList<>();

    public void assignUserToProjct(String emailAddress) {
        usersAssigned.add(emailAddress);
    }

    public void addTaskToProject(String taskName) {
        taskNames.add(taskName);
        tasks++;
    }
}
