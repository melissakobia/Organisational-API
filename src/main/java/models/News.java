package models;

import java.util.Objects;

public abstract class News {

    public String content;
    public String type;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(getContent(), news.getContent()) &&
                Objects.equals(type, news.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent(), type);
    }
}
