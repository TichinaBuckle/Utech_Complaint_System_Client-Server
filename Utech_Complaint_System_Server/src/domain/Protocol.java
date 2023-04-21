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
	    public static String createTrackComplaintRequest(String studentId) {
	        return "TRACK_COMPLAINT_REQUEST:" + studentId;
	    }
	    
	    public static String createTrackComplaintResponse(List<Complaint> complaints, Map<Integer, String> advisorFullNames) {
	        StringBuilder response = new StringBuilder("TRACK_COMPLAINT_RESPONSE");
	        for (Complaint complaint : complaints) {
	            response.append(":");
	            response.append(complaint.getComplaintId());
	            response.append("|");
	            response.append(complaint.getCategory());
	            response.append("|");
	            response.append(complaint.getDescription());
	            response.append("|");
	            response.append(complaint.getStatus());
	            response.append("|");
	            response.append(advisorFullNames.get(complaint.getStaffId())); // Use staffId as the key
	            response.append("|");
	            response.append(complaint.getCreateAt());
	            response.append("|");
	            response.append(complaint.getUpdatedAt());
	        }
	        return response.toString();
	    }

	    public static Pair<List<Complaint>, Map<String, String>> parseTrackComplaintResponse(String response) {
	        String[] parts = response.split(":");
	        List<Complaint> complaints = new ArrayList<>();
	        Map<String, String> advisorFullNames = new HashMap<>();

	        for (int i = 1; i < parts.length; i++) {
	            String[] complaintInfo = parts[i].split("\\|");
	            if (complaintInfo.length < 7) {
	                System.err.println("Skipping malformed complaint data: " + parts[i]);
	                continue;
	            }
	            Complaint complaint = new Complaint();

	            int complaintId = Integer.parseInt(complaintInfo[0]);
	            complaint.setComplaintId(complaintId);
	            complaint.setCategory(complaintInfo[1]);
	            complaint.setDescription(complaintInfo[2]);
	            complaint.setStatus(complaintInfo[3]);
	            complaint.setStaffId(complaintInfo[4]); // staffId is a string
	            complaint.setCreateAt(parseDate(complaintInfo[5]));
	            complaint.setUpdatedAt(parseDate(complaintInfo[6]));

	            complaints.add(complaint);
	            advisorFullNames.put(complaintInfo[4], complaintInfo[4]); // Use staffId as the key
	        }

	        return new Pair<>(complaints, advisorFullNames);
	    }

	    private static Date parseDate(String dateString) {
	        if (dateString == null || dateString.equals("null")) {
	            return null;
	        }
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	        try {
	            return sdf.parse(dateString);
	        } catch (ParseException e) {
	            System.err.println("Error parsing date: " + e.getMessage());
	            return null;
	        }
	    }
	    
	 // Add more methods for other requests and responses as needed.
}