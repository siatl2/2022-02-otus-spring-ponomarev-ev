package ru.otus.homework17.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "book")
public class BookMongo {
    @Id
    private long id;
    private String name;

    @DBRef
    private AuthorMongo authorMongo;

    @DBRef
    private GenreMongo genreMongo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookMongo)) return false;

        BookMongo bookMongo = (BookMongo) o;

        if (id != bookMongo.id) return false;
        if (!name.equals(bookMongo.name)) return false;
        if (!authorMongo.equals(bookMongo.authorMongo)) return false;
        return genreMongo.equals(bookMongo.genreMongo);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + authorMongo.hashCode();
        result = 31 * result + genreMongo.hashCode();
        return result;
    }
}

