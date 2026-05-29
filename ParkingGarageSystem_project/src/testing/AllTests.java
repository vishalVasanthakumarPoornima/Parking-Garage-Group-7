package testing;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    AccessEmployeeControlTest.class,
    AddressTest.class,
    AdminTest.class,
    AuthenticationManagerTest.class,
    CustomerTest.class,
    EntranceDisplayBoardTest.class,
    EntryKioskTest.class,
    ExitKioskTest.class,
    FixAdapterTest.class,
    FixModelTest.class,
    FixScaleTest.class,
    GateTest.class,
    HardwareTest.class,
    LevelDisplayBoardTest.class,
    ParkingAttendantTest.class,
    ParkingExceptionsTest.class,
    ParkingGarageSystemTest.class,
    ParkingGarageTest.class,
    ParkingLevelTest.class,
    ParkingSpaceTest.class,
    PaymentTest.class,
    SystemLogTest.class,
    TicketTest.class,
    UserTest.class
})
public class AllTests {
}
