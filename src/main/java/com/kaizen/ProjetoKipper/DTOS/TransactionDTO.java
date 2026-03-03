package com.kaizen.ProjetoKipper.DTOS;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId ) {
}
