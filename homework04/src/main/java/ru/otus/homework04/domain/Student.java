package ru.otus.homework04.domain;

public class Student {
    private final String family;
    private final String name;

    public Student(final String family, final String name) {
        this.family = family;
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        if (!family.equals(student.family)) return false;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        int result = family.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
