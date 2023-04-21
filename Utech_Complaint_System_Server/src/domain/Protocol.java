/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	    
	    // StudentDashboard createNewComplaint
	    public static String createNewComplaintRequest(String studentId, String category, String description) {
	        return "NEW_COMPLAINT_REQUEST:" + studentId + ":" + category + ":" + description;
	    }

	    public static String createNewComplaintResponse(boolean success) {
	        return "NEW_COMPLAINT_RESPONSE:" + (success ? "SUCCESS" : "FAILURE");
	    }

	    public static boolean isNewComplaintResponseSuccess(String response) {
	        return response.startsWith("NEW_COMPLAINT_RESPONSE:SUCCESS");
	    }
	    
	    // StudentDashboard createTrackComplaint
	    
	    
	 // Add more methods for other requests and responses as needed.
}