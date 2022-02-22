package com.example.multipledatasources.service.impl;

import com.example.multipledatasources.entities.mysqlrepo.User;
import com.example.multipledatasources.entities.posgrerepo.Product;
import com.example.multipledatasources.repository.dbmysql.UserRespository;
import com.example.multipledatasources.service.GenericService;
import com.example.multipledatasources.service.ProductService;
import com.example.multipledatasources.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class UserServiceImpl implements UserService {

    private UserRespository respository;

    @Autowired
    public UserServiceImpl(UserRespository respository) {
        this.respository = respository;
    }

    @Override
    public List<User> findAll() {
        return respository.findAll();
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return respository.save(user);
    }

    @Override
    public User update(User user) {
        return respository.save(user);
    }

    @Override
    public int delete(User user) {
        respository.delete(user);
        return 1;
    }

    @Override
    public int deleteAll(List<User> list) {
        respository.deleteAll(list);
        return list.size();
    }
}
