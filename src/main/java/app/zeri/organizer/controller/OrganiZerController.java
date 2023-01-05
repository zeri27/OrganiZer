package app.zeri.organizer.controller;

import app.zeri.organizer.domain.User;
import app.zeri.organizer.models.UserModel;
import app.zeri.organizer.service.CompanyService;
import app.zeri.organizer.service.ProjectService;
import app.zeri.organizer.service.TaskService;
import app.zeri.organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/organizer")
public class OrganiZerController {

    private final transient UserService userService;
    private final transient CompanyService companyService;
    private final transient ProjectService projectService;
    private final transient TaskService taskService;

    @Autowired
    public OrganiZerController(
            UserService userService,
            CompanyService companyService,
            ProjectService projectService,
            TaskService taskService) {
        this.userService = userService;
        this.companyService = companyService;
        this.projectService = projectService;
        this.taskService = taskService;
    }

    public ResponseEntity<User> createNewUser(@RequestBody UserModel userModel) {
        try {
            return null;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
