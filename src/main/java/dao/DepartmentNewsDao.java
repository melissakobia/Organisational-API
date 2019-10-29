package dao;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {
    //Create
    void add(DepartmentNews departmentNews);

    //Read
    List<DepartmentNews> getAll();
    List<DepartmentNews> getAllDepartmentNewsByDepartment(int departmentId);

    //Update
    void update (String content, int departmentId);

    //Delete
    void deleteById (int id);
}
