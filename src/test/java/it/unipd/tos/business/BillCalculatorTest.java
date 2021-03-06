////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.model.MenuItem.ItemType;

public class BillCalculatorTest {

  List<MenuItem> listItems;
  User user;
  BillCalculator bill;

  @Before
  public void setup() {
    user = new User("_name", 21);

    listItems = new ArrayList<MenuItem>();
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    listItems.add(new MenuItem(ItemType.BUDINO, "Budino al pistacchio", 14.99));
    listItems.add(new MenuItem(ItemType.BEVANDA, "Bevanda al pistacchio", 16.99));

    bill = new BillCalculator();
  }

  @Test
  public void testGetOrderPrice() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(listItems, user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListNull() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(null, user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListEmpty() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(new ArrayList<MenuItem>(), user), 0.001);
  }

  @Test(expected = TakeAwayBillException.class)
  public void testMax30Item() throws TakeAwayBillException {
    for (int i = 0; i < 30; i++) {
      listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    }
    assertEquals(434.67, bill.getOrderPrice(listItems, user), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUserNull() throws TakeAwayBillException {
    assertEquals(44.97, bill.getOrderPrice(listItems, null), 0.001);
  }

  @Test
  public void testDiscount5Gelati() throws TakeAwayBillException {
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cioccolato", 5.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato alla fragola", 2.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al limone", 7.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cocco", 3.99));

    // SOLO SCONTO 5 GELATI
    // assertEquals(77.42, bill.getOrderPrice(listItems, user), 0.01);

    assertEquals(69.53, bill.getOrderPrice(listItems, user), 0.01);
  }

  @Test
  public void testCommssion50Cent() throws TakeAwayBillException {
    listItems = new ArrayList<MenuItem>();
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99));
    listItems.add(new MenuItem(ItemType.BUDINO, "Budino al pistacchio", 1.99));
    listItems.add(new MenuItem(ItemType.BEVANDA, "Bevanda al pistacchio", 1.99));

    assertEquals(6.47, bill.getOrderPrice(listItems, user), 0.01);
  }

  @Test
  public void testIsFree() {
    MenuItem item = new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99);
    item.setTime(LocalTime.of(18, 45, 0));

    user = new User("_name1", 17);

    assertTrue(bill.isFree(item, user));
  }

  @Test
  public void testIsNotFreeBecauseTime() {
    MenuItem item = new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99);
    item.setTime(LocalTime.of(19, 45, 0));

    user = new User("_name1", 17);

    assertFalse(bill.isFree(item, user));
  }

  @Test
  public void testIsNotFreeBecauseSfiga() {
    MenuItem item = new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99);
    item.setTime(LocalTime.of(18, 45, 0));

    user = new User("_name", 17);

    assertFalse(bill.isFree(item, user));
  }

  @Test
  public void testIsNotFreeBecauseTooOld() {
    MenuItem item = new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99);
    item.setTime(LocalTime.of(18, 45, 0));

    user = new User("_name1", 21);

    assertFalse(bill.isFree(item, user));
  }

  @Test
  public void testIsNotFreeBecauseTooOldAndSfiga() {
    MenuItem item = new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 1.99);
    item.setTime(LocalTime.of(18, 45, 0));

    user = new User("_name1", 18);

    assertFalse(bill.isFree(item, user));
  }

  @Test
  public void test1Winner() throws TakeAwayBillException {
    listItems.get(listItems.size() - 1).setTime(LocalTime.of(18, 45, 0));
    user = new User("_name1", 17);
    assertEquals(0, bill.getOrderPrice(listItems, user), 0.01);
  }

  @Test
  public void test10Winner() throws TakeAwayBillException {
    listItems.get(listItems.size() - 1).setTime(LocalTime.of(18, 45, 0));

    for (int i = 0; i < 10; i++) {
      user = new User("_name1", 17);
      assertEquals(0, bill.getOrderPrice(listItems, user), 0.01);
    }
  }

  @Test
  public void test10WinnerAnd11thNotWinner() throws TakeAwayBillException {
    listItems.get(listItems.size() - 1).setTime(LocalTime.of(18, 45, 0));

    for (int i = 0; i < 10; i++) {
      user = new User("_name1", 17);
      assertEquals(0, bill.getOrderPrice(listItems, user), 0.01);
    }

    user = new User("_name1", 17);
    assertEquals(44.97, bill.getOrderPrice(listItems, user), 0.01);
  }
}
