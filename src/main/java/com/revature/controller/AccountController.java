package com.revature.controller;

import java.util.Scanner;
import org.apache.log4j.Logger;
import com.revature.exception.InvalidInputException;
import com.revature.model.Account;
import com.revature.service.AccountService;
import Main.Main;


public class AccountController {
  private static Logger log = Logger.getLogger(Main.class);

  static Scanner sc = new Scanner(System.in);

  // first prompt and get the user to log-in or register a new Account
  // public AccountController() {
  // }

  public  int promptCHoices() throws InvalidInputException {
    AccountService accountService = new AccountService();

    int userInput = 0;
    System.out.println("Press 1 to login or 2 to register");

    do {

      try {
        // convert the string read from the scanner into Integer type
        userInput = Integer.parseInt(sc.nextLine());

      } catch (NumberFormatException ne) {
        System.out.println("Invalid Input");
      }


      if (userInput == 1)
        accountService.longIn();

      else if (userInput == 2) {
        accountService.register();
      } else
        System.out.println("Please enter a validate number: 1 to login or 2 to register");
    } while (userInput != 1 && userInput != 2);
    return userInput;
  }


  // get the customer to choose a transaction or log-out
  public void transactionCHoices(String username) {
    AccountService accountService = new AccountService();
    int userInput = 0;
    System.out.println(
        "Press 1 to display the balance, or 2 to withdraw, 3 to deposit,4 to trasfer money or 5 to log out");


    // convert the string read from the scanner into Integer type
    userInput = Integer.parseInt(sc.nextLine());


    if (userInput < 6 & userInput > 0) {
      if (userInput == 1) {
        accountService.display(username);

      } else if (userInput == 2) {
        accountService.withdraw(username);
      } else if (userInput == 3) {
        accountService.deposit(username);
      } else if (userInput == 5) {
        accountService.logOut(username);
      } else if (userInput == 4) {
        try {
          accountService.transfer(username);
        } catch (Exception e) {
          log.error("this userName " + username + " doesnot exist in our database");

        }
      }
    } else
      transactionCHoices(username);
  }



}
