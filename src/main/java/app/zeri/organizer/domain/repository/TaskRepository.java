package app.zeri.organizer.domain.repository;

import app.zeri.organizer.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task getTaskByTaskNameAndProjectNameAndCompanyName(String taskName, String projectName, String companyName);

    boolean existsTaskByTaskNameAndProjectNameAndCompanyName(String taskName, String projectName, String companyName);
}
