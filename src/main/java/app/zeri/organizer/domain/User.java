package app.zeri.organizer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    @ElementCollection
    private List<String> companies = new ArrayList<>();
    @ElementCollection
    private List<String> projects = new ArrayList<>();

    public void assignProject(String project) {
        this.projects.add(project);
    }

    public void assignCompany(String companyName) {
        this.companies.add(companyName);
    }
}
