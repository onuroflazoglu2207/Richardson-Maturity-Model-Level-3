package com.api.restful.service;

import com.api.restful.model.UserModel;
import com.api.restful.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceImpl {

    private final UserRepository repository;


    @Override
    public List<UserModel> getAll() {
        return repository.findAll();
    }

    @Override
    public UserModel getById(Long identity) {
        Optional<UserModel> optional = repository.findById(identity);
        return optional.isEmpty() ? null : optional.get();
    }

    @Override
    public UserModel save(UserModel model) {
        try {
            if (getById(model.getIdentity()) != null) return null;
            return repository.save(model);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserModel update(Long identity, UserModel model) {
        UserModel temp = getById(identity);
        if (temp == null) return null;
        delete(identity);
        UserModel temp2 = save(model);
        if (temp2 == null) save(temp);
        return temp2;
    }

    @Override
    public UserModel delete(Long identity) {
        UserModel model = getById(identity);
        if (model != null) repository.deleteById(identity);
        return model;
    }
}
