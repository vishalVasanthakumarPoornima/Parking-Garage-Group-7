
## How to Run

### 1. Initialize Garages (First Time Only)
If you are running the system for the first time and need to set up garages:

- Run `Driver.java`  
  This will create garages, parking levels, and admins, and serialize them.

### 2. Start the Server
Once garage data is available:

- Run `ParkingGarageServer.java`  
  This reads the garage object from file and starts the server on port `12345`.

### 3. Start the Client
In a separate terminal or process:

- Run `Client.java`  
  This connects to the server and presents the GUI or CLI to the user.

## Login Credentials
- Admins are created in `Driver.java` and auto-assigned default usernames and passwords.
- Example:
  - Username: `A1` (Admin ID)
  - Password: `adminpass`

- For Parking Attendants (if implemented similarly):
  - Username: `PA1`
  - Password: `password`

## Testing
JUnit 5 tests are provided in the `testing/` directory.

To run all tests:

- Open `AllTests.java`
- Right-click > Run As > JUnit Test (in Eclipse)

Each test targets a specific module (e.g., `DVDTest`, `DVDCollectionTest`) and validates expected behaviors.

## Authors
- Vishal Vasanthakumar Poornima
- Kurt
- Raymond

## Notes
- All data is serialized to the `/log` folder.
- Manual ticket creation and payment handling are available to Parking Attendants.
- GUI implementation may vary depending on deployment (Swing, console-based, etc.).

## License
This project is for academic use only and not intended for commercial deployment.
