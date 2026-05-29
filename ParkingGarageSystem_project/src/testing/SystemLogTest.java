package testing;

import modules.SystemLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SystemLogTest {

    @BeforeEach
    public void clearLogs() {
        SystemLog.getAllLogs().clear();
    }

    @Test
    public void testRecordEventWithDetailsAndType() {
        SystemLog.recordEvent("System started", "INFO");
        List<SystemLog> logs = SystemLog.getAllLogs();
        assertEquals(1, logs.size());
        assertEquals("System started", logs.get(0).getEventDetails());
        assertEquals("INFO", logs.get(0).getEventType());
    }

    @Test
    public void testRecordEventWithFullDetails() {
        SystemLog.recordEvent("Gate failure", "ERROR", "U001", "HW01");
        List<SystemLog> logs = SystemLog.getAllLogs();
        assertEquals(1, logs.size());
        assertEquals("Gate failure", logs.get(0).getEventDetails());
        assertEquals("ERROR", logs.get(0).getEventType());
        assertEquals("U001", logs.get(0).getUserID());
        assertEquals("HW01", logs.get(0).getHardwareID());
    }

    @Test
    public void testGetLogsByType() {
        SystemLog.recordEvent("A", "INFO");
        SystemLog.recordEvent("B", "ERROR");
        SystemLog.recordEvent("C", "INFO");
        List<SystemLog> infoLogs = SystemLog.getLogsByType("INFO");
        assertEquals(2, infoLogs.size());
        for (SystemLog log : infoLogs) {
            assertEquals("INFO", log.getEventType());
        }
    }

    @Test
    public void testGetLogsByDateRange() {
        LocalDateTime start = LocalDateTime.now();
        SystemLog.recordEvent("X", "INFO");
        SystemLog.recordEvent("Y", "INFO");
        LocalDateTime end = LocalDateTime.now().plusSeconds(1);
        List<SystemLog> logs = SystemLog.getLogsByDateRange(start.minusSeconds(1), end);
        assertEquals(2, logs.size());
    }

    @Test
    public void testGettersWorkCorrectly() {
        SystemLog.recordEvent("Maintenance check", "INFO", "EMP10", "GATE03");
        SystemLog log = SystemLog.getAllLogs().get(0);
        assertNotNull(log.getLogID());
        assertEquals("Maintenance check", log.getEventDetails());
        assertEquals("INFO", log.getEventType());
        assertEquals("EMP10", log.getUserID());
        assertEquals("GATE03", log.getHardwareID());
        assertNotNull(log.getTimestamp());
    }
}
