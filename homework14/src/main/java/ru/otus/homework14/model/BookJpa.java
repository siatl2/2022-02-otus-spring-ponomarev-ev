package ru.otus.homework14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BOOK")
public class BookJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_sec")
    @SequenceGenerator(name = "Book_sec", sequenceName = "SEC_BOOK")
    private long id;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorJpa authorJpa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    private GenreJpa genreJpa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookJpa)) return false;

        BookJpa bookJpa = (BookJpa) o;

        if (id != bookJpa.id) return false;
        if (!name.equals(bookJpa.name)) return false;
        if (!authorJpa.equals(bookJpa.authorJpa)) return false;
        return genreJpa.equals(bookJpa.genreJpa);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + authorJpa.hashCode();
        result = 31 * result + genreJpa.hashCode();
        return result;
    }
}

