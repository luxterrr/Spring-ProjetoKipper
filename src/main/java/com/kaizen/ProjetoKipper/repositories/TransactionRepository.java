package com.kaizen.ProjetoKipper.repositories;

import com.kaizen.ProjetoKipper.Domains.Transacao.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
