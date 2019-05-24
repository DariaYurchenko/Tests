package model.entity;

public class Theme {
    private Long themeId;
    private String themeName;

    //to take from db
    public Theme(Long themeId, String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

    //to add to db
    public Theme(String themeName) {
        this.themeName = themeName;
    }


    public Long getThemeId() {
        return themeId;
    }

    public String getThemeName() {
        return themeName;
    }



    @Override
    public String toString() {
        return "Theme{" +
                "themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
