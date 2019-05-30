package model.entity;

import java.util.Objects;

public class QuestionType {
    private Integer id;
    private String type;

    //in order to put in db
    public QuestionType(String type) {
        this.type = type;
    }

    //in order to take from db
    public QuestionType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionType that = (QuestionType) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
