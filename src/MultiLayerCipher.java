// MultiLayerCipher class performs encryption/decryption operations
public class MultiLayerCipher {

    String origin;
    String[] tR;
    String key;

    // The key is set when instance is created
    public MultiLayerCipher(String key) {
        this.key = key;
        this.origin = originStringGen();
        this.tR = tableGen();
    }

    // Encrypt calls ciphers: substitution, transposition, substitution again (w/ reversed key)
    public String encrypt(String message) {
        String firstCipher = subEncipher(this.key, message);
        String secondCipher = transEncipher(firstCipher);
        return subEncipher(keyFlip(this.key), secondCipher);
    }

    // Decrypt calls ciphers: substitution (w/ reversed key), transposition, substitution (normal key)
    public String decrypt(String message) {
        String firstDecipher = subDecipher(keyFlip(this.key), message);
        String secondDecipher = transDecipher(firstDecipher);
        return subDecipher(this.key, secondDecipher);
    }

    // Substitution encipher is based on a Vigenere-style cipher using tR Vigenere table
    private String subEncipher(String key, String message) {
        StringBuilder sb = new StringBuilder();
        int kI = 0;

        for (int i = 0; i < message.length(); i++) {
            if (kI == key.length()) {
                kI = 0;
            }
            int kP = origin.indexOf(key.charAt(kI));
            String srcStr = tR[kP];
            int mP = origin.indexOf(message.charAt(i));
            sb.append(srcStr.charAt(mP));
            kI++;
        }
        return sb.toString();
    }

    // Substitution decipher is the inverse of substitution encipher
    private String subDecipher(String key, String message) {
        StringBuilder sb = new StringBuilder();
        int kI = 0;

        for (int i = 0; i < message.length(); i++) {
            if (kI == key.length()) {
                kI = 0;
            }
            int kP = origin.indexOf(key.charAt(kI));
            String srcStr = tR[kP];
            int mP = srcStr.indexOf(message.charAt(i));
            sb.append(origin.charAt(mP));
            kI++;
        }
        return sb.toString();
    }

    // Transposition encipher alternates characters into two strings and collates the combined strings in reverse
    private String transEncipher(String message) {
        int offset = (message.length() % 2 != 0) ? 1 : 0;
        String firstHalf = message.substring(0, (message.length()/2) + offset);
        String secondHalf = message.substring(message.length()/2 + offset);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < firstHalf.length(); i++) {
            sb.append(firstHalf.charAt(i));
            if (i < secondHalf.length()) {
                sb.append(secondHalf.charAt(i));
            }
        }
        return sb.reverse().toString();
    }

    // Transposition decipher is the inverse of transposition encipher
    private String transDecipher(String message) {
        StringBuilder sb = new StringBuilder(message);
        String reversed = sb.reverse().toString();
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < reversed.length(); i+=2) {
            s1.append(reversed.charAt(i));
            if (i + 1 < reversed.length()) {
                s2.append(reversed.charAt(i + 1));
            }
        }
        return s1.toString() + s2.toString();
    }

    // Creates an origin string of ASCII characters from 32-126 (inclusive)
    private String originStringGen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 32; i < 127; i++) {
            sb.append((char)i);
        }
        return sb.toString();
    }

    // Creates a Vigenere table based on the origin string
    private String[] tableGen() {
        String[] tabulaRecta = new String[95];
        tabulaRecta[0] = origin;
        String temp = origin;
        for (int i = 1; i < origin.length(); i++)
        {
            char tempChar = temp.charAt(0);
            temp = temp.substring(1) + tempChar;
            tabulaRecta[i] = temp;
        }
        return tabulaRecta;
    }

    // Returns the key in reverse
    private String keyFlip(String key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= key.length(); i++) {
            sb.append(key.charAt(key.length() - i));
        }
        return sb.toString();
    }
}
