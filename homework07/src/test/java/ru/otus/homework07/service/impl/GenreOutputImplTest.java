package ru.otus.homework07.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework07.component.impl.IOServiceStream;
import ru.otus.homework07.model.Genre;
import ru.otus.homework07.service.GenreOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {GenreOutputImpl.class})
public class GenreOutputImplTest {
    private static final String EXPECTED_GENRE = "Genre with id=1, name=Romantic literature";
    private static final List<String> EXPECTED_GENRES = Arrays.asList(
            "List genres:"
            , " id  | Name"
            , "-----|------"
            , "    1|Romantic literature"
            , "    2|Terrible literature"
            , "------------"
            );

    @Autowired
    private GenreOutput genreOutput;

    @MockBean
    private IOServiceStream ioServiceStream;

    @Captor
    private ArgumentCaptor<String> interceptOutput;

    @BeforeEach
    void BeforeEachTest(){
        doNothing().when(ioServiceStream).outputString(interceptOutput.capture());
    }

    @Test
    void outputGenre() {
        long id = 1;
        String name = "Romantic literature";
        Genre genre = new Genre(id, name);

        genreOutput.outputGenre(genre);

        String expectedValue = EXPECTED_GENRE;
        String actualValue = interceptOutput.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void outputGenres() {
        long id1 = 1;
        String name1 = "Romantic literature";
        Genre genre1 = new Genre(id1, name1);

        long id2 = 2;
        String name2 = "Terrible literature";
        Genre genre2 = new Genre(id2, name2);

        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        genreOutput.outputGenres(genres);

        List<String> expectedValue = EXPECTED_GENRES;
        List<String> actualValue = interceptOutput.getAllValues();
        assertEquals(expectedValue, actualValue);
    }
}

