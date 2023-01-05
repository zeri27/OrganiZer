package app.zeri.organizer.domain.repository;

import app.zeri.organizer.domain.Project;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project getProjectByProjectNameAndCompanyName(String projectName, String companyName);

    boolean existsProjectByProjectNameAndCompanyName(String projectName, String companyName);
}
