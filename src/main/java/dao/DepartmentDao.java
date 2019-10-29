package dao;

import models.Department;

import java.util.List;

public interface DepartmentDao {
    //Create
    void add (Department department);

    //Read
    List<Department> getAll();
    Department findById(int id);


    //Update
    void update (String deptName, String description, int NoOfEmployees);

    //Delete
    void deleteById (int id);

}
