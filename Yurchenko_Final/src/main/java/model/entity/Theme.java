package model.entity;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Theme theme = (Theme) o;
        return Objects.equals(themeId, theme.themeId) &&
                Objects.equals(themeName, theme.themeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, themeName);
    }

    @Override
    public String toString() {
        return "Theme{" +
                "themeId=" + themeId +
                ", themeName='" + themeName + '\'' +
                '}';
    }
}
