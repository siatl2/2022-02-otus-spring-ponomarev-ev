package ru.otus.homework14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GENRE")
public class GenreJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Genre_sec")
    @SequenceGenerator(name = "Genre_sec", sequenceName = "SEC_GENRE")
    private long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenreJpa)) return false;

        GenreJpa genreJpa = (GenreJpa) o;

        if (id != genreJpa.id) return false;
        return name.equals(genreJpa.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

