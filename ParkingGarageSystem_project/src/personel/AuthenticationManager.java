package personel;

import java.util.ArrayList;

public class AuthenticationManager {
	
    private ArrayList<User> users;

    public AuthenticationManager() {
        users = new ArrayList<>();
    }

    public void registerUser(User user) {
        users.add(user);
    }

    
    public User login(String username, String password) {
        for (User user : users) {
            if (user.checkCredentials(username, password)) {
                return user;
            }
        }
        return null; // login failed
    }
}
