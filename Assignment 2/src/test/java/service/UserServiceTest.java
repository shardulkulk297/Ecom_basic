package service;

import exception.UserNotFoundException;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;

    @BeforeEach
    public void init(){
        userService = new UserService();
    }

    @Test
    public void testAddUser() {


        UserNotFoundException e = assertThrows(UserNotFoundException.class, ()->{
            userService.loginUser("test", "test1");
        });
        assertEquals("User not found", e.getMessage());

        assertTrue(userService.registerUser(new User(1, "userr", "user", "Customer")));

    }

}