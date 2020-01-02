package com.revature.model;

public class Account {
  private int id;
  private String name;
  private String username;
  private String password;
  private double balance;
  
  public Account() {}

  public Account(int id, String name, String username,String password, double balance) {
    this.id = id;
    this.name = name;
    this.username = username;
    this.password=password;
    this.balance = balance;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public double getBalance() {
    return balance;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword() {
    return password;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public String toString() {
    return "Your Account:  "+"\n"+" name: " + name +"\n"+ " Your is username: " + username + "\n"+" Your balance: " + balance + "";

  }



  



}
