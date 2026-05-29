package testing;

import exception.FixAdapter;
import exception.ParkingExceptions;
import modules.ParkingGarage;
import personel.AccessEmployeeControl;
import personel.Admin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FixAdapterTest {

    @Test
    public void testIsLevelOutOfBoundsReturnsTrueIfBelowRange() throws ParkingExceptions {
        ParkingGarage garage = new ParkingGarage(3, 5);
        boolean result = FixAdapter.isLevelOutOfBounds(0, garage);
        assertTrue(result);
    }

    @Test
    public void testIsLevelOutOfBoundsReturnsTrueIfAboveRange() throws ParkingExceptions {
        ParkingGarage garage = new ParkingGarage(3, 5);
        boolean result = FixAdapter.isLevelOutOfBounds(5, garage);
        assertTrue(result);
    }

    @Test
    public void testIsLevelOutOfBoundsReturnsFalseIfWithinRange() throws ParkingExceptions {
        ParkingGarage garage = new ParkingGarage(3, 5);
        boolean result = FixAdapter.isLevelOutOfBounds(2, garage);
        assertFalse(result);
    }

    @Test
    public void testUnAuthorizedReturnsTrueIfAdminNotAuthorized() {
        AccessEmployeeControl control = new AccessEmployeeControl();
        Admin admin = new Admin("test");
        boolean result = FixAdapter.unAuthorized(control, admin);
        assertTrue(result);
    }

    @Test
    public void testUnAuthorizedReturnsFalseIfAdminIsAuthorized() {
        AccessEmployeeControl control = new AccessEmployeeControl();
        Admin admin = new Admin("test");
        control.registerAdmin(admin);
        boolean result = FixAdapter.unAuthorized(control, admin);
        assertFalse(result);
    }
}
