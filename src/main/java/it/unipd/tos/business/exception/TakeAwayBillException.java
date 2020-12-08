////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Exception {

  private static final long serialVersionUID = -6738592842440983810L; // Generato autmaticamente
  private final String msg;

  public TakeAwayBillException(String error) {
    msg = error;
  }

  public String toString() {
    return "TakeAwayBillException thrown: ".concat(msg);
  }
}
