package ru.otus.homework07.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework07.component.impl.IOServiceStream;
import ru.otus.homework07.model.Author;
import ru.otus.homework07.service.AuthorOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

@SpringBootTest(classes = {AuthorOutputImpl.class})
class AuthorOutputImplTest {
    private static final String EXPECTED_AUTHOR = "Author with id=1, name=vasya-author";
    private static final List<String> EXPECTED_AUTHORS = Arrays.asList(
                                                    "List authors:"
                                                    , " id  | Name"
                                                    , "-----|------"
                                                    , "    1|vasya-author"
                                                    , "    2|kolea-author"
                                                    , "------------"
                                                    );
    @Autowired
    private AuthorOutput authorOutput;

    @MockBean
    private IOServiceStream ioServiceStream;

    @Captor
    private ArgumentCaptor<String> interceptOutput;

    @BeforeEach
    void BeforeEachTest(){
        doNothing().when(ioServiceStream).outputString(interceptOutput.capture());
    }

    @Test
    void outputAuthor() {
        long id = 1;
        String name = "vasya-author";
        Author author = new Author(id, name);

        authorOutput.outputAuthor(author);

        String expectedValue = EXPECTED_AUTHOR;
        String actualValue = interceptOutput.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void outputAuthors() {
        long id1 = 1;
        String name1 = "vasya-author";
        Author author1 = new Author(id1, name1);

        long id2 = 2;
        String name2 = "kolea-author";
        Author author2 = new Author(id2, name2);

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        authorOutput.outputAuthors(authors);

        List<String> expectedValue = EXPECTED_AUTHORS;
        List<String> actualValue = interceptOutput.getAllValues();
        assertEquals(expectedValue, actualValue);
    }
}
