////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import java.time.LocalTime;
import java.util.List;
import java.util.Random;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.Discount50Euro;
import it.unipd.tos.model.Discount5Gelati;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

public class BillCalculator implements TakeAwayBill {
  private double total;
  private int regali = 10;

  public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
      throws TakeAwayBillException, IllegalArgumentException {

    if (itemsOrdered == null) {
      throw new IllegalArgumentException("La lista non può essere nulla");
    }
    if (itemsOrdered.isEmpty()) {
      throw new IllegalArgumentException("La lista non può essere vuota");
    }
    if (itemsOrdered.size() > 30) {
      throw new TakeAwayBillException("Non si possono ordinare più di 30 item");
    }
    if (user == null) {
      throw new IllegalArgumentException("L'utente non può essere nullo");
    }

    for (MenuItem item : itemsOrdered) {
      total += item.getPrice();
    }

    try {
      // ASSUMO CHE GLI SCONTI SIANO CUMULABILI SUL TOTALE NON SCONTATO
      Discount5Gelati discount5Gelati = new Discount5Gelati();
      total -= discount5Gelati.getDiscount(itemsOrdered);

      Discount50Euro discount50Euro = new Discount50Euro();
      total -= discount50Euro.getDiscount(itemsOrdered);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // LA COMMISSIONE VIENE AGGIUNTA DOPO IL CALOLO DEGLI SCONTI
    if (total < 10) {
      total += 0.5;
    }

    MenuItem last = itemsOrdered.get(itemsOrdered.size() - 1);

    if (isFree(last, user)) {
      total = 0;
      regali--;
    }

    return total;
  }

  public boolean isFree(MenuItem item, User user) {
    return item.isDuringWinningTime() && user.isWinner() && regali > 0;
  }
}
