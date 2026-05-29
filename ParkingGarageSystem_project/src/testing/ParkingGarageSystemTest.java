package testing;

import adapter.ParkingGarageSystem;
import modules.Ticket;
import personel.Admin;
import personel.ParkingAttendant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingGarageSystemTest {

    static class TestParkingGarageSystem extends ParkingGarageSystem {}
    TestParkingGarageSystem system;

    @BeforeEach
    void setup() {
        system = new TestParkingGarageSystem();
        ParkingGarageSystem.getActiveTicketsMap().clear();
        ParkingGarageSystem.getAttendantsList().clear();
        ParkingGarageSystem.getAdminsList().clear();
    }

    @Test
    public void testValidateExitWithActiveTicket() {
        Ticket ticket = new Ticket();
        Integer id = Integer.valueOf(ticket.getTicketID());
        ParkingGarageSystem.getActiveTicketsMap().put(id, ticket);
        assertFalse(system.validateExit(id));
    }

    @Test
    public void testValidateExitWithRemovedTicket() {
        Integer id = 123456;
        assertTrue(system.validateExit(id));
    }

    @Test
    public void testGetActiveTicketIDsReflectsAddedTickets() {
        Ticket t1 = new Ticket();
        Ticket t2 = new Ticket();
        ParkingGarageSystem.getActiveTicketsMap().put(Integer.valueOf(t1.getTicketID()), t1);
        ParkingGarageSystem.getActiveTicketsMap().put(Integer.valueOf(t2.getTicketID()), t2);
        Set<Integer> ids = system.getActiveTicketIDs();
        assertEquals(2, ids.size());
        assertTrue(ids.contains(Integer.valueOf(t1.getTicketID())));
        assertTrue(ids.contains(Integer.valueOf(t2.getTicketID())));
    }

    @Test
    public void testAddParkingAttendantRegistersAndStores() {
        int before = ParkingGarageSystem.getAttendantsList().size();
        ParkingAttendant pa = new ParkingAttendant("John Doe", "john123", "pass123");
        system.addParkingAttendent(pa);
        int after = ParkingGarageSystem.getAttendantsList().size();
        assertEquals(before + 1, after);
        assertTrue(ParkingGarageSystem.getAttendantsList().contains(pa));
    }

    @Test
    public void testAddAdminRegistersAndStores() {
        int before = ParkingGarageSystem.getAdminsList().size();
        Admin admin = new Admin("Alice Doe");
        system.addAdmin(admin);
        int after = ParkingGarageSystem.getAdminsList().size();
        assertEquals(before + 1, after);
        assertTrue(ParkingGarageSystem.getAdminsList().contains(admin));
    }

    @Test
    public void testValidateUserCredentialsReturnsTrueForValidUser() {
        Admin admin = new Admin("ValidAdmin");
        system.addAdmin(admin);
        assertTrue(system.validateUserCredentials(admin.getAdminID(), "admin123"));
    }

    @Test
    public void testValidateUserCredentialsReturnsFalseForInvalidUser() {
        assertFalse(system.validateUserCredentials("invalidUser", "wrongpass"));
    }
}
