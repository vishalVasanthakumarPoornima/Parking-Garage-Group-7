package testing;

import modules.Gate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GateTest {

    @Test
    public void testConstructorInitializesClosed() {
        Gate gate = new Gate();
        assertFalse(gate.isOpen());
    }

    @Test
    public void testOpenGateSetsIsOpenTrue() {
        Gate gate = new Gate();
        gate.openGate();
        assertTrue(gate.isOpen());
    }

    @Test
    public void testCloseGateSetsIsOpenFalse() {
        Gate gate = new Gate();
        gate.openGate();
        gate.closeGate();
        assertFalse(gate.isOpen());
    }
}
