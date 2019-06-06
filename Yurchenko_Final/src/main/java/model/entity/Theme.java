package model.entity;

import java.io.Serializable;
import java.util.Objects;

public class Theme implements Serializable {
    private Long themeId;
    private String themeName;

    public Theme(Long themeId, String themeName) {
        this.themeId = themeId;
        this.themeName = themeName;
    }

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
        Theme themeToCompare = (Theme) o;
        return Objects.equals(themeId, themeToCompare.themeId) &&
                Objects.equals(themeName, themeToCompare.themeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(themeId, themeName);
    }

    @Override
    public String toString() {
        return new StringBuilder("{Theme: ")
                .append("themeId = ").append(themeId)
                .append(", themeName = ").append(themeName)
                .append("}")
                .toString();
    }

}
