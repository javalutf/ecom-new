package com.application.ecom_applicationNew.repository;

import com.application.ecom_applicationNew.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
