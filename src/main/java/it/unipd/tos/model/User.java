////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class User {
  private final String name;
  private final int age;

  public User(String _name, int _age) throws IllegalArgumentException {
    if (_name == null) {
      throw new IllegalArgumentException("Il nome non può essere nullo");
    }
    if (_age < 0) {
      throw new IllegalArgumentException("L'età non può essere negativa");
    }
    if (_name == "") {
      throw new IllegalArgumentException("Il nome non può essere vuoto");
    }

    this.name = _name;
    this.age = _age;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public boolean isUnder18() {
    return age < 18;
  }
}
