package app.zeri.organizer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Tasks")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private int taskNumber;
    private String taskName;
    private String taskDescription;
    private String dateAdded;
    private String deadline;
    private String projectName;
    private String companyName;
    @ElementCollection
    private List<String> usersAssigned = new ArrayList<>();

    public void assignUser(String userEmailAddress) {
        usersAssigned.add(userEmailAddress);
    }
}
