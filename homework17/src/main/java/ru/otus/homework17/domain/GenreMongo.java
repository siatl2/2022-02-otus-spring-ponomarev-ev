package ru.otus.homework17.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genre")
public class GenreMongo {
    @Id
    private long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreMongo)) return false;

        GenreMongo genreMongo = (GenreMongo) o;

        if (id != genreMongo.id) return false;
        return name.equals(genreMongo.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GenreMongo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

