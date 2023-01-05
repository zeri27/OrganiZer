package app.zeri.organizer.service;

import app.zeri.organizer.domain.Task;
import app.zeri.organizer.domain.repository.TaskRepository;
import app.zeri.organizer.exceptions.TaskAlreadyAddedToProject;
import app.zeri.organizer.exceptions.TaskDoesNotExistException;
import app.zeri.organizer.exceptions.UserAlreadyAssignedToTask;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final transient TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createNewTask(Task task) throws TaskAlreadyAddedToProject {
        if(!taskRepository.existsTaskByTaskNameAndProjectNameAndCompanyName(task.getTaskName(), task.getProjectName(), task.getCompanyName())) {
            taskRepository.save(task);
        } else {
            throw new TaskAlreadyAddedToProject();
        }
    }

    public void assignUserToTask(String userEmailAddress, String taskName, String projectName, String companyName) throws TaskDoesNotExistException, UserAlreadyAssignedToTask {
        if(taskRepository.existsTaskByTaskNameAndProjectNameAndCompanyName(taskName, projectName, companyName)) {
            Task task = taskRepository.getTaskByTaskNameAndProjectNameAndCompanyName(taskName, projectName, companyName);
            if(!task.getUsersAssigned().contains(userEmailAddress)) {
                task.assignUser(userEmailAddress);
                taskRepository.save(task);
            } else {
                throw new UserAlreadyAssignedToTask();
            }
        } else {
            throw new TaskDoesNotExistException();
        }
    }
}
