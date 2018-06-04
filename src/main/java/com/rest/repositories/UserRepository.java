package com.rest.repositories;

import com.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bimal on 2/17/18.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String name);
}
