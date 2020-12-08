////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unipd.tos.business.exception.TakeAwayBillException;

public class TakeAwayBillExceptionTest {
  @Test
  public void testExceptionMessage() {
    TakeAwayBillException exception = new TakeAwayBillException("error");
    assertEquals("TakeAwayBillException thrown: error", exception.toString());
  }
}
