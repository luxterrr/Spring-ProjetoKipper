package com.kaizen.ProjetoKipper.DTOS;

import com.kaizen.ProjetoKipper.Domains.Users.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
