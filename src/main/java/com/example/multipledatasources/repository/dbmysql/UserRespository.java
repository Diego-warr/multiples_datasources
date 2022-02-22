package com.example.multipledatasources.repository.dbmysql;

import com.example.multipledatasources.entities.mysqlrepo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Integer> {
}
