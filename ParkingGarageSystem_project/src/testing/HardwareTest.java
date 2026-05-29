package testing;

import modules.Hardware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HardwareTest {

    Hardware criticalHardware;
    Hardware nonCriticalHardware;

    @BeforeEach
    public void setup() {
        criticalHardware = new Hardware("CRIT01", "sensor", "Main Entrance", true);
        nonCriticalHardware = new Hardware("NONCRIT01", "light", "Level 3", false);
    }

    @Test
    public void testConstructorInitializesFields() {
        assertEquals("CRIT01", criticalHardware.getDeviceID());
        assertEquals("sensor", criticalHardware.getType());
        assertEquals("Main Entrance", criticalHardware.getLocation());
        assertEquals("active", criticalHardware.getStatus());
        assertNotNull(criticalHardware.getLastChecked());
        assertTrue(criticalHardware.isCritical());
    }

    @Test
    public void testSetStatusUpdatesValue() {
        criticalHardware.setStatus("offline");
        assertEquals("offline", criticalHardware.getStatus());
    }

    @Test
    public void testUpdateLastCheckedChangesTime() {
        LocalDateTime before = criticalHardware.getLastChecked();
        criticalHardware.updateLastChecked();
        LocalDateTime after = criticalHardware.getLastChecked();
        assertTrue(after.isAfter(before) || after.isEqual(before));
    }

    @Test
    public void testPerformHealthCheckOnCriticalOffline() {
        criticalHardware.setStatus("offline");
        assertDoesNotThrow(() -> criticalHardware.performHealthCheck());
    }

    @Test
    public void testPerformHealthCheckOnNonCriticalOffline() {
        nonCriticalHardware.setStatus("offline");
        assertDoesNotThrow(() -> nonCriticalHardware.performHealthCheck());
    }

    @Test
    public void testPerformHealthCheckOnActiveHardware() {
        criticalHardware.setStatus("active");
        assertDoesNotThrow(() -> criticalHardware.performHealthCheck());
    }

    @Test
    public void testToStringIncludesDeviceId() {
        String output = criticalHardware.toString();
        assertTrue(output.contains("CRIT01"));
        assertTrue(output.contains("type='sensor'") || output.contains("sensor"));
    }
}
