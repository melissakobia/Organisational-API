package dao;

import models.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements UserDao {
    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (username, company_position, role, departmentid) VALUES (:username, :company_position, :role, :departmentId)";

        try (Connection con = sql2o.open()) {
           int id = (int) con.createQuery(sql, true).bind(user).executeUpdate().getKey();
           user.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<User> getAll() {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM users").executeAndFetch(User.class);

        }
    }

    @Override
    public List<User> getAllUsersByDepartment( int departmentId) {
        String sql = "SELECT * FROM users WHERE departmentid = :departmentId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("departmentId", departmentId).executeAndFetch(User.class);

        }
    }

    @Override
    public void update(String username, String position, String role, int departmentId) {
        //TO DO
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).addParameter("id",id).executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE from users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
