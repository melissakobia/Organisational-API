package dao;

import models.GeneralNews;

import java.util.List;

public interface GeneralNewsDao {

    //Create
    void add(GeneralNews generalNews);

    //Read
    List<GeneralNews> getAll();


    //Update
    void update (String content);

    //Delete
    void deleteById (int id);
    void clearAll();
}

