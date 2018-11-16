package com.roje.boxf.hall.dao;

import com.roje.boxf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    User getUserByAccount(String account);
}
