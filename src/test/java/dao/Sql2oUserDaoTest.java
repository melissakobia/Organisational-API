package dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oUserDaoTest {

    private Connection conn;
    private Sql2oUserDao userDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helpers
    public User setupUser () {
        User user = new User("Mary", "CEO", "Managerial", 3);
        userDao.add(user);
        return user;
    }

    public User setupUserForDepartment(Department department) {
        User user = new User("Mary", "CEO", "Managerial", department.getId());
        userDao.add(user);
        return user;
    }

    public Department setupDepartment () {
        Department department = new Department("Accounting", "Deals with the Account", 4);
        departmentDao.add(department);
        return department;
    }

    @Test
    public void addingUserSetsId() throws Exception {
        User testUser = setupUser();
        assertNotEquals(0, testUser.getId());
    }

    @Test
    public void getAllUsers() throws Exception{
        User testUser = setupUser();
        User otherUser = setupUser();
        assertEquals(2, userDao.getAll().size());
    }

    @Test
    public void getAllUsersByDepartment() throws Exception {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupDepartment();

        User testuser = setupUserForDepartment(testDepartment);
        User otherUser = setupUserForDepartment(testDepartment);

        assertEquals(2, userDao.getAllUsersByDepartment(testDepartment.getId()).size());
    }

    @Test
    public void deletesCorrectUserById() throws Exception {
        User testUser = setupUser();
        User otherUser = setupUser();
        assertEquals(2, userDao.getAll().size());
        userDao.deleteById(testUser.getId());
        assertEquals(1, userDao.getAll().size());
    }

    @Test
    public void clearAllDeletesAllUsers() throws Exception {
        User testUser = setupUser();
        User otherUser = setupUser();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }
}