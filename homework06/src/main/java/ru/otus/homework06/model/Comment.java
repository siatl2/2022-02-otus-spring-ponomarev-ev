package ru.otus.homework06.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Comment_sec")
    @SequenceGenerator(name = "Comment_sec", sequenceName = "SEC_COMMENT")
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;


}
