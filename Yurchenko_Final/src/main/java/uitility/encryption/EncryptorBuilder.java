package uitility.encryption;

public final class EncryptorBuilder {
    private String hash;
    private byte[] salt;

    public EncryptorBuilder(String password) {
        this.salt = Encryptor.getSalt();
        this.hash = Encryptor.getSecurePassword(password, salt);
    }

    public String getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }
}
