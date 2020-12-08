////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.util.List;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class BillCalculator implements TakeAwayBill {
  private double total;

  public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
      throws TakeAwayBillException, IllegalArgumentException {

    if (itemsOrdered == null) {
      throw new IllegalArgumentException("La lista non può essere nulla");
    }
    if (itemsOrdered.isEmpty()) {
      throw new IllegalArgumentException("La lista non può essere vuota");
    }
    if (user == null) {
      throw new IllegalArgumentException("L'utente non può essere nullo");
    }

    for (MenuItem item : itemsOrdered) {
      total += item.getPrice();
    }

    return total;
  }

}