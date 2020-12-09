////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.List;

import it.unipd.tos.model.MenuItem.ItemType;
import it.unipd.tos.model.exception.DiscountException;

public class Discount50Euro implements Discount {

  public double getDiscount(List<MenuItem> items) throws DiscountException, IllegalArgumentException {

    if (items == null) {
      throw new IllegalArgumentException("La lista non può essere nulla");
    }

    if (items.isEmpty()) {
      throw new IllegalArgumentException("La lista non può essere vuota");
    }

    double total = 0;
    double sum = 0;

    for (MenuItem item : items) {
      if (item.getType() == ItemType.GELATO || item.getType() == ItemType.BUDINO) {
        sum += item.getPrice();
      }
      total += item.getPrice();
    }

    if (sum >= 50) {
      return total * 0.1;
    }

    throw new DiscountException("Sconto non applicabile, soglia non raggiunta");
  }

}
