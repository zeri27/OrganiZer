package app.zeri.organizer.service;

import app.zeri.organizer.domain.Project;
import app.zeri.organizer.domain.repository.ProjectRepository;
import app.zeri.organizer.exceptions.*;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final transient ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void registerProject(Project project) throws ProjectAlreadyAssignedToCompany {
        if(!projectRepository.existsProjectByProjectNameAndCompanyName(project.getProjectName(), project.getCompanyName())) {
            projectRepository.save(project);
        } else {
            throw new ProjectAlreadyAssignedToCompany();
        }
    }

    public void assignUserToProject(String userEmailAddress, String projectName, String companyName) throws ProjectDoesNotExistException, UserAlreadyAssignedToProject {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            Project project = projectRepository.getProjectByProjectNameAndCompanyName(projectName, companyName);
            if(!project.getUsersAssigned().contains(userEmailAddress)) {
                project.assignUserToProject(userEmailAddress);
                projectRepository.save(project);
            } else {
                throw new UserAlreadyAssignedToProject();
            }
        } else {
            throw new ProjectDoesNotExistException();
        }
    }

    public void addTaskToProject(String taskName, String projectName, String companyName) throws ProjectDoesNotExistException, TaskAlreadyAddedToProject {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            Project project = projectRepository.getProjectByProjectNameAndCompanyName(projectName, companyName);
            if(!project.getTaskNames().contains(taskName)) {
                project.addTaskToProject(taskName);
            } else {
                throw new TaskAlreadyAddedToProject();
            }
        } else {
            throw new ProjectDoesNotExistException();
        }
    }

    public boolean projectExists(String projectName, String companyName) throws ProjectDoesNotExistException {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            return true;
        } else {
            throw new ProjectDoesNotExistException();
        }
    }

    public int getCurrentTaskNumber(String projectName, String companyName) throws ProjectDoesNotExistException {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            Project project = projectRepository.getProjectByProjectNameAndCompanyName(projectName, companyName);
            return project.getTasks();
        } else {
            throw new ProjectDoesNotExistException();
        }
    }

    public boolean userAssignedToProject(String emailAddress, String projectName, String companyName) throws UserNotAssignedToProjectException, ProjectDoesNotExistException {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            Project project = projectRepository.getProjectByProjectNameAndCompanyName(projectName, companyName);
            if(project.getUsersAssigned().contains(emailAddress)) {
                return true;
            } else {
                throw new UserNotAssignedToProjectException();
            }
        } else {
            throw new ProjectDoesNotExistException();
        }
    }

    public boolean taskAssignedToProject(String taskName, String projectName, String companyName) throws ProjectDoesNotExistException, TaskDoesNotExistException {
        if(projectRepository.existsProjectByProjectNameAndCompanyName(projectName, companyName)) {
            Project project = projectRepository.getProjectByProjectNameAndCompanyName(projectName, companyName);
            if(project.getTaskNames().contains(taskName)) {
                return true;
            } else {
                throw new TaskDoesNotExistException();
            }
        } else {
            throw new ProjectDoesNotExistException();
        }
    }
}
