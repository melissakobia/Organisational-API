package dao;

import models.Department;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;


public class Sql2oDepartmentDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";;
        Sql2o sql2o = new Sql2o(connectionString, "sonnie", "DBpassword");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception{
        String sql = "DELETE from departments";
        conn.createQuery(sql).executeUpdate();
    }

    @AfterClass
    public static void shutDown() throws Exception {
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