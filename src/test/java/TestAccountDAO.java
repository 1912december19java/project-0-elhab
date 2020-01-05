import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.Account;
import com.revature.repository.AccountDaoPostgres;
import com.revature.service.AccountService;

public class TestAccountDAO {
  Account acc = new Account();
  AccountDaoPostgres accp = new AccountDaoPostgres();
  @Before
  public void setUp() {
    Account acc = new Account(1, "hassan", "elec", "eld33", 450);
  }

  @Test
  public void test_AccountServiceGetnameReturnsAName() {

    assertTrue(AccountService.display("elec").getName() == "hassan");
  }

  @Test
  public void test_DaoPosgresGetnameReturnsAName() {

    assertTrue(accp.getName("elec").getName().equalsIgnoreCase("hassan"));

  }

  @Test
  public void test_DaoPosgresGetnameReturnsObject() {

    assertTrue(accp.getName("elec").getName().equals(acc));
  }


  @After
  public void tearDown() {
    acc = null;
  }

}
