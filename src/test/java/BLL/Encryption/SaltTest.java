package BLL.Encryption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaltTest {

    @Test
    void saltAlwaysDifferent() {

        byte[] salt1 = (new Salt()).get();
        byte[] salt2 = (new Salt()).get();
        assertFalse(salt1.equals(salt2));
    }
}