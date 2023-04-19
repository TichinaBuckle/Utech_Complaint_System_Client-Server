package server;

import domain.Complaint;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComplaintCRUD {
    private final DataSource dataSource;

    public ComplaintCRUD() {
        this.dataSource = DatabaseConnection.getDataSource();
    }

    // Add Complaint to database
    public boolean addComplaint(Complaint complaint) {
        String query = "INSERT INTO complaint (student_id, advisor_id, category, description, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, complaint.getStudentId());
            preparedStatement.setString(2, complaint.getStaffId());
            preparedStatement.setString(3, complaint.getCategory());
            preparedStatement.setString(4, complaint.getDescription());
            preparedStatement.setString(5, complaint.getStatus());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve Complaints from database by student_id
    public List<Complaint> getComplaintsByStudentId(String studentId) {
        List<Complaint> complaintList = new ArrayList<>();
        String query = "SELECT * FROM complaint WHERE student_id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String advisorId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                Complaint complaint = new Complaint(id, studentId, advisorId, category, description, status, createdAt, updatedAt);
                complaintList.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaintList;
    }

    // Update Complaint
    public boolean updateComplaint(Complaint complaint) {
        String query = "UPDATE complaint SET advisor_id = ?, category = ?, description = ?, status = ?, updated_at = ? WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, complaint.getStaffId());
            preparedStatement.setString(2, complaint.getCategory());
            preparedStatement.setString(3, complaint.getDescription());
            preparedStatement.setString(4, complaint.getStatus());
            preparedStatement.setTimestamp(5, new Timestamp(complaint.getUpdatedAt().getTime()));
            preparedStatement.setInt(6, complaint.getComplaintId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Complaint
    public boolean deleteComplaint(int id) {
        String query = "DELETE FROM complaint WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Retrieve a single Complaint by its id
    public Complaint getComplaintById(int complaintId) {
        Complaint complaint = null;
        String query = "SELECT * FROM complaint WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, complaintId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String advisorId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                complaint = new Complaint(complaintId, studentId, advisorId, category, description, status, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaint;
    }

    // Retrieve all Complaints from the database
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaintList = new ArrayList<>();
        String query = "SELECT * FROM complaint";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String studentId = resultSet.getString("student_id");
                String advisorId = resultSet.getString("advisor_id");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                String status = resultSet.getString("status");
                Date createdAt = resultSet.getTimestamp("created_at");
                Date updatedAt = resultSet.getTimestamp("updated_at");

                Complaint complaint = new Complaint(id, studentId, advisorId, category, description, status, createdAt, updatedAt);
                complaintList.add(complaint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaintList;
    }
    
    /*
    //Test
    public static void main(String[] args) {
        // Initialize ComplaintCRUD
        ComplaintCRUD complaintCRUD = new ComplaintCRUD();

        // Create a new Complaint
        Complaint newComplaint = new Complaint(0, "ST001", null, "general query", "I have a question about my course registration.", "open", null, null);
        boolean isCreated = complaintCRUD.addComplaint(newComplaint);
        System.out.println("Complaint creation: " + (isCreated ? "Success" : "Failed"));

        // Retrieve all Complaints
        List<Complaint> complaints = complaintCRUD.getAllComplaints();
        System.out.println("All Complaints:");
        for (Complaint complaint : complaints) {
            System.out.println(complaint);
        }

        // Update a Complaint
        Complaint complaintToUpdate = complaints.get(0);
        complaintToUpdate.setDescription("I have a question about my course registration and fees.");
        boolean isUpdated = complaintCRUD.updateComplaint(complaintToUpdate);
        System.out.println("Complaint update: " + (isUpdated ? "Success" : "Failed"));

        // Get a single Complaint by its id
        Complaint singleComplaint = complaintCRUD.getComplaintById(complaintToUpdate.getComplaintId());
        System.out.println("Single Complaint:");
        System.out.println(singleComplaint);

        // Delete a Complaint
        //boolean isDeleted = complaintCRUD.deleteComplaint(complaintToUpdate.getComplaintId());
        //System.out.println("Complaint deletion: " + (isDeleted ? "Success" : "Failed"));
    }*/
    
}
    