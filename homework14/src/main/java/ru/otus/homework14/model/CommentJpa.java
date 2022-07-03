package ru.otus.homework14.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT")
public class CommentJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Comment_sec")
    @SequenceGenerator(name = "Comment_sec", sequenceName = "SEC_COMMENT")
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private BookJpa bookJpa;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentJpa)) return false;

        CommentJpa commentJpa = (CommentJpa) o;

        if (id != commentJpa.id) return false;
        if (!name.equals(commentJpa.name)) return false;
        return bookJpa.equals(commentJpa.bookJpa);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + bookJpa.hashCode();
        return result;
    }
}

