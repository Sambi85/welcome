package com.java.welcome.repository;

import com.java.welcome.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository 
        extends JpaRepository<UserAccount, Long> {

}