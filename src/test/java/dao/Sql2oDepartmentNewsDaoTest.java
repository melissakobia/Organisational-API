package dao;

import models.DepartmentNews;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {
    private static Connection conn;
    private static Sql2oDepartmentNewsDao departmentNewsDao;


    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api_test";;
        Sql2o sql2o = new Sql2o(connectionString, "sonnie", "DBpassword");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        String sql = "DELETE from news where type = 'department'";
        conn.createQuery(sql).executeUpdate();
    }

    @AfterClass
    public static void shutDown() throws Exception {
        conn.close();

    }

    public DepartmentNews setupDepartmentNews() {
        DepartmentNews departmentNews = new DepartmentNews("I am department", 2);
        departmentNewsDao.add(departmentNews);
        return departmentNews;
    }

    @Test
    public void addingDepartmentNewsSetsId() throws Exception {
        DepartmentNews testDepartmentNews = setupDepartmentNews();
        assertNotEquals(0, testDepartmentNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        DepartmentNews testDepartmentNews = setupDepartmentNews();
        DepartmentNews testDepartmentNews1 = setupDepartmentNews();
        assertEquals(2, departmentNewsDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        DepartmentNews testDepartmentNews = setupDepartmentNews();
        DepartmentNews testDepartmentNews1 = setupDepartmentNews();
        assertEquals(2, departmentNewsDao.getAll().size());
        departmentNewsDao.deleteById(testDepartmentNews.getId());
        assertEquals(1, departmentNewsDao.getAll().size());
    }
}