package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personel.Admin;
import personel.AuthenticationManager;
import personel.User;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationManagerTest {

    AuthenticationManager manager;
    Admin admin;

    @BeforeEach
    public void setup() {
        manager = new AuthenticationManager();
        admin = new Admin("Alice");
    }

    @Test
    public void testLoginFailsWhenNoUsersRegistered() {
        assertNull(manager.login("AD1", "admin123"));
    }

    @Test
    public void testLoginSucceedsAfterRegistration() {
        manager.registerUser(admin);
        User result = manager.login(admin.getAdminID(), "admin123");
        assertNotNull(result);
        assertEquals(admin.getAdminID(), result.getUsername());
    }

    @Test
    public void testLoginFailsWithWrongPassword() {
        manager.registerUser(admin);
        assertNull(manager.login(admin.getAdminID(), "wrongpass"));
    }

    @Test
    public void testLoginFailsWithWrongUsername() {
        manager.registerUser(admin);
        assertNull(manager.login("wrong", "admin123"));
    }
}
