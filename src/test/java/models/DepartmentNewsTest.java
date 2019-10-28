package models;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DepartmentNewsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public DepartmentNews setupDepartmentNews () {
        return new DepartmentNews("Submit Projects", 2);
    }

    @Test
    public void getContentReturnsContent() throws Exception {
      DepartmentNews departmentNews = setupDepartmentNews();
      assertEquals("Submit Projects", departmentNews.getContent());
    }

    @Test
    public void setContentsetsContent() throws Exception {
        DepartmentNews departmentNews = setupDepartmentNews();
        departmentNews.setContent("Department Catchup");
        assertNotEquals("Submit Projects", departmentNews.getContent());

    }

    @Test
    public void getDepartmentIDGetsCorrectDepartmentId() throws Exception {
        DepartmentNews departmentNews = setupDepartmentNews();
        assertEquals(2,departmentNews.getDepartmentId());
    }

    @Test
    public void setDepartmentIdSetsDepartmentId() {
        DepartmentNews departmentNews = setupDepartmentNews();
        departmentNews.setDepartmentId(3);
        assertNotEquals(2,departmentNews.getDepartmentId());
    }
}