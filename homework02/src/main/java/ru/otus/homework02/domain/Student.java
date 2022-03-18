package ru.otus.homework02.domain;

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
}
