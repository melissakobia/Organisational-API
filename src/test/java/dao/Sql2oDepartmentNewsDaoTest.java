package dao;

import models.DepartmentNews;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oDepartmentNewsDaoTest {
    private Connection conn;
    private Sql2oDepartmentNewsDao departmentNewsDao;


    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
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
        assertEquals(1, testDepartmentNews.getId());
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