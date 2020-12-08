////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.List;

import it.unipd.tos.model.exception.DiscountException;

public interface Discount {

  public double getDiscount(List<MenuItem> items) throws DiscountException;

}
