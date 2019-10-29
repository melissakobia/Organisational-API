package dao;

import models.User;

import java.util.List;

public interface UserDao {

    //Create
    void add(User user);

    //Read
    List<User> getAll();
    List<User> getAllUsersByDepartment(int departmentID);

    //Update
    void update (String username, String company_position, String role, int departmentId);

    //Delete
    void deleteById (int id);
    void clearAll();
}
