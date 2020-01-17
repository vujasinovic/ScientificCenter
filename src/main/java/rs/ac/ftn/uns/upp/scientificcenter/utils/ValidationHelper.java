package rs.ac.ftn.uns.upp.scientificcenter.utils;

import java.util.regex.Pattern;

public final class ValidationHelper {
    private static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

    public static final Pattern VALID_EMAIL_REGEX = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    private ValidationHelper() {

    }

    public static boolean validateEmail(String email) {
        return VALID_EMAIL_REGEX.matcher(email).find();
    }
}
