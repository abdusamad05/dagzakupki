package com.folivora.dagzakupki.reps;
import com.folivora.dagzakupki.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRep extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
