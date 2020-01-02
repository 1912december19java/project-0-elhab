package com.revature.controller;

import java.util.Scanner;
import com.revature.model.Account;
import com.revature.service.AccountService;


public class AccountController {
//  private Account account;
//  private AccountService accountService;
//  
//  public AccountController(Account account, AccountService accountService) {
//    this.account=account;
//    this.accountService=accountService;
//  }
//  public Account getAccount() {
//    return account;
//  }
//
//  public void setAccount(Account account) {
//    this.account = account;
//  }
  static Scanner sc = new Scanner(System.in);

  //first prompt and get the user to log-in or register a new Account
  public AccountController() {
  }
  public int promptCHoices() {
    AccountService accountService = new AccountService();
    int userInput = 0;
    System.out.println("Press 1 to login or 2 to register");
    do {
    userInput = sc.nextInt();
   
    if(userInput==1 ) 
      accountService.longIn();
    
    else if (userInput==2 ) {
      accountService.register();
    }
    else
      System.out.println("Please enter a validate number: 1 to login or 2 to register"); 
    }while(userInput!=1 && userInput!=2);
    return userInput;
  }

  // get the customer to choose a transaction or log-out 
  public void transactionCHoices(String username) {
    AccountService accountService = new AccountService();
    
    System.out.println("Press 1 to display the balance, or 2 to withdraw, 3 to deposit,4 to trasfer money or 5 to log out");
    
    int userInput = sc.nextInt();
    if(userInput<6 & userInput>0) {
    if(userInput==1) {  
      accountService.display(username);
      
    }else if (userInput == 2) {
    accountService.withdraw(username);}
    else if(userInput == 3) {
      accountService.deposit(username);
    }else if (userInput == 5) {
      accountService.logOut(username);
    }else if (userInput == 4){
      accountService.transfer(username);
    }
    }else transactionCHoices( username);
  } 
 

//  Account displayBlance() {
//    return account;
//  }

}
