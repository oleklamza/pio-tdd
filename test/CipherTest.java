import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

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

    @Test
    public void testSetKey3() {
        // Test ustawiania klucza szyfrowania (przesunięcia).
        cipher.setKey(3);
        Assertions.assertEquals("DEF", cipher.encrypt("ABC"));
        Assertions.assertEquals("ZAB", cipher.encrypt("WXY"));
    }

    @Test
    public void testSetKeyNegative() {
        // Test szyfrowania dla ujemnego klucza (przesunięcia).
        cipher.setKey(-3);
        Assertions.assertEquals("ABC", cipher.encrypt("DEF"));
        Assertions.assertEquals("WXY", cipher.encrypt("ZAB"));
    }

    @Test
    public void testSetKeyLargerThanAlphaLength() {
        // Test szyfrowania dla klucza o wartości większej niż długość alfabetu (26).
        cipher.setKey(3 + 26*9);
        Assertions.assertEquals("DEF", cipher.encrypt("ABC"));
        Assertions.assertEquals("ZAB", cipher.encrypt("WXY"));
        cipher.setKey(-3 - 26*7);
        Assertions.assertEquals("ABC", cipher.encrypt("DEF"));
        Assertions.assertEquals("WXY", cipher.encrypt("ZAB"));
    }

    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    public void testEncryptLongTextTimeout() {
        // Test czasu wykonania: zaszyfrowanie tekstu składającego się z 100_000 znaków
        // nie może trwać dłużej niż 100 ms. W pierwszej implementacji metody encrypt()
        // dochodziło do konkatenacji w pętli, co powodowało zdecydowane przekroczenie
        // tego limitu.

        // Zaczynamy od przygotowania długiego tekstu do zaszyfrowania oraz spodziewanego wyniku.
        StringBuilder in = new StringBuilder();
        StringBuilder expectedOut = new StringBuilder();
        for (int i=0; i<100_000; ++i) {
            in.append('A');
            expectedOut.append('B');
        }

        Assertions.assertEquals(expectedOut.toString(), cipher.encrypt(in.toString()));
    }

    @Test
    public void testEncryptTextNull() {
        // Test sprawdzający, czy po przekazaniu null-a jako tekstu do zaszyfrowania
        // rzucany jest wyjątek IllegalArgumentException z komunikatem "Invalid text".
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cipher.encrypt(null);
        });
        Assertions.assertEquals("Invalid text", ex.getMessage());
    }

    @Test
    public void testEncryptInvalidText() {
        // Test sprawdzający, czy po przekazaniu tekstu zawierającego znaki spoza alfabetu
        // rzucany jest wyjątek IllegalArgumentException z komunikatem "Invalid text".
        Exception ex = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cipher.encrypt("#$%");
        });
        Assertions.assertEquals("Invalid text", ex.getMessage());
    }

    @Test
    public void testEmptyText() {
        // Test sprawdzający, czy wynikiem szyfrowania pustego tekstu jest również pusty tekst.
        // Po modyfikacji wynikającej z dwóch poprzednich testów po przekazaniu pustego
        // łańcucha do encrypt() był rzucany wyjątek.
        // Poprawka wymaga jedynie drobnej zmiany w wyrażeniu regularnym (linia 12 klasy Cipher):
        // zamiast "+" (co najmniej jedno wystąpienie) trzeba dać "*" (zero lub więcej wystąpień).
        Assertions.assertTrue(cipher.encrypt("").isEmpty());
    }

}