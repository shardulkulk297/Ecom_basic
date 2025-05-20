package dao;

import model.User;

public interface UserDao {

    public boolean registerUser(User user);
    public boolean loginUser(String username, String password);
    public User getUserByUsername(String username);
    public User getUserById(int userId);

}
