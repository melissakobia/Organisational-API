package models;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UserTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    public User setupUser () {
        return new User("Mary", "CEO", "Managerial", 3);
    }

    @Test
    public void getUsernameGetsCorrectUsername() throws Exception {
        User newUser = setupUser();
        assertEquals("Mary", newUser.getUsername());
    }

    @Test
    public void getPositionGetsCorrectPosition() throws Exception {
        User newUser = setupUser();
        assertEquals("CEO", newUser.getCompany_position());
    }

    @Test
    public void getRoleGetsCorrectRole() throws Exception {
        User newUser = setupUser();
        assertEquals("Managerial", newUser.getRole());
    }

    @Test
    public void getDepartmentIDGetsCorrectDepartmentId() throws Exception {
        User newUser = setupUser();
        assertEquals(3, newUser.getDepartmentId());
    }

    @Test
    public void setUsername() {
        User newUser = setupUser();
        newUser.setUsername("Jane");
        assertNotEquals("Mary", newUser.getUsername());
    }

    @Test
    public void setPosition() {
        User newUser = setupUser();
        newUser.setCompany_position("accountant");
        assertNotEquals("CEO", newUser.getCompany_position());
    }

    @Test
    public void setRole() {
        User newUser = setupUser();
        newUser.setRole("Accounting");
        assertNotEquals("Managerial", newUser.getRole());
    }

    @Test
    public void setDepartmentId() {
        User newUser = setupUser();
        newUser.setDepartmentId(2);
        assertNotEquals(3, newUser.getDepartmentId());
    }


}