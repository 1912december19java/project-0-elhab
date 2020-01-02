package Main;

import org.apache.log4j.Logger;
import com.revature.controller.*;
import com.revature.model.*;
import com.revature.repository.*;
import com.revature.service.*;

public class Main {
  private static Logger log =  Logger.getLogger(Main.class);

  public static void main(String[] args) {
//     AccountDaoPostgres sample = new AccountDaoPostgres();
      log.trace("starting");
     AccountController controle = new AccountController(); 
    controle.promptCHoices();
  
//    System.out.println(sample.getName("afkir"));
       

  }

}
