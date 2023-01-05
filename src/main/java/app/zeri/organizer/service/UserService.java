package app.zeri.organizer.service;

import app.zeri.organizer.domain.User;
import app.zeri.organizer.domain.repository.UserRepository;
import app.zeri.organizer.exceptions.*;
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

    public void authenticateUser(String emailAddress, String password) throws UserDoesNotExistException, UserAuthenticationFailedException {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            User user = userRepository.getUserByEmailAddress(emailAddress);
            if(user.getPassword().equals(password)) {
                user.authenticateUser();
                userRepository.save(user);
            } else {
                throw new UserAuthenticationFailedException();
            }
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public void deAuthenticateUser(String emailAddress) throws UserDoesNotExistException, UserAuthenticationFailedException {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            User user = userRepository.getUserByEmailAddress(emailAddress);
            user.deauthenticateUser();
            userRepository.save(user);
        } else {
            throw new UserDoesNotExistException();
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

    public boolean userExists(String emailAddress) throws UserDoesNotExistException {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            return true;
        } else {
            throw new UserDoesNotExistException();
        }
    }

    public boolean userAllocatedToCompany(String emailAddress, String companyName) throws UserDoesNotExistException, UserNotAssignedToCompanyException {
        if(userRepository.existsByEmailAddress(emailAddress)) {
            User user = userRepository.getUserByEmailAddress(emailAddress);
            if(user.getCompanies().contains(companyName)) {
                return true;
            } else {
                throw new UserNotAssignedToCompanyException();
            }
        } else {
            throw new UserDoesNotExistException();
        }
    }
}
