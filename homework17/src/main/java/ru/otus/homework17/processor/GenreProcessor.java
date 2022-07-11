package ru.otus.homework17.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.homework17.domain.GenreMongo;
import ru.otus.homework17.model.GenreJpa;

@Service
public class GenreProcessor implements ItemProcessor<GenreJpa, GenreMongo> {

    @Override
    public GenreMongo process(GenreJpa genreJpa) throws Exception {
        GenreMongo genreMongo = new GenreMongo();
        genreMongo.setId(genreJpa.getId());
        genreMongo.setName(genreJpa.getName());
        return genreMongo;
    }
}
