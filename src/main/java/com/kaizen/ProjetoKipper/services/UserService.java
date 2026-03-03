package com.kaizen.ProjetoKipper.services;

import com.kaizen.ProjetoKipper.Users.User;
import com.kaizen.ProjetoKipper.Users.UserType;
import com.kaizen.ProjetoKipper.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount ) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("USUARIO NAO PERMITIDO PARA REALIZAR TRANSACAO");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("SALDO INSUFICIENTE");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow (() -> new Exception("USUARIO NAO ENCONTRADO"));
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
