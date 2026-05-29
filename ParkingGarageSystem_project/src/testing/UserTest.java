package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personel.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    User user;

    @BeforeEach
    public void setup() {
        user = new User("testUser", "pass123") {};
    }

    @Test
    public void testCheckCredentialsReturnsTrueForValidInput() {
        assertTrue(user.checkCredentials("testUser", "pass123"));
    }

    @Test
    public void testCheckCredentialsReturnsFalseForInvalidUsername() {
        assertFalse(user.checkCredentials("wrongUser", "pass123"));
    }

    @Test
    public void testCheckCredentialsReturnsFalseForInvalidPassword() {
        assertFalse(user.checkCredentials("testUser", "wrongpass"));
    }

    @Test
    public void testGetUsernameReturnsCorrectValue() {
        assertEquals("testUser", user.getUsername());
    }
}
