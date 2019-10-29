package dao;

import models.GeneralNews;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oGeneralNewsDaoTest {
    private Connection conn;
    private Sql2oGeneralNewsDao generalNewsDao;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        generalNewsDao = new Sql2oGeneralNewsDao(sql2o);
        conn = sql2o.open();

    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    public GeneralNews setupGeneralNews() {
        GeneralNews generalNews = new GeneralNews("I am general");
        generalNewsDao.add(generalNews);
        return generalNews;
    }

    @Test
    public void addingGeneralNewsSetsId() throws Exception {
        GeneralNews testGeneralNews = setupGeneralNews();
        assertEquals(1, testGeneralNews.getId());
    }

    @Test
    public void getAll() throws Exception {
        GeneralNews testGeneralNews = setupGeneralNews();
        GeneralNews otherGeneralNews = setupGeneralNews();
        assertEquals(2, generalNewsDao.getAll().size());
    }

    @Test
    public void deleteById() throws Exception {
        GeneralNews testGeneralNews = setupGeneralNews();
        GeneralNews otherGeneralNews = setupGeneralNews();
        assertEquals(2, generalNewsDao.getAll().size());
        generalNewsDao.deleteById(testGeneralNews.getId());
        assertEquals(1, generalNewsDao.getAll().size());

    }

    @Test
    public void clearAll() throws Exception {
        GeneralNews testGeneralNews = setupGeneralNews();
        GeneralNews otherGeneralNews = setupGeneralNews();
        generalNewsDao.clearAll();
        assertEquals(0, generalNewsDao.getAll().size());
    }
}