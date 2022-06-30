package ru.otus.homework14.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class CommentMongo {
    @Id
    private long id;

    private String name;

    @DBRef
    private BookMongo bookMongo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentMongo)) return false;

        CommentMongo commentMongo = (CommentMongo) o;

        if (id != commentMongo.id) return false;
        if (!name.equals(commentMongo.name)) return false;
        return bookMongo.equals(commentMongo.bookMongo);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + bookMongo.hashCode();
        return result;
    }
}

