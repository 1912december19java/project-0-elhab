package com.revature.repository;

import com.revature.model.Account;

public interface AccountDao {

  
    void save(String name, String username, String password, double balance) ;
  
    Account getName(String username) ;
  
   void updateBalance(double newBalance, String username) ;
  
}
