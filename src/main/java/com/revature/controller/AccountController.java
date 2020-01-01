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
  public int promptCHoices() {
    int userInput = 0;
    System.out.println("Press 1 to login or 2 to register");
    do {
    userInput = sc.nextInt();
    AccountService accountService = new AccountService();
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
  public int transactionCHoices() {
    int userInput = 0;
    System.out.println("Press 1 to display the balance, or 2 to withdraw, 3 to deposit, or 4 to log out");
    do {
    userInput = sc.nextInt();
    if(userInput<=4&&userInput>0)
      break;
      System.out.println("Please enter a validate number"); 
    }while(true);
    return userInput;
  }
 

//  Account displayBlance() {
//    return account;
//  }

}
