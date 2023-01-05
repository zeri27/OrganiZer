package app.zeri.organizer.domain.repository;

import app.zeri.organizer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getUserByEmailAddress(String emailAddress);

    boolean existsByEmailAddress(String emailAddress);
}
