package com.api.restful.controller;

import com.api.restful.model.UserModel;
import com.api.restful.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    @GetMapping("/getAll")
    public List<UserModel> getAll() {
        List<UserModel> list = service.getAll();
        for (UserModel model : list)
            model = addLinks(model);
        return list;
    }

    @GetMapping("/getById/{identity}")
    public UserModel getById(@PathVariable Long identity) {
        UserModel model = service.getById(identity);
        return model == null ? null : addLinks(model);
    }

    @PostMapping("/save")
    public UserModel save(@RequestBody UserModel model) {
        UserModel model2 = service.save(model);
        return model2 == null ? null : addLinks(model2);
    }

    @PutMapping("/update/{identity}")
    public UserModel update(@PathVariable Long identity, @RequestBody UserModel model) {
        UserModel model2 = service.update(identity, model);
        return model2 == null ? null : addLinks(model2);
    }

    @DeleteMapping("/delete/{identity}")
    public UserModel delete(@PathVariable Long identity) {
        UserModel model = service.delete(identity);
        return model == null ? null : addLinks(model);
    }

    public UserModel addLinks(UserModel model) {
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getAll()).withSelfRel().withType("GET"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getById(model.getIdentity())).withSelfRel().withType("GET"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .save(model)).withSelfRel().withType("POST"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .update(model.getIdentity(), model)).withSelfRel().withType("PUT"));
        model.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .delete(model.getIdentity())).withSelfRel().withType("DELETE"));
        return model;
    }
}
