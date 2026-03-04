package com.kaizen.ProjetoKipper.services;

import com.kaizen.ProjetoKipper.DTOS.TransactionDTO;
import com.kaizen.ProjetoKipper.Domains.Transacao.Transaction;
import com.kaizen.ProjetoKipper.Domains.Users.User;
import com.kaizen.ProjetoKipper.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());

        if (!isAuthorized) {
            throw new Exception("Transacao nao autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);

        this.notificationService.sendNotification(sender , "TRANSACAO ENVIADA COM SUCESSO");
        this.notificationService.sendNotification(receiver , "TRANSACAO RECEBIDA COM SUCESSO");

        return newTransaction;
    }

    public boolean authorizeTransaction(User sender, BigDecimal value) {
        try {
            ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://util.devi.tools/api/v2/authorize", Map.class);

            if (authorizationResponse.getStatusCode() == HttpStatus.OK) {
                // Ajuste aqui para o novo formato da API se necessário
                return true;
            }else{ return false;}
        } catch (Exception exception) {
            // Se cair aqui (ex: 403 Forbidden), a transação não é autorizada
            return false;
        }
    }
}
