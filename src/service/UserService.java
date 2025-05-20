package service;

import dao.UserDao;
import dao.UserDaoImpl;
import exception.UserNotFoundException;
import model.User;

public class UserService {

    UserDao userDao = new UserDaoImpl();
    public boolean registerUser(User user){

       return userDao.registerUser(user);

    }

    public User loginUser(String username, String password) throws UserNotFoundException {

        boolean userExists = userDao.loginUser(username, password);
        if(userExists){
            return userDao.getUserByUsername(username);
        }
        else{
            throw new UserNotFoundException("User not found");
        }

    }



}
