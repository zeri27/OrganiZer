package app.zeri.organizer.service;

import app.zeri.organizer.domain.Company;
import app.zeri.organizer.domain.repository.CompanyRepository;
import app.zeri.organizer.exceptions.CompanyAlreadyExistsException;
import app.zeri.organizer.exceptions.CompanyDoesNotExistException;
import app.zeri.organizer.exceptions.ProjectAlreadyAssignedToCompany;
import app.zeri.organizer.exceptions.UserAlreadyAssignedToCompany;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final transient CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void registerCompany(Company company) throws CompanyAlreadyExistsException {
        if(!companyRepository.existsCompanyByCompanyName(company.getCompanyName())) {
            companyRepository.save(company);
        } else {
            throw new CompanyAlreadyExistsException();
        }
    }

    public void registerUserToCompany(String emailAddress, String companyName) throws CompanyDoesNotExistException, UserAlreadyAssignedToCompany {
        if(companyRepository.existsCompanyByCompanyName(companyName)) {
            Company company = companyRepository.getCompanyByCompanyName(companyName);
            if(!company.getUsers().contains(emailAddress)) {
                company.addUserToCompany(emailAddress);
                companyRepository.save(company);
            } else {
                throw new UserAlreadyAssignedToCompany();
            }
        } else {
            throw new CompanyDoesNotExistException();
        }
    }

    public void registerProjectToCompany(String projectName, String companyName) throws CompanyDoesNotExistException, ProjectAlreadyAssignedToCompany {
        if(companyRepository.existsCompanyByCompanyName(companyName)) {
            Company company = companyRepository.getCompanyByCompanyName(companyName);
            if(!company.getProjects().contains(projectName)) {
                company.addProjectToCompany(projectName);
                companyRepository.save(company);
            } else {
                throw new ProjectAlreadyAssignedToCompany();
            }
        } else {
            throw new CompanyDoesNotExistException();
        }
    }
}
