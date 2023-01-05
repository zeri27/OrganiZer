package app.zeri.organizer.service;

import app.zeri.organizer.domain.User;
import app.zeri.organizer.domain.repository.UserRepository;
import app.zeri.organizer.exceptions.UserAlreadyAssignedToCompany;
import app.zeri.organizer.exceptions.UserAlreadyAssignedToProject;
import app.zeri.organizer.exceptions.UserAlreadyExistsException;
import app.zeri.organizer.exceptions.UserDoesNotExistException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final transient UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) throws UserAlreadyExistsException {
        if(!userRepository.existsByEmailAddress(user.getEmailAddress())) {
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException();
        }
    }

    public void assignUserToProject(String emailAddress, String projectName) throws UserDoesNotExistException, UserAlreadyAssignedToProject {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            User user = userRepository.getUserByEmailAddress(emailAddress);
            if(!user.getProjects().contains(projectName)) {
                user.assignProject(projectName);
                userRepository.save(user);
            } else {
                throw new UserAlreadyAssignedToProject();
            }
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public void assignUserToCompany(String emailAddress, String companyName) throws UserDoesNotExistException, UserAlreadyAssignedToCompany {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            User user = userRepository.getUserByEmailAddress(emailAddress);
            if(!user.getCompanies().contains(companyName)) {
                user.assignCompany(companyName);
                userRepository.save(user);
            } else {
                throw new UserAlreadyAssignedToCompany();
            }
        } else {
            throw new UserDoesNotExistException();
        }
    }
}
