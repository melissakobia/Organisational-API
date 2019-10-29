package models;

import java.util.Objects;

public class Department {

    private String DeptName;
    private String description;
    private int NoOfEmployees;
    private int id;


    public Department(String deptName, String description, int NoOfEmployees) {
        this.DeptName = deptName;
        this.description = description;
        this.NoOfEmployees = NoOfEmployees;
    }

    public String getDeptName() {
        return DeptName;
    }

    public String getDescription() {
        return description;
    }

    public int getNoOfEmployees() {
        return NoOfEmployees;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNoOfEmployees(int NoOfEmployees) {
        this.NoOfEmployees = NoOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return id == that.id &&
                getNoOfEmployees() == that.getNoOfEmployees() &&
                Objects.equals(getDeptName(), that.getDeptName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeptName(), getDescription(), getNoOfEmployees(), getId());
    }
}
