package model.entity;

import model.entity.status.UserStatus;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class User implements Serializable {
    private Long userId;
    private String login;
    private String name;
    private String lastName;
    private String hash;
    private byte[] salt;
    /**
     * userRank - relation of user's
     * right answers to all his answers
     * in all tests
     */
    private Double userRank;
    private UserStatus userStatus;

    public User(Builder builder) {
        this.userId = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.hash = builder.hash;
        this.salt = builder.salt;
        this.userRank = builder.userRank;
        this.userStatus = builder.userStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public String getLogin() {
        return login;
    }

    public Double getUserRank() {
        return userRank;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public static class Builder {
        private Long id;
        private String login;
        private String name;
        private String lastName;
        private String hash;
        private Double userRank;
        byte[] salt;
        private UserStatus userStatus;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUserType(UserStatus type) {
            this.userStatus = type;
            return this;
        }

        public Builder withHash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder withSalt(byte[] salt) {
            this.salt = salt;
            return this;
        }

        public Builder withRank(Double rank) {
            this.userRank = rank;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User userToCompare = (User) o;
        return Objects.equals(userId, userToCompare.userId) &&
                Objects.equals(login, userToCompare.login) &&
                Objects.equals(name, userToCompare.name) &&
                Objects.equals(lastName, userToCompare.lastName) &&
                Objects.equals(hash, userToCompare.hash) &&
                Arrays.equals(salt, userToCompare.salt) &&
                Objects.equals(userRank, userToCompare.userRank) &&
                userStatus == userToCompare.userStatus;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, login, name, lastName, hash, userRank, userStatus);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }

    @Override
    public String toString() {

        return  new StringBuilder("{User: ")
                .append("userId = ").append(userId)
                .append(", login = ").append(login)
                .append(",  name = ").append(name)
                .append(", lastName = ").append(lastName)
                .append(", hash = ").append(hash)
                .append(", salt = ").append(Arrays.toString(salt))
                .append(", userRank = ").append(userRank)
                .append(", userStatus = ").append(userStatus)
                .append("}")
                .toString();
    }

}
