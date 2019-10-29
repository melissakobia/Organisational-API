package models;

public class DepartmentNews extends News {


    public static final String DATABASE_TYPE = "department";
    private int departmentId;
    private int id;

    public DepartmentNews(String content, int departmentId) {
        this.content = content;
        this.departmentId = departmentId;
        this.type = DATABASE_TYPE;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
