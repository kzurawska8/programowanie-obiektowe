package war_games.Utils;

public class InputValidator {
    public static void validatePositiveNumber(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be greater than zero.");
        }
    }
}