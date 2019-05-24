package model.entity;

import model.entity.entityenum.UserType;
import uitility.encryption.Encryptor;
import java.util.Arrays;


//TODO: write documenttaion to fields which are not obvious
//TODO: not add but save
//TODO: in dao use optional if return one object
//TODO: not get but find
//TODO: void methods verify stab
//TODO: no magic numbers
public class User {
    private Long userId;
    private String login;
    private String name;
    private String lastName;
    private String hash;
    private byte[] salt;
    private Double rank;
    private UserType type;

    public User(Builder builder) {
        this.userId = builder.id;
        this.login = builder.login;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.hash = builder.hash;
        this.salt = builder.salt;
        this.rank = builder.rank;
        this.type = builder.type;
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

    public UserType getType() {
        return type;
    }

    public String getLogin() {
        return login;
    }

    public Double getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", hash='" + hash + '\'' +
                ", salt=" + Arrays.toString(salt) +
                ", rank=" + rank +
                ", type=" + type +
                '}';
    }

    public static class Builder {
        private Long id;
        private String login;
        private String name;
        private String lastName;
        private String hash;
        private Double rank;
        byte[] salt;
        private UserType type;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setPassword(String password) {
            this.salt = Encryptor.getSalt();
            this.hash = Encryptor.getSecurePassword(password, salt);
            return this;
        }

        public Builder setUserType(UserType type) {
            this.type = type;
            return this;
        }

        public Builder setHash(String hash) {
            this.hash = hash;
            return this;
        }

        public Builder setSalt(byte[] salt) {
            this.salt = salt;
            return this;
        }

        public Builder setRank(Double rank) {
            this.rank = rank;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
