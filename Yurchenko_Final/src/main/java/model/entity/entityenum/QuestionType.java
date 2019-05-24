package model.entity.entityenum;

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
    public String toString() {
        return "QuestionType{" +

                 "type='" + type + '\'' +
                '}';
    }
}
