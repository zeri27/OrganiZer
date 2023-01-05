package app.zeri.organizer.domain.repository;

import app.zeri.organizer.domain.Company;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Company getCompanyByCompanyName(String companyName);

    boolean existsCompanyByCompanyName(String companyName);
}
