
public class Password {
    private final String value;
    private final int length;

    public Password(String s) {
        value = s;
        length = s.length();
    }

    public String getValue() {
        return value;
    }

    public int getLength() {
        return length;
    }

    public int charType(char c) {
        int val;

        // Char is Uppercase Letter
        if ((int) c >= 65 && (int) c <= 90)
            val = 1;

        // Char is Lowercase Letter
        else if ((int) c >= 97 && (int) c <= 122) {
            val = 2;
        }

        // Char is Digit
        else if ((int) c >= 48 && (int) c <= 57) {
            val = 3;
        }

        // Char is Symbol
        else {
            val = 4;
        }

        return val;
    }

    public int passwordStrength() {
        String s = this.value;
        boolean usedUpper = false;
        boolean usedLower = false;
        boolean userNumber = false;
        boolean usedSymbol = false;
        int type;
        int score = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            type = charType(c);

            if (type == 1) usedUpper = true;
            if (type == 2) usedLower = true;
            if (type == 3) userNumber = true;
            if (type == 4) usedSymbol = true;
        }

        if (usedUpper) score += 1;
        if (usedLower) score += 1;
        if (userNumber) score += 1;
        if (usedSymbol) score += 1;

        if (s.length() >= 8) score += 1;
        if (s.length() >= 16) score += 1;

        return score;
    }

    public String calculateScore() {
        int strengthScore = this.passwordStrength();

        if (strengthScore == 6) {
            return "This is a very good password :D check the Useful Information section to make sure it satisfies the guidelines";
        } else if (strengthScore >= 4) {
            return "This is a good password :) but you can still do better";
        } else if (strengthScore >= 3) {
            return "This is a medium password :/ try making it better";
        } else {
            return "This is a weak password :( definitely find a new one";
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
