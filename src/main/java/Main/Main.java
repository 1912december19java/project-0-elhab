package Main;

import org.apache.log4j.Logger;
import com.revature.controller.*;
import com.revature.exception.InvalidInputException;
import com.revature.model.*;
import com.revature.repository.*;
import com.revature.service.*;

public class Main {
  private static Logger log = Logger.getLogger(Main.class);

  public static void main(String[] args) throws InvalidInputException {
    // starting the program
    log.trace("starting");
    AccountController controle = new AccountController();
    try {
      controle.promptCHoices();
    } catch (NumberFormatException ne) {
      log.error("not a valid number", ne);
      System.out.println("Invalid Input");
    }

  }

}
