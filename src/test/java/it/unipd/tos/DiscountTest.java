////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.Discount5Gelati;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.ItemType;
import it.unipd.tos.model.exception.DiscountException;

public class DiscountTest {

  Discount5Gelati discount;

  public List<MenuItem> listItems;

  @Before
  public void setup() {

    discount = new Discount5Gelati();

    listItems = new ArrayList<MenuItem>();

    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al pistacchio", 12.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cioccolato", 5.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato alla fragola", 2.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al limone", 7.99));
    listItems.add(new MenuItem(ItemType.GELATO, "Gelato al cocco", 3.99));
  }

  @Test
  public void testGetDiscount() throws DiscountException {
    assertEquals(1.50, discount.getDiscount(listItems), 0.01);
    assertEquals(1.49, discount.getDiscount(listItems), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testListNull() throws DiscountException {
    assertEquals(1.50, discount.getDiscount(null), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotEnoughItems() throws DiscountException {
    listItems.remove(0);
    assertEquals(1.50, discount.getDiscount(listItems), 0.01);
  }

  @Test(expected = DiscountException.class)
  public void testNotEnoughGelati() throws DiscountException {
    listItems.remove(0);
    listItems.add(new MenuItem(ItemType.BEVANDA, "_name", 100.99));
    assertEquals(1.50, discount.getDiscount(listItems), 0.01);
  }

}
