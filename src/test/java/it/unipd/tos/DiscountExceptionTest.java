////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.unipd.tos.model.exception.DiscountException;

public class DiscountExceptionTest {
  @Test
  public void testExceptionMessage() {
    DiscountException exception = new DiscountException("error");
    assertEquals("DiscountException thrown: error", exception.toString());
  }
}
