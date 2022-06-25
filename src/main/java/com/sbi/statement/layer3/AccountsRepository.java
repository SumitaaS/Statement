package com.sbi.statement.layer3;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sbi.statement.layer2.Accounts;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Integer> {

}
