////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.MenuItem.ItemType;

public class MenuItemTest {

  MenuItem item;

  ItemType type;
  String name;
  double price;

  @Before
  public void setup() {
    type = ItemType.GELATO;
    name = "Gelato al pistacchio";
    price = 12.99;

    item = new MenuItem(type, name, price);
  }

  @Test
  public void testGetType() {
    assertEquals(type, item.getType());
  }

  @Test
  public void testGetName() {
    assertEquals(name, item.getName());
  }

  @Test
  public void testGetPrice() {
    assertEquals(price, item.getPrice(), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testTypeNull() {
    new MenuItem(null, name, price);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNameNull() {
    new MenuItem(type, null, price);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPriceNegative() {
    new MenuItem(type, name, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNameEmpty() {
    new MenuItem(type, "", price);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetTimeNull() {
    item.setTime(null);
  }

  @Test
  public void testGetTime() {
    LocalTime time = LocalTime.of(18, 45, 0);
    item.setTime(time);

    assertEquals(time, item.getTime());
  }

  @Test
  public void testIsDuringWinningTime() {
    LocalTime time = LocalTime.of(18, 45, 0);
    item.setTime(time);

    assertTrue(item.isDuringWinningTime());
  }

  @Test
  public void testIsNotDuringWinningTime() {
    LocalTime time = LocalTime.of(17, 45, 0);
    item.setTime(time);

    assertFalse(item.isDuringWinningTime());
  }
}
