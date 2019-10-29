package dao;

import models.Department;
import models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao {
    private final Sql2o sql2o;

    public Sql2oDepartmentNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(DepartmentNews departmentNews) {
        String sql = "INSERT INTO news (content, type, departmentId) VALUES (:content, :type, :departmentId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql,true).bind(departmentNews).executeUpdate().getKey();
            departmentNews.setId(id);

        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<DepartmentNews> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM news WHERE type = 'department'").throwOnMappingFailure(false).executeAndFetch(DepartmentNews.class);

        }
    }

    @Override
    public List<DepartmentNews> getAllDepartmentNewsByDepartment(int departmentId) {
        String sql = "SELECT * FROM news WHERE departmentid = :departmentId";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql).addParameter("departmentId", departmentId).throwOnMappingFailure(false).executeAndFetch(DepartmentNews.class);

        }
    }


    @Override
    public void update(String content, int departmentId) {

    }


    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }

    }
}
