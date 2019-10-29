package dao;

import models.Department;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oDepartmentDaoTest {
    private Connection conn;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    //helpers
    public Department setupDepartment () {
        Department department = new Department("Accounting", "Deals with the Account", 4);
        departmentDao.add(department);
        return department;
    }

    public Department setupOtherDepartment () {
        Department department = new Department("HR", "Deals with the Human Resource", 3);
        departmentDao.add(department);
        return department;
    }


    @Test
    public void addDepartmentSetsId() throws Exception{
        Department testDepartment = setupDepartment();
        assertNotEquals(0, testDepartment.getId());
    }

    @Test
    public void addedDepartmentsAreReturnedFromGetAll() {
        Department testDepartment = setupDepartment();
        assertEquals(1, departmentDao.getAll().size());

    }

    @Test
    public void noDepartmentReturnsEmptyList() {
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void deleteByIdDeletesCorrectDepartment() {
        Department testDepartment = setupDepartment();
        Department otherDepartment = setupOtherDepartment();
        departmentDao.deleteById(testDepartment.getId());
        assertEquals(1,departmentDao.getAll().size());
    }
}