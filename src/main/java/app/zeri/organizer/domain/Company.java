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
@Table(name = "Company")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "companyId")
    private int id;
    private String companyName;
    private String companyDescription;
    @ElementCollection
    private List<String> users = new ArrayList<>();
    @ElementCollection
    private List<String> projects = new ArrayList<>();

    public Company(String companyName, String companyDescription) {
        this.companyName = companyName;
        this.companyDescription = companyDescription;
    }

    public void addUserToCompany(String userEmailAddress) {
        users.add(userEmailAddress);
    }

    public void addProjectToCompany(String projectName) {
        projects.add(projectName);
    }
}
