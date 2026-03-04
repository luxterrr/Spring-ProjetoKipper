package com.kaizen.ProjetoKipper.Domains.Users;


import com.kaizen.ProjetoKipper.DTOS.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode (of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private String password;

    public User(UserDTO data){
        this.firstName = data.firstName();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.password = data.password();
        this.document = data.document();
        this.email = data.email();
        this.userType = data.userType();
    }

}
