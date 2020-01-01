package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
//import java.sql.*;
import com.revature.model.Account;

public class AccountDaoPostgres {
  private static Logger log= Logger.getLogger(AccountDaoPostgres.class);

  private static Connection conn;
  private static String protocol = "jdbc:postgresql://";

  static {
    try {
      conn = DriverManager.getConnection(
          protocol + System.getenv("connHost") + ":5432/" + System.getenv("dbname"),
          System.getenv("username"), 
          System.getenv("password")
          
      );
      log.info("Connected to database");
//    conn = DriverManager.getConnection(
//        "jdbc:postgresql://database-1.ctt3qcstw0da.us-east-1.rds.amazonaws.com:5432/postgres",
//        "postgres",
//        "");

    } catch (SQLException e) {
      log.error("failed to connect to database",e);
//      e.printStackTrace();
    }
  }


//  public Account get(int id) {
//    Account act = null;
//    ResultSet rs = null;
//
//    try {
//      PreparedStatement stm = conn.prepareStatement("SELECT * FROM account WHERE id = ?");
//      stm.setInt(1, id);
//      if (stm.execute()) {
//        rs = stm.getResultSet();
//      }
//      while (rs.next()) {
//        act = new Account(rs.getInt("id"), rs.getString("name"), rs.getString("username"),
//            rs.getInt("password"), rs.getDouble("balance"));
//      }
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//
//    return act;
//  }
  public Account getName(String username) {
    Account act = null;
    ResultSet rs = null;

    try {
      PreparedStatement stm = conn.prepareStatement("SELECT * FROM account WHERE name = ?");
      stm.setString(1, username);
      if (stm.execute()) {
        rs = stm.getResultSet();
      }
      while (rs.next()) {
        act = new Account(rs.getInt("id"), rs.getString("name"), rs.getString("username"),
            rs.getInt("password"), rs.getDouble("balance"));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return act;
  }

  public void save(String name, String username, int password, double balance) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO account(name, username, password, balance) VALUES (?,?,?,?)");
      stmt.setString(1, name);
      stmt.setString(2,username);
      stmt.setInt(3,password);
      stmt.setDouble(4,balance);

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateBalance(Account account) {
    PreparedStatement stmt = null;
    try {
      stmt = conn.prepareStatement("UPDATE accounts SET balance = ?,  WHERE id = ?");

      stmt.setDouble(1, account.getBalance());
      stmt.setInt(2, account.getId());

      stmt.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }



}
