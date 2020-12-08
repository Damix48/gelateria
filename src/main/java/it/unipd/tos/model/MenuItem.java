////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.time.LocalTime;

public class MenuItem {
  public enum ItemType {
    GELATO, BUDINO, BEVANDA;
  }

  private final ItemType type;
  private final String name;
  private final double price;

  public MenuItem(ItemType _type, String _name, double _price) throws IllegalArgumentException {
    if (_type == null) {
      throw new IllegalArgumentException("Il tipo non può essere nullo");
    }
    if (_name == null) {
      throw new IllegalArgumentException("Il nome non può essere nullo");
    }
    if (_price < 0) {
      throw new IllegalArgumentException("Il prezzo non può essere negativo");
    }
    if (_name == "") {
      throw new IllegalArgumentException("Il nome non può essere vuoto");
    }

    type = _type;
    name = _name;
    price = _price;
  }

  public ItemType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

}
