package app.zeri.organizer.controller;

import app.zeri.organizer.domain.Company;
import app.zeri.organizer.domain.Project;
import app.zeri.organizer.domain.Task;
import app.zeri.organizer.domain.User;
import app.zeri.organizer.domain.repository.TaskRepository;
import app.zeri.organizer.exceptions.*;
import app.zeri.organizer.models.*;
import app.zeri.organizer.service.CompanyService;
import app.zeri.organizer.service.ProjectService;
import app.zeri.organizer.service.TaskService;
import app.zeri.organizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
@RequestMapping("/organizer")
public class OrganiZerController {

    private final transient UserService userService;
    private final transient CompanyService companyService;
    private final transient ProjectService projectService;
    private final transient TaskService taskService;
    private final TaskRepository taskRepository;

    @Autowired
    public OrganiZerController(
            UserService userService,
            CompanyService companyService,
            ProjectService projectService,
            TaskService taskService,
            TaskRepository taskRepository) {
        this.userService = userService;
        this.companyService = companyService;
        this.projectService = projectService;
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody UserModel userModel) {
        try {
            User user = new User(
                    userModel.getFirstName(),
                    userModel.getLastName(),
                    userModel.getEmailAddress(),
                    userModel.getPassword());
            userService.registerUser(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/registerCompany")
    public ResponseEntity<Company> registerCompany(@RequestBody CompanyModel companyModel) {
        try {
            Company company = new Company(companyModel.getCompanyName(), companyModel.getCompanyDescription());
            companyService.registerCompany(company);
            return ResponseEntity.ok(company);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/registerProject")
    public ResponseEntity<Project> registerProject(@RequestBody ProjectModel projectModel) {
        try {
            if(companyService.companyExists(projectModel.getCompanyName())) {
                Project project = new Project(
                        projectModel.getProjectName(),
                        projectModel.getProjectDescription(),
                        projectModel.getCompanyName());
                projectService.registerProject(project);
                companyService.registerProjectToCompany(project.getProjectName(), project.getCompanyName());
                return ResponseEntity.ok(project);
            } else {
                throw new CompanyDoesNotExistException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/createTask")
    public ResponseEntity<Task> createTask(@RequestBody TaskModel taskModel) {
        try {
            if(projectService.projectExists(taskModel.getProjectName(), taskModel.getCompanyName())) {
                int taskNumber = projectService.getCurrentTaskNumber(taskModel.getProjectName(), taskModel.getCompanyName());
                String dateAdded = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                Task task = new Task(
                        taskNumber,
                        taskModel.getTaskName(),
                        taskModel.getTaskDescription(),
                        dateAdded,
                        taskModel.getDeadline(),
                        taskModel.getProjectName(),
                        taskModel.getCompanyName()
                );
                projectService.addTaskToProject(task.getTaskName(), task.getProjectName(), task.getCompanyName());
                taskService.createNewTask(task);
                return ResponseEntity.ok(task);
            } else {
                throw new ProjectDoesNotExistException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/login")
    public void authenticateUser(@RequestBody AuthenticateUserModel userModel) {
        try {
            userService.authenticateUser(userModel.getEmailAddress(), userModel.getPassword());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/logout")
    public void deAuthenticateUser(@RequestBody LogOutModel logOutModel) {
        try {
            userService.deAuthenticateUser(logOutModel.getEmailAddress());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/assignCompany")
    public void assignCompanyToUser(@RequestBody UserCompanyModel userCompanyModel) {
        try {
            if(companyService.companyExists(userCompanyModel.getCompanyName()) && userService.userExists(userCompanyModel.getEmailAddress())) {
                userService.assignUserToCompany(userCompanyModel.getEmailAddress(), userCompanyModel.getCompanyName());
                companyService.registerUserToCompany(userCompanyModel.getEmailAddress(), userCompanyModel.getCompanyName());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/assignProject")
    public void assignProjectToUser(@RequestBody UserProjectModel userProjectModel) {
        try {
            if(projectUserExistenceCheck(
                    userProjectModel.getEmailAddress(),
                    userProjectModel.getProjectName(),
                    userProjectModel.getCompanyName())) {
                if(userService.userAllocatedToCompany(
                        userProjectModel.getEmailAddress(),
                        userProjectModel.getCompanyName())) {
                    userService.assignUserToProject(
                            userProjectModel.getEmailAddress(),
                            userProjectModel.getProjectName());
                    projectService.assignUserToProject(
                            userProjectModel.getEmailAddress(),
                            userProjectModel.getProjectName(),
                            userProjectModel.getCompanyName());
                }
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/assignTask")
    public void assignTaskToUser(@RequestBody TaskUserModel taskUserModel) {
        try {
            if(taskUserExistenceCheck(
                    taskUserModel.getEmailAddress(),
                    taskUserModel.getTaskName(),
                    taskUserModel.getProjectName(),
                    taskUserModel.getCompanyName())) {
                taskService.assignUserToTask(
                        taskUserModel.getEmailAddress(),
                        taskUserModel.getTaskName(),
                        taskUserModel.getProjectName(),
                        taskUserModel.getCompanyName());
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public boolean taskUserExistenceCheck(String emailAddress, String taskName, String projectName, String companyNamne) {
        try {
            if(userService.userExists(emailAddress)) {
                if(projectService.userAssignedToProject(emailAddress, projectName, companyNamne)) {
                    if(projectService.taskAssignedToProject(taskName, projectName, companyNamne)) {
                        return true;
                    } else {
                        throw new TaskDoesNotExistException();
                    }
                } else {
                    throw new UserNotAssignedToProjectException();
                }
            } else {
                throw new UserDoesNotExistException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public boolean projectUserExistenceCheck(String emailAddress, String projectName, String companyName) {
        try {
            if(userService.userExists(emailAddress)) {
                if(projectService.projectExists(projectName, companyName)) {
                    return true;
                } else {
                    throw new ProjectDoesNotExistException();
                }
            } else {
                throw new UserDoesNotExistException();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
