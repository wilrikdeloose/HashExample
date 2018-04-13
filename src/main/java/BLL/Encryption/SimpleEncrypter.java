package BLL.Encryption;

public class SimpleEncrypter implements PasswordEncrypter {

    @Override
    public SimplePassword encrypt(String password) {
        return encrypt(password, null);
    }

    @Override
    public SimplePassword encrypt(String password, byte[] inputSalt) {
        byte[] salt = inputSalt == null ? (new Salt()).get() : inputSalt;
        byte[] encryptedPass = new byte[password.length()];

        byte s = 0;
        for (int i = 0; i < salt.length; i++) { s = (byte)(s + salt[i]); }

        // worst encryption ever...
        for (int i = 0; i < encryptedPass.length; i++) {
            byte b = (byte)(password.toCharArray()[i]);
            encryptedPass[i] = (byte)(b + s);
        }

        return new SimplePassword(new String(encryptedPass), salt);
    }
}
