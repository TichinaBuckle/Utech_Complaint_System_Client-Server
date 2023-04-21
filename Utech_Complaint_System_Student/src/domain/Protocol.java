package domain;

public class Protocol {
	
	// Authenticator Protocol
	public static String createAuthRequest(String id, String password) {
		return "AUTH_REQUEST:" + id + ":" + password;
	}

	public static String createAuthResponse(boolean success, String role) {
	    return "AUTH_RESPONSE:" + (success ? "SUCCESS" : "FAILURE") + ":" + role;
	 }

	 public static boolean isAuthResponseSuccess(String response) {
	    return response.startsWith("AUTH_RESPONSE:SUCCESS");
	 }

	 public static String getAuthResponseRole(String response) {
		 String[] parts = response.split(":");
	     return parts.length > 2 ? parts[2] : "";
	 }

	// Sign-up Protocol
	    public static String createSignUpRequest(String firstName, String lastName, String email, String contactNumber, String id, String password, String role) {
	        return "SIGN_UP_REQUEST:" + firstName + ":" + lastName + ":" + email + ":" + contactNumber + ":" + id + ":" + password + ":" + role;
	    }
	    
	    public static String createSignUpResponse(boolean success) {
	        return "SIGN_UP_RESPONSE:" + (success ? "SUCCESS" : "FAILURE");
	    }

	    public static boolean isSignUpResponseSuccess(String response) {
	        return response.startsWith("SIGN_UP_RESPONSE:SUCCESS");
	    }
	    
	    
	 // Add more methods for other requests and responses as needed.
}

