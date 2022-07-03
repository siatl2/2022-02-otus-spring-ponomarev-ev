package ru.otus.homework14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "AUTHOR")
public class AuthorJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Author_sec")
    @SequenceGenerator(name = "Author_sec", sequenceName = "SEC_AUTHOR")
    private long id;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthorJpa)) return false;

        AuthorJpa authorJpa = (AuthorJpa) o;

        if (id != authorJpa.id) return false;
        return name.equals(authorJpa.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

