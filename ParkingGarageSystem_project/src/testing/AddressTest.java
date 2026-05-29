package testing;

import modules.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    @Test
    public void testConstructorInitializesFieldsCorrectly() {
        Address address = new Address("123 Main St", "Fremont", "CA", "94536");
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Fremont", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("94536", address.getZipcode());
    }

    @Test
    public void testSettersUpdateValues() {
        Address address = new Address("", "", "", "");
        address.setStreet("456 Elm St");
        address.setCity("Hayward");
        address.setState("CA");
        address.setZipcode("94544");

        assertEquals("456 Elm St", address.getStreet());
        assertEquals("Hayward", address.getCity());
        assertEquals("CA", address.getState());
        assertEquals("94544", address.getZipcode());
    }

    @Test
    public void testToStringContainsAllFields() {
        Address address = new Address("789 Oak Ave", "Union City", "CA", "94587");
        String result = address.toString();
        assertTrue(result.contains("Street: 789 Oak Ave"));
        assertTrue(result.contains("City: Union City"));
        assertTrue(result.contains("State: CA"));
        assertTrue(result.contains("Zipcode: 94587"));
    }
}
