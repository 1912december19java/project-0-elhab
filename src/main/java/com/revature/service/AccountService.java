package com.revature.service;

import com.revature.model.Account;
import com.revature.repository.AccountDaoPostgres;
import java.util.Scanner;
import com.revature.controller.AccountController;

public class AccountService {
  private Account account= new Account();
  private AccountController controle = new AccountController();
  private AccountDaoPostgres postgress= new AccountDaoPostgres();
//  = new AccountDaoPostgres ();


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

  public void withdraw (String username) {
    System.out.println("what is the amount you want to withdraw");
    double amount=input.nextDouble() ;
    double balance=postgress.getName(username).getBalance();
    double entry=0;
    entry=balance-amount;
    double newBalance= entry;
    
    if(newBalance>0) {
      
     postgress.updateBalance(newBalance, username);
     System.out.println("Your new balance is "+newBalance);
     System.out.println("Anything else you wanna do");
     controle.transactionCHoices(username);
    }else {
      entry=0;
      newBalance=0;
      System.out.println("You only have:"+balance+" try again");
      withdraw (username);
    }
   }
  public void deposit(String username) {
    System.out.println("what is the amount you want to deposit");
    double amount=input.nextDouble() ;
    double balance=postgress.getName(username).getBalance();
    double newBalance= amount+balance;
    postgress.updateBalance(newBalance, username);
  System.out.println("Your new balance is"+newBalance);
    
    System.out.println("thanks for depsoiting your money, anything else you wanna do");
    controle.transactionCHoices(username);
  }

  public void register() {
//    System.out.println("Please enter your id");
//    int id = input.nextInt();
    System.out.println("Please enter your name");
    String name = input.nextLine();
    System.out.println("Please enter your username");
    String username = input.next();
    System.out.println("Please enter your password");
    //int password = input.nextInt();
    String password=input.next();
    System.out.println("you enter your initial balance");
    double balance = input.nextDouble();
    postgress.save(name,username,password,balance);
    System.out.println("Thanks for choosing our Bank. Anything else you wanna do");
    controle.transactionCHoices(username);
  }

  public void longIn() {
    AccountDaoPostgres post = new AccountDaoPostgres ();
    
  System.out.println("Please enter your username");
  String username="";
  String password="";

  username=input.next();
//  System.out.println(post.getName(username));
//  System.out.println(post.getName(username).getBalance());
  if (post.getName(username).getUsername()!= null) {
    do {
    System.out.println("Please enter a valid your password");
    password=input.next();
  }
   while (post.getName(username).getPassword().equals(password)== false);
    
  System.out.println("hi  "+post.getName(username).getName()+"  Welcome to Revature Bank");
  controle.transactionCHoices(username);
  }else {
    System.out.println("Sorry! your Username is not in our record please try again or register for a new account");
    controle.promptCHoices();  }
  
  
  }

  public void logOut(String username) {
    System.out.println("Thank you "+username+" for Banking with us. Have a nice day");
    System.exit(0);
  }

 public void transfer(String username) {
    System.out.println("Please specify to whom you want to transfer the money");
    String receiver="";
    receiver=input.next();
    System.out.println("Please specify the amount you want to transfer the money");
    double amount=input.nextDouble() ;
    double balance=postgress.getName(username).getBalance();
    double entry=0;
    entry=balance-amount;
    double newBalance= entry;
    
    if(newBalance>0) {
      double receiverBalance=postgress.getName(receiver).getBalance()+amount; 
     postgress.updateBalance(newBalance, username);
     postgress.updateBalance(receiverBalance, receiver);
     System.out.println("Your transfer is done and your new balance is "+newBalance);
     System.out.println("Anything else you wanna do");
     controle.transactionCHoices(username);
    }else {
      entry=0;
      newBalance=0;
      System.out.println("You only have:"+balance+" press 1 to try a less amount or 2 to return to main menue or 3 to log-out");
      int choice=0;
      choice=input.nextInt();
      if (choice==1)
      transfer( username);else if (choice==2) controle.transactionCHoices(username);else logOut(username);
    }
  }
  public void display(String username) {
    AccountDaoPostgres post = new AccountDaoPostgres ();
    System.out.println("Your balance is  "+post.getName(username).getBalance());
    System.out.println("Anything else you wanna do ?");
    controle.transactionCHoices(username);
    
  }
}
