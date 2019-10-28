package models;

public class GeneralNews extends News {
    public static final String DATABASE_TYPE = "general";

    public GeneralNews(String content) {
        this.content = content;
        type = DATABASE_TYPE;
    }
}
