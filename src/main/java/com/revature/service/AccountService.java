package com.revature.service;

import com.revature.model.Account;
import com.revature.repository.AccountDaoPostgres;
import java.util.Scanner;
import com.revature.controller.AccountController;

public class AccountService {
  private Account account;
  private AccountController controle = new AccountController();
  private AccountDaoPostgres postgress;


  public AccountService(Account account, AccountController controle, AccountDaoPostgres postgress) {
    this.account = account;
    this.controle = controle;
    this.postgress = postgress;
  }
  public AccountService() {
  }
Scanner  input= new Scanner(System.in);
//  Account displayBalance(int id) {
//
//
//
//    return postgress.get(id);
//  }

  // void withdraw (double amount) {
  // account.balance= balance-amount;
  // }
  public static Double Withdrawal(Account customer, Double amount) {

    if (customer.getBalance() - amount < 0.0) {
        System.out.println("\n" + amount + " dollars have been withdrawn.");
        customer.setBalance((customer.getBalance() - amount));
    }
     System.out.println("Your account balance is " + customer.getBalance());
    return customer.getBalance();
  }

  void deposit(Account customer, Double amount) {
    
    customer.setBalance((customer.getBalance() + amount));
    System.out.println("Your new account balance is " + customer.getBalance());
  }

  public void register() {
    
    System.out.println("Please enter your name");
    String name = input.next();
    System.out.println("Please enter your username");
    String username = input.next();
    System.out.println("Please enter your password");
    int password = input.nextInt();
    System.out.println("you enter your initial balance");
    double balance = input.nextInt();
    postgress.save(name,username,password,balance);
    controle.transactionCHoices();
  }

  public void longIn() {
  System.out.println("Please enter your username");
  String username="";
  username=input.next();
  if (username!= null) {
  System.out.println("hi"+username+"Welcome to Revature Bank");
  controle.transactionCHoices();
  }else {
    System.out.println("Sorry! your Username is not in our record please try again or register for a new account");
    controle.promptCHoices();  }
  
  
  }

  void longOut() {
    System.out.println("Thanks for Banking with us. Have a nice day");
  }

  void trasfer() {

  }
}
