package model.entity;

import java.io.Serializable;
import java.util.Objects;

public class QuestionType implements Serializable {
    private Integer id;
    private String type;

    public QuestionType(String type) {
        this.type = type;
    }

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
        QuestionType questionTypeToCompare = (QuestionType) o;
        return Objects.equals(id, questionTypeToCompare.id) &&
                Objects.equals(type, questionTypeToCompare.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return new StringBuilder("{QuestionType: ")
                .append("id = ").append(id)
                .append(", type = ").append(type)
                .append("}")
                .toString();

    }

}
