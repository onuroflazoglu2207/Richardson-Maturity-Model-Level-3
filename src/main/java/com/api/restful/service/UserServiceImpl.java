package com.api.restful.service;

import com.api.restful.model.UserModel;

import java.util.List;

public interface UserServiceImpl {
    public List<UserModel> getAll();

    public UserModel getById(Long identity);

    public UserModel save(UserModel model);

    public UserModel update(Long identity, UserModel model);

    public UserModel delete(Long identity);
}
