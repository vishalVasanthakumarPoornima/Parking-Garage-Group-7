package testing;

import modules.EntranceDisplayBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntranceDisplayBoardTest {

    @Test
    public void testConstructorSetsParkingAvailableTrue() {
        EntranceDisplayBoard display = new EntranceDisplayBoard();
        display.validateCapacity(1);
        assertTrue(getAvailability(display));
    }

    @Test
    public void testValidateCapacitySetsAvailableTrue() {
        EntranceDisplayBoard display = new EntranceDisplayBoard();
        display.validateCapacity(5);
        assertTrue(getAvailability(display));
    }

    @Test
    public void testValidateCapacitySetsAvailableFalse() {
        EntranceDisplayBoard display = new EntranceDisplayBoard();
        display.validateCapacity(0);
        assertFalse(getAvailability(display));
    }

//  Overrode access modifier of isParkingAvailable field to use in testing
    private boolean getAvailability(EntranceDisplayBoard display) {
        try {
            var field = EntranceDisplayBoard.class.getDeclaredField("isParkingAvailable");
            field.setAccessible(true);
            return (boolean) field.get(display);
        } catch (Exception e) {
            fail("Reflection failed");
            return false;
        }
    }
}
