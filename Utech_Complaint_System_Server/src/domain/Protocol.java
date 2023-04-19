package domain;

public class Protocol {
    public static String createLoginRequest(String email, String password) {
        return "LOGIN_REQUEST:" + email + ":" + password;
    }

    public static String createLoginResponse(boolean success, String message) {
        return "LOGIN_RESPONSE:" + (success ? "SUCCESS" : "FAILURE") + ":" + message;
    }

    // Add more methods for other requests and responses as needed.
}
