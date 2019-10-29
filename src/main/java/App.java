import static spark.Spark.*;

import exceptions.ApiException;
import dao.Sql2oDepartmentDao;
import dao.Sql2oDepartmentNewsDao;
import dao.Sql2oGeneralNewsDao;
import dao.Sql2oUserDao;
import models.Department;
import models.DepartmentNews;
import models.GeneralNews;
import models.User;
import org.sql2o.Sql2o;
import org.sql2o.Connection;
import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        Sql2oDepartmentDao departmentDao;
        Sql2oDepartmentNewsDao departmentNewsDao;
        Sql2oGeneralNewsDao generalNewsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson = new Gson();


        String connectionString = "jdbc:postgresql://localhost:5432/organisational_api";
        Sql2o sql2o = new Sql2o(connectionString, "sonnie", "DBpassword");
        userDao = new Sql2oUserDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        departmentNewsDao = new Sql2oDepartmentNewsDao(sql2o);
        generalNewsDao = new Sql2oGeneralNewsDao(sql2o);
        conn = sql2o.open();

        //CREATE
        post("/departments/new", "application/json", ((request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);
            response.status(201);
            return gson.toJson(department);
        }));

        post("/users/new", "application/json", ((request, response) -> {
            User user = gson.fromJson(request.body(),User.class);
            userDao.add(user);
            response.status(201);
            return gson.toJson(user);
        }));

        post("/generalnews/new", "application/json", (request, response) ->{
            GeneralNews generalNews = gson.fromJson(request.body(),GeneralNews.class);
            generalNews.setType("general");

            generalNewsDao.add(generalNews);
            response.status(201);
            return gson.toJson(generalNews);

        });

        post("/departmentnews/new", "application/json", (request, response) ->{
            DepartmentNews departmentNews = gson.fromJson(request.body(), DepartmentNews.class);
            departmentNews.setType("department");
            departmentNewsDao.add(departmentNews);
            response.status(201);
            return gson.toJson(departmentNews);

        });



        //READ

        //Get all departments
        get("/departments", "application/json" , ((request, response) -> {
            return gson.toJson(departmentDao.getAll());
        }));

        get("/users", "application/json" , ((request, response) -> {
            return gson.toJson(userDao.getAll());
        }));

        get("/departments/:id/users", "application/json", (request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if(departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(userDao.getAllUsersByDepartment(departmentId));

            }
        });


        get("/generalnews", "application/json", ((request, response) -> {
            if (generalNewsDao.getAll().size() > 0) {
                return gson.toJson(generalNewsDao.getAll());
            }
            else {
                return  "{\"message\":\"I'm sorry, but no general News are currently listed in the database.\"}";
            }
        }));

        get("/departmentnews", "application/json", ((request, response) -> {
            if (departmentNewsDao.getAll().size() > 0) {
                return gson.toJson(departmentNewsDao.getAll());
            }
            else {
                return  "{\"message\":\"I'm sorry, but no Department News are currently listed in the database.\"}";
            }
        }));

        get("/departments/:id/departmentnews", "application/json", ((request, response) -> {
            int departmentId = Integer.parseInt(request.params("id"));
            Department departmentToFind = departmentDao.findById(departmentId);
            if(departmentToFind == null) {
                throw new ApiException(404, String.format("No department with the id: \"%s\" exists", request.params("id")));
            }
            else {
                return gson.toJson(departmentNewsDao.getAllDepartmentNewsByDepartment(departmentId));
            }

        }));




        //FILTERS
        after((req, res) ->{
            res.type("application/json");
        });

    }
}
