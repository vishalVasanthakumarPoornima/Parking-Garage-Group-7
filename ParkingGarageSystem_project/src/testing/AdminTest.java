package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personel.Admin;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    Admin admin;

    @BeforeEach
    public void setup() {
        admin = new Admin("John");
    }

    @Test
    public void testAdminIDIsGeneratedCorrectly() {
        assertTrue(admin.getAdminID().startsWith("AD"));
    }

    @Test
    public void testGetNameReturnsCorrectName() {
        assertEquals("John", admin.getName());
    }

    @Test
    public void testSetNameChangesName() {
        admin.setName("Alice");
        assertEquals("Alice", admin.getName());
    }

    @Test
    public void testSuccessfulLogin() {
        assertTrue(admin.login(admin.getAdminID(), "admin123"));
    }

    @Test
    public void testFailedLoginWithWrongUsername() {
        assertFalse(admin.login("wrong", "admin123"));
    }

    @Test
    public void testFailedLoginWithWrongPassword() {
        assertFalse(admin.login(admin.getAdminID(), "wrongpass"));
    }
}
