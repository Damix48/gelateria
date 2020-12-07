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
  void setup() {
    name = "Damiano";
    age = 21;

    user = new User(name, age);
  }

  @Test
  void testGetName() {
    assertEquals(name, user.getName());
  }

  @Test
  void testkGetAge() {
    assertEquals(age, user.getAge());
  }

  @Test(expected = IllegalArgumentException.class)
  void testNameNull() {
    new User(null, age);
  }

  @Test(expected = IllegalArgumentException.class)
  void testAgeNull() {
    new User(name, null);
  }

  @Test(expected = IllegalArgumentException.class)
  void testNameAgeNull() {
    new User(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  void testAgeNegative() {
    new User(name, -1);
  }

  @Test(expected = IllegalAccessException.class)
  void testNameEmpty() {
    new User("", age);
  }

  @Test
  void testUserUnder18() {
    user = new User(name, 17);
    assertTrue(user.isUnder18());
  }

  @Test
  void testUserIs18() {
    user = new User(name, 18);
    assertFalse(user.isUnder18());
  }

  @Test
  void testUserOver18() {
    user = new User(name, 19);
    assertFalse(user.isUnder18());
  }

  @Test
  void testEqualsItSelf() {
    assertTrue(user.equals(user));
  }

  @Test
  void testEqualsUser() {
    User user2 = new User(name, age);
    assertTrue(user.equals(user2) && user2.equals(user));
  }

  @Test
  void testDifferentName() {
    User user2 = new User(name.concat("str"), age);
    assertFalse(user.equals(user2));
  }

  @Test
  void testDifferentAge() {
    User user2 = new User(name, age + 1);
    assertFalse(user.equals(user2));
  }

  @Test
  void testDifferentUser() {
    User user2 = new User(name.concat("str"), age + 1);
    assertFalse(user.equals(user2));
  }

  @Test
  void testDifferentFromNull() {
    assertFalse(user.equals(null));
  }

}
