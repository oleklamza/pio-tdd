import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Testy klasy Cipher.
 * Kod jest tworzony zgodnie z techniką TDD, czyli -- w skrócie -- najpierw test kończący się
 * niepowodzeniem, później kod, dzięki któremu test przechodzi.
 */
class CipherTest {
    private Cipher cipher;

    @BeforeEach
    public void setUp() {
        // Przed każdym testem tworzony jest nowy obiekt Cipher.
        cipher = new Cipher();
    }

    @Test
    public void testCipherObject() {
        // Test, który nie ma większego sensu
        // (poza pokazaniem, że istnieje asercja assertNotNull ;)
        Assertions.assertNotNull(cipher);
    }

    @Test
    public void testEncrypt() {
        // Test standardowego szyfrowania.
        Assertions.assertEquals("BCD", cipher.encrypt("ABC"));
    }

    @Test
    public void testEncryptCyclic() {
        // Test "zawinięcia" na końcu alfabetu.
        Assertions.assertEquals("YZA", cipher.encrypt("XYZ"));
    }

}