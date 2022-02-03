package com.api.restful.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class UserModel extends RepresentationModel<UserModel> {

    @Id
    @NotNull(message = "Identity cannot be null!")
    @Min(value = 10000000000L, message = "Identity length must be 11!")
    @Max(value = 99999999999L, message = "Identity length must be 11!")
    @Column(name = "identity")
    Long identity;

    @NotBlank(message = "Name cannot be blank!")
    @Column(name = "name")
    String name;

    @NotNull(message = "Birthday cannot be null!")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = ISO.DATE)
    @Column(name = "birthday")
    LocalDate birthday;

    @NotNull(message = "Gender cannot be null!")
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @NotBlank(message = "Password cannot be blank!")
    @Column(name = "password")
    String password;
}
