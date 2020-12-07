////////////////////////////////////////////////////////////////////
// Damiano Zanardo 1193216
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.unipd.tos.model.User;

public class UserTest {

  User user;

  String name;
  int age;

  @Before
  public void setup() {
    name = "Damiano";
    age = 21;

    user = new User(name, age);
  }

  @Test
  public void testGetName() {
    assertEquals(name, user.getName());
  }

  @Test
  public void testkGetAge() {
    assertEquals(age, user.getAge());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNameNull() {
    new User(null, age);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAgeNegative() {
    new User(name, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNameEmpty() {
    new User("", age);
  }

  @Test
  public void testUserUnder18() {
    user = new User(name, 17);
    assertTrue(user.isUnder18());
  }

  @Test
  public void testUserIs18() {
    user = new User(name, 18);
    assertFalse(user.isUnder18());
  }

  @Test
  public void testUserOver18() {
    user = new User(name, 19);
    assertFalse(user.isUnder18());
  }

  @Test
  public void testEqualsItSelf() {
    assertTrue(user.equals(user));
  }

  @Test
  public void testEqualsUser() {
    User user2 = new User(name, age);
    assertTrue(user.equals(user2) && user2.equals(user));
  }

  @Test
  public void testDifferentName() {
    User user2 = new User(name.concat("str"), age);
    assertFalse(user.equals(user2));
  }

  @Test
  public void testDifferentAge() {
    User user2 = new User(name, age + 1);
    assertFalse(user.equals(user2));
  }

  @Test
  public void testDifferentUser() {
    User user2 = new User(name.concat("str"), age + 1);
    assertFalse(user.equals(user2));
  }

  @Test
  public void testDifferentFromNull() {
    assertFalse(user.equals(null));
  }

}
