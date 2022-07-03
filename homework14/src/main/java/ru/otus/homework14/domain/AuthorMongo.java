package ru.otus.homework14.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "author")
public class AuthorMongo {
    @Id
    private long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorMongo)) return false;

        AuthorMongo authorMongo = (AuthorMongo) o;

        if (id != authorMongo.id) return false;
        return name.equals(authorMongo.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AuthorMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

