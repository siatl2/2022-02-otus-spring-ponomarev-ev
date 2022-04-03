package ru.otus.homework04.domain;

public class Answer {
    private final String name;
    private final boolean correct;

    public Answer(final String name, final boolean correct){
        this.name = name;
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        return (name.equals(answer.name)) &
                (correct == correct);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (correct ? 1 : 0);
        return result;
    }
}
