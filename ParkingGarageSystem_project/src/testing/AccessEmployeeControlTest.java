package testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personel.AccessEmployeeControl;
import personel.Admin;
import personel.ParkingAttendant;

import static org.junit.jupiter.api.Assertions.*;

public class AccessEmployeeControlTest {

    AccessEmployeeControl control;
    ParkingAttendant attendant;
    Admin admin;

    @BeforeEach
    public void setup() {
        control = new AccessEmployeeControl();
        attendant = new ParkingAttendant("AttendantName", "paUser", "pass123");
        admin = new Admin("AdminName");
    }

    @Test
    public void testUnregisteredAttendantIsUnauthorized() {
        assertFalse(control.isAuthorized(attendant));
    }

    @Test
    public void testRegisterAttendantGrantsAccess() {
        control.registerAttendants(attendant);
        assertTrue(control.isAuthorized(attendant));
    }

    @Test
    public void testUnregisteredAdminIsUnauthorized() {
        assertFalse(control.isAuthorized(admin));
    }

    @Test
    public void testRegisterAdminGrantsAccess() {
        control.registerAdmin(admin);
        assertTrue(control.isAuthorized(admin));
    }
}
