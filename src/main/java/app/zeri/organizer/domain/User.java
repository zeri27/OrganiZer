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
    private String password;
    @ElementCollection
    private List<String> companies = new ArrayList<>();
    @ElementCollection
    private List<String> projects = new ArrayList<>();
    private boolean authentication = false;

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public void authenticateUser() {
        this.authentication = true;
    }

    public void deauthenticateUser() {
        this.authentication = false;
    }

    public void assignProject(String project) {
        this.projects.add(project);
    }

    public void assignCompany(String companyName) {
        this.companies.add(companyName);
    }
}
