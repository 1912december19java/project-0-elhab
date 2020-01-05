package com.revature.service;

import com.revature.model.Account;
import com.revature.repository.AccountDaoPostgres;
import Main.Main;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.controller.AccountController;
import com.revature.exception.DataBaseNotFoundException;
import com.revature.exception.InvalidInputException;

public class AccountService {
  private static Logger log = Logger.getLogger(Main.class);
  private static Account account = new Account();
  private static AccountController controle = new AccountController();
  private AccountDaoPostgres postgress = new AccountDaoPostgres();
  // = new AccountDaoPostgres ();


  public AccountService(Account account, AccountController controle, AccountDaoPostgres postgress) {
    this.account = account;
    this.controle = controle;
    this.postgress = postgress;
  }

  public AccountService() {}

  Scanner input = new Scanner(System.in);

  // deposit an amount less or equal to the balance
  public void withdraw(String username) {
    try {
    System.out.println("what is the amount you want to withdraw");
    
    Scanner input2 = new Scanner(System.in);
    double amount = input2.nextDouble();
    if (amount<=0) {throw new InputMismatchException(); }
    double balance = postgress.getName(username).getBalance();
    double entry = 0;
    entry = balance - amount;
    double newBalance = entry;

    if (newBalance > 0) {

      postgress.updateBalance(newBalance, username);
      System.out.println("Your new balance is " + newBalance);
      System.out.println("Anything else you wanna do");
      controle.transactionCHoices(username);
    } else {
      entry = 0;
      newBalance = 0;
      System.out.println("You only have:" + balance + " try again");
      withdraw(username);
    } }
    catch (InputMismatchException e) {
      System.out.println("please enter a valid amount");
       deposit(username);
    }
  }

  // deposit an amount to the account
  
  public void deposit(String username) {
    try {
    System.out.println("what is the amount you want to deposit");
    double amount = 0;
    double newBalance=0;
    
    Scanner input2 = new Scanner(System.in);
    amount = input2.nextDouble();
     if (amount<=0) {throw new InputMismatchException(); }
    double balance = postgress.getName(username).getBalance();
     newBalance = amount + balance;
    postgress.updateBalance(newBalance, username);
    
    System.out.println("Your new balance is" + newBalance);

    System.out.println("thanks for depsoiting your money, anything else you wanna do");
    controle.transactionCHoices(username);
    }
    catch (InputMismatchException e) {
      System.out.println("please enter a valid amount");
       deposit(username);
    }
  }

  // get the costumer to register.
  public void register() {

    System.out.println("Please enter your name");
    String name = input.nextLine();
    System.out.println("Please enter your username");
    String username = input.next();
    System.out.println("Please enter your password");
    // int password = input.nextInt();
    String password = input.next();
    System.out.println("you enter your initial balance");
    double balance = input.nextDouble();
    postgress.save(name, username, password, balance);
    System.out.println("Thanks for choosing our Bank. Anything else you wanna do");
    controle.transactionCHoices(username);
  }

  // get the customer to log-in.
  public void longIn() {
    AccountDaoPostgres post = new AccountDaoPostgres();

    System.out.println("Please enter your username");
    String username = "";
    String password = "";

    username = input.next();
    
    if (post.getName(username) != null) {
      do {
        System.out.println("Please enter a valid your password");
        password = input.next();
      } while (post.getName(username).getPassword().equals(password) == false);

      System.out.println("hi  " + post.getName(username).getName() + "  Welcome to Revature Bank");
      controle.transactionCHoices(username);
    } else {
      try {
        System.out.println( "Sorry! your Username is not in our record please try again or register for a new account");
        controle.promptCHoices();
      } catch (Exception e) {
        log.error("invalid username", e);
        System.out.println("Invalid Input");
        
      }
    } }

  // let costumer exit from the account
  public void logOut(String username) {
    System.out.println("Thank you " + postgress.getName(username).getName()
        + " for Banking with us. Have a nice day");
    System.exit(0);
  }

  // transfer the amount from the costumer to another costumer
  public void transfer(String username) throws DataBaseNotFoundException {
    System.out.println("Please specify to whom you want to transfer the money");
    String receiver = "";
    receiver = input.next();
    double balance = 0;
    double receiverOriginBalance = 0;
    try {
      receiverOriginBalance = postgress.getName(receiver).getBalance();

    } catch (Exception e) {
      log.error("this userName " + username + " doesnot exist in our database");
      System.out.println("Please enter a valid username");
      transfer(username);
    }
    balance = postgress.getName(username).getBalance();
    System.out.println("Please specify the amount you want to transfer the money");
    double amount = input.nextDouble();



    double entry = 0;
    entry = balance - amount;
    double newBalance = entry;

    if (newBalance > 0) {

      double receiverBalance = receiverOriginBalance + amount;
      postgress.updateBalance(newBalance, username);
      postgress.updateBalance(receiverBalance, receiver);
      System.out.println("Your transfer is done and your new balance is " + newBalance);
      System.out.println("Anything else you wanna do");
      controle.transactionCHoices(username);
    } else {
      entry = 0;
      newBalance = 0;
      System.out.println("You only have:" + balance
          + " press 1 to try a less amount or 2 to return to main menue or 3 to log-out");
      int choice = 0;
      choice = input.nextInt();
      if (choice == 1)
        transfer(username);
      else if (choice == 2)
        controle.transactionCHoices(username);
      else
        logOut(username);
    }
  }

  // display the balance for the costumer
  public static Account display(String username) {
    AccountDaoPostgres post = new AccountDaoPostgres();
    System.out.println("Your balance is  " + post.getName(username).getBalance());
    System.out.println("Anything else you wanna do ?");
    controle.transactionCHoices(username);
 return post.getName(username);
  }

}
