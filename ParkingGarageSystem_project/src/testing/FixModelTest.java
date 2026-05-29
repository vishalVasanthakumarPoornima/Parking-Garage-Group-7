package testing;

import exception.FixModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FixModelTest {

    @Test
    public void testIsValidIntegerWithPositiveNumber() {
        assertTrue(FixModel.isValidInteger(10));
    }

    @Test
    public void testIsValidIntegerWithZero() {
        assertTrue(FixModel.isValidInteger(0));
    }

    @Test
    public void testIsValidIntegerWithNegativeNumber() {
        assertFalse(FixModel.isValidInteger(-1));
    }

    @Test
    public void testCheckdataEntryWithInvalidFloat() {
        assertThrows(NumberFormatException.class, () -> {
            FixModel.checkdataEntry("invalid");
        });
    }

    @Test
    public void testCheckdataEntryWithValidFloat() {
        assertFalse(FixModel.checkdataEntry("12.34"));
    }

    @Test
    public void testIsFileValidWithExistingFile() throws IOException {
        File tempFile = File.createTempFile("testfile", ".txt");
        tempFile.deleteOnExit();
        assertTrue(FixModel.isFileValid(tempFile.getAbsolutePath()));
    }

    @Test
    public void testIsFileValidWithNonExistentFile() {
        assertFalse(FixModel.isFileValid("non_existent_file_12345.txt"));
    }

    @Test
    public void testIsParkingAvailableWithAvailableSpots() {
        assertTrue(FixModel.isParkingAvailable(3));
    }

    @Test
    public void testIsParkingAvailableWithNoSpots() {
        assertFalse(FixModel.isParkingAvailable(0));
    }

    @Test
    public void testValidateZipCodeWithValidZip() {
        assertDoesNotThrow(() -> FixModel.validateZipCode(94536));
    }

    @Test
    public void testValidateZipCodeThrowsForShortZip() {
        assertThrows(IllegalArgumentException.class, () -> FixModel.validateZipCode(123));
    }

    @Test
    public void testValidateZipCodeThrowsForNonNumericZip() {
        assertThrows(IllegalArgumentException.class, () -> FixModel.validateZipCode(Integer.parseInt("12a34")));
    }
}
