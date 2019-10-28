package models;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneralNewsTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public GeneralNews setupGeneralNews () {
        return new GeneralNews("I am general");
    }

    @Test
    public void getContentReturnsContent() throws Exception {
        GeneralNews generalNews = setupGeneralNews();
        assertEquals("I am general", generalNews.getContent());
    }

    @Test
    public void setContentsetsContent() throws Exception {
        GeneralNews generalNews = setupGeneralNews();
        generalNews.setContent("I am other general");
        assertNotEquals("I am general", generalNews.getContent());

    }
}