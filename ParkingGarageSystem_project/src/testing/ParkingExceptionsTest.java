package testing;

import exception.ParkingExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingExceptionsTest {

    @Test
    public void testConstructorWithErrorNumberSetsCorrectMessage() {
        ParkingExceptions ex = new ParkingExceptions(1);
        assertEquals(1, ex.getErrorno());
        assertEquals("File doesn't exist. Replacing with known file", ex.getErrormsg());
    }

    @Test
    public void testConstructorWithMessageOnly() {
        ParkingExceptions ex = new ParkingExceptions("Custom error");
        assertEquals("Custom error", ex.getErrormsg());
    }

    @Test
    public void testFixMethodForKnownErrorCode() {
        ParkingExceptions ex = new ParkingExceptions();
        ex.fix(3);
        assertEquals("Invalid or already used ticket.", ex.getErrormsg());
    }

    @Test
    public void testWritemyproblemFormatsCorrectly() {
        ParkingExceptions ex = new ParkingExceptions(5);
        String message = ex.writemyproblem();
        assertTrue(message.contains("errorno= 5"));
        assertTrue(message.contains("Unauthorized user attempt on an admin operation."));
    }

    @Test
    public void testSetAndGetErrorNumber() {
        ParkingExceptions ex = new ParkingExceptions();
        ex.setErrorno(6);
        assertEquals(6, ex.getErrorno());
    }

    @Test
    public void testSetAndGetErrorMessage() {
        ParkingExceptions ex = new ParkingExceptions();
        ex.setErrormsg("Test error");
        assertEquals("Test error", ex.getErrormsg());
    }

    @Test
    public void testConstructorWithExceptionDoesNotThrow() {
        Exception e = new Exception("Wrapped");
        ParkingExceptions ex = new ParkingExceptions(e);
        assertNotNull(ex);
    }

    @Test
    public void testConstructorWithCloneExceptionDoesNotThrow() {
        CloneNotSupportedException e = new CloneNotSupportedException("Clone");
        ParkingExceptions ex = new ParkingExceptions(e);
        assertNotNull(ex);
    }
}
