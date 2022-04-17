package ru.otus.homework05.domain;

public class Author {
    private static final long ID_NOT_FROM_DATABASE = -1L;
    private long id;
    private final String name;

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.id = ID_NOT_FROM_DATABASE;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        return name.equals(author.name);
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
