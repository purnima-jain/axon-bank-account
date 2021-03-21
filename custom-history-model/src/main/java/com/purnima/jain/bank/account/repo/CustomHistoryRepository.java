package com.purnima.jain.bank.account.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.purnima.jain.bank.account.entity.CustomBankAccountHistoryEntity;

@Repository
public interface CustomHistoryRepository extends JpaRepository<CustomBankAccountHistoryEntity, Long> {

	public List<CustomBankAccountHistoryEntity> findByAccountNumberOrderById(String accountNumber);

}
