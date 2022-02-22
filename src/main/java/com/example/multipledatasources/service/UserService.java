package com.example.multipledatasources.service;

import com.example.multipledatasources.entities.mysqlrepo.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save( User t);

    User update(User t);

    int delete(User t);

    int deleteAll(List<User> list);

}
