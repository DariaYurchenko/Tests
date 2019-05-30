package model.entity;

import model.entity.status.UserStatus;
import uitility.encryption.Encryptor;
import java.util.Arrays;
import java.util.Objects;

public class User {
    private Long userId;
    private String login;
    private String name;
    private String lastName;
    private String hash;
    private byte[] salt;
    private Double rank;
    private UserStatus status;

    public User(Builder builder) {
        this.userId = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.hash = builder.hash;
        this.salt = builder.salt;
        this.rank = builder.rank;
        this.status = builder.status;
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

    public UserStatus getStatus() {
        return status;
    }

    public String getLogin() {
        return login;
    }

    public Double getRank() {
        return rank;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public static class Builder {
        private Long id;
        private String login;
        private String name;
        private String lastName;
        private String hash;
        private Double rank;
        byte[] salt;
        private UserStatus status;

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

        public Builder withPassword(String password) {
            this.salt = Encryptor.getSalt();
            this.hash = Encryptor.getSecurePassword(password, salt);
            return this;
        }

        public Builder withUserType(UserStatus type) {
            this.status = type;
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
            this.rank = rank;
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(login, user.login) &&
                Objects.equals(name, user.name) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(hash, user.hash) &&
                Arrays.equals(salt, user.salt) &&
                Objects.equals(rank, user.rank) &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(userId, login, name, lastName, hash, rank, status);
        result = 31 * result + Arrays.hashCode(salt);
        return result;
    }
}
