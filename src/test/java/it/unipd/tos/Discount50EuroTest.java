////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.Discount50Euro;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.ItemType;
import it.unipd.tos.model.exception.DiscountException;

public class Discount50EuroTest {

  Discount50Euro discount;

  public List<MenuItem> listItems;

  @Before
  public void setup() {

    discount = new Discount50Euro();

    listItems = new ArrayList<MenuItem>();

    listItems.add(new MenuItem(ItemType.GELATO, "Gelato all'oro", 18.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cioccolato", 5.99));
    listItems.add(new MenuItem(ItemType.BUDINO, "Budino alla fragola", 2.99));
    listItems.add(new MenuItem(ItemType.BUDINO, "Budino al limone", 7.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cocco", 3.99));
    listItems.add(new MenuItem(ItemType.BUDINO, "Budino al lime", 8.99));
    listItems.add(new MenuItem(ItemType.BEVANDA, "Bevanda al gusto di Coca-Cola", 4.99));

  }

  @Test
  public void testGetDiscount() throws DiscountException {
    assertEquals(6.69, discount.getDiscount(listItems), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListNull() throws DiscountException {
    assertEquals(6.69, discount.getDiscount(null), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotEnoughItems() throws DiscountException {
    listItems.removeAll(listItems);
    assertEquals(6.69, discount.getDiscount(listItems), 0.01);
  }

  @Test(expected = DiscountException.class)
  public void testNotEnoughEuro() throws DiscountException {
    listItems.remove(0);
    assertEquals(1.50, discount.getDiscount(listItems), 0.01);
  }
}
