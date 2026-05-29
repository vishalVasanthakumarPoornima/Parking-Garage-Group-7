package testing;

import modules.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    public void testConstructorInitializesNameAndContactInfo() {
        Customer customer = new Customer("Jane Doe", "jane.doe@example.com");
        assertEquals("Jane Doe", customer.getName());
        assertEquals("jane.doe@example.com", customer.getContactInfo());
    }

    @Test
    public void testEnterGarageSetsEntryTime() {
        Customer customer = new Customer("John Doe", "john@example.com");
        assertNull(customer.getEntryTime());
        customer.enterGarage();
        assertNotNull(customer.getEntryTime());
    }

    @Test
    public void testExitGarageSetsExitTime() throws InterruptedException {
        Customer customer = new Customer("Amy Smith", "amy@example.com");
        customer.enterGarage();
        Thread.sleep(10);
        customer.exitGarage();
        assertNotNull(customer.getExitTime());
        assertTrue(customer.getExitTime().isAfter(customer.getEntryTime()));
    }
}
