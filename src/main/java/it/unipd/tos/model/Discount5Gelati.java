////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.List;

import it.unipd.tos.model.MenuItem.ItemType;
import it.unipd.tos.model.exception.DiscountException;

public class Discount5Gelati implements Discount {

  public double getDiscount(List<MenuItem> items) throws DiscountException, IllegalArgumentException {

    if (items == null) {
      throw new IllegalArgumentException("La lista non può essere nulla");
    }

    if (items.size() < 5) {
      throw new IllegalArgumentException("La lista non può avere meno di 5 elementi");
    }

    int nGelati = 0;
    double minGelato = 0;

    for (MenuItem item : items) {
      if (item.getType() == ItemType.GELATO) {
        if (nGelati == 0) {
          minGelato = item.getPrice();
        }

        if (minGelato > item.getPrice()) {
          minGelato = item.getPrice();
        }

        nGelati++;
      }
    }

    if (nGelati >= 5) {
      return minGelato * 0.5;
    }

    throw new DiscountException("Sconto non applicabile, pochi gelati");
  }

}
