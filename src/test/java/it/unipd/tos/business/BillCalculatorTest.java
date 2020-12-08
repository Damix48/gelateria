////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.ItemType;

public class BillCalculatorTest {

  List<MenuItem> listItem;
  User user;
  BillCalculator bill;

  @Before
  public void setup() {
    user = new User("_name", 21);

    listItem = new ArrayList<MenuItem>();
    listItem.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    listItem.add(new MenuItem(ItemType.BUDINO, "Budino al pistacchio", 14.99));
    listItem.add(new MenuItem(ItemType.BEVANDA, "Bevanda al pistacchio", 16.99));

    bill = new BillCalculator();
  }

  @Test
  public void testGetOrderPrice() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(listItem, user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListNull() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(null, user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListEmpty() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(new ArrayList<MenuItem>(), user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUserNull() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(listItem, null), 0.001);
  }
}
