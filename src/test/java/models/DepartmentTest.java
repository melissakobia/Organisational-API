package models;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DepartmentTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public Department setupDepartment () {
        return new Department("Accounting", "Deals with the Account", 4);
    }

    public Department setupOtherDepartment () {
        return new Department("HR", "Deals with the Human Resource", 3);
    }

    @Test
    public void Department_instantiatesCorrectly_true() {
        Department newDepartment = setupDepartment();
        assertTrue(newDepartment instanceof Department);
    }

    @Test
    public void getDeptNameReturnsCorrectDeptName() throws Exception {
        Department newDepartment = setupDepartment();
        assertEquals("Accounting", newDepartment.getDeptName());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() throws Exception {
        Department newDepartment = setupDepartment();
        assertEquals("Deals with the Account", newDepartment.getDescription());
    }

    @Test
    public void getNoOfEmployeesReturnsCorrectNoOfEmployees() throws Exception {
        Department newDepartment = setupDepartment();
        assertEquals(4, newDepartment.getNoOfEmployees());
    }

    @Test
    public void setDeptNameSetsCorrectDeptName() throws Exception {
        Department newDepartment = setupDepartment();
        newDepartment.setDeptName("IT");
        assertNotEquals("Accounting", newDepartment.getDeptName());
    }

    @Test
    public void setNoOfEmployeesSetsCorrectNoOfEmployees() throws Exception {
        Department newDepartment = setupDepartment();
        newDepartment.setNoOfEmployees(2);
        assertNotEquals("4", newDepartment.getNoOfEmployees());
    }

    @Test
    public void setDescriptionSetsCorrectDescription() throws Exception {
        Department newDepartment = setupDepartment();
        newDepartment.setDescription("Tech");
        assertNotEquals("Deals with the Account", newDepartment.getDescription());
    }
}