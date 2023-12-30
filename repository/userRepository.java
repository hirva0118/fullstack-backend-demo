package com.CodingWithHirva.fullstack.repository;
import java.util.List;

import com.CodingWithHirva.fullstack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<User,Long> {

    public interface UserRepository {
        List<User> getAllUsers();
    }
}
