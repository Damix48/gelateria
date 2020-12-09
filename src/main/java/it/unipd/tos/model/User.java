////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

import java.util.Objects;

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

  @Override
  public boolean equals(Object _object) {
    if (this == _object) {
      return true;
    }

    if (_object == null) {
      return false;
    }

    if (getClass() != _object.getClass()) {
      return false;
    }

    User _user = (User) _object;

    if (!Objects.equals(getName(), _user.getName())) {
      return false;
    }

    return getAge() == _user.getAge();
  }

  @Override
  public int hashCode() {
    return name.hashCode() * 29 + age * 37;
  }

  public boolean isWinner() {
    return this.isUnder18() && this.hashCode() % 4 == 0;
  }
}
