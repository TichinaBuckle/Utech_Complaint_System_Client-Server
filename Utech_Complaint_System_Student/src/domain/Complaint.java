package domain;

import java.util.Date;

public class Complaint {

	// local attributes
	private int complaintId;
	private String studentId;
	private String staffId;
	private String category;
	private String description;
	private String status;
	private Date createAt;
	private Date updatedAt;
		
	// constructor
		
	// default
	public Complaint() {
		this.complaintId = 0000;
		this.studentId = "";
		this.staffId = "";
		this.category = "";
		this.description = "";
		this.status = "";
		this.createAt = new Date();
		this.updatedAt = new Date();
	}
		
	// primary
	public Complaint(int complaintId, String studentId, String staffId, String category, String description,
			String status, Date createAt, Date updatedAt) {
		super();
		this.complaintId = complaintId;
		this.studentId = studentId;
		this.staffId = staffId;
		this.category = category;
		this.description = description;
		this.status = status;
		this.createAt = createAt;
		this.updatedAt = updatedAt;
	}
	
	// primary 2
	public Complaint(String studentId, String category, String description, String status, String staffId) {
	    this.studentId = studentId;
	    this.category = category;
	    this.description = description;
	    this.status = status;
	    this.staffId = staffId;
	    this.createAt = new Date();
	    this.updatedAt = new Date();
	}
		
	// copy
	public Complaint(Complaint complaint) {
		super();
		this.complaintId = complaint.complaintId;
		this.studentId = complaint.studentId;
		this.staffId = complaint.staffId;
		this.category = complaint.category;
		this.description = complaint.description;
		this.status = complaint.status;
		this.createAt = complaint.createAt;
		this.updatedAt = complaint.updatedAt;
	}
		
	// getters and setters
	public int getComplaintId() {
		return complaintId;
	}
	
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	public String getStaffId() {
		return staffId;
	}
	
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// toString
	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", studentId=" + studentId + ", staffId=" + staffId
				+ ", category=" + category + ", description=" + description + ", status=" + status + ", createAt="
				+ createAt + ", updatedAt=" + updatedAt + "]";
	}	
}

