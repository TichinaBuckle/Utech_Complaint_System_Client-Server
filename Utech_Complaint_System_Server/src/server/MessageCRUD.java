/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package server;

import domain.Message;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageCRUD {
    private DataSource dataSource;

    public MessageCRUD() {
        dataSource = DatabaseConnection.getDataSource();
    }

    public boolean addMessage(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO message (complaint_id, student_id, staff_id, user_type, message, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message.getComplaintId());
            preparedStatement.setString(2, message.getStudentSenderId());
            preparedStatement.setString(3, message.getStaffSenderId());
            preparedStatement.setString(4, message.getSenderType());
            preparedStatement.setString(5, message.getMessage());
            preparedStatement.setTimestamp(6, new Timestamp(message.getTimestamp().getTime()));
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Message> getMessagesByComplaintId(int complaintId) {
        List<Message> messages = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM message WHERE complaint_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, complaintId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Message message = new Message(
                        resultSet.getInt("message_id"),
                        resultSet.getInt("complaint_id"),
                        resultSet.getString("student_id"),
                        resultSet.getString("staff_id"),
                        resultSet.getString("user_type"),
                        resultSet.getString("message"),
                        resultSet.getTimestamp("timestamp")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public boolean deleteMessagesByComplaintId(int complaintId) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM message WHERE complaint_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, complaintId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /*
    // Test
    public static void main(String[] args) {
        // Initialize MessageCRUD
        MessageCRUD messageCRUD = new MessageCRUD();

        // Create a new Message
        Message newMessage = new Message(0, 3, "ST001", null, "student", "This is a test message.", new Date());
        boolean isCreated = messageCRUD.addMessage(newMessage);
        System.out.println("Message creation: " + (isCreated ? "Success" : "Failed"));

        // Retrieve all Messages by complaintId
        List<Message> messages = messageCRUD.getMessagesByComplaintId(3);
        System.out.println("All Messages:");
        for (Message message : messages) {
            System.out.println(message);
        }

        // Delete Messages by complaintId
        //boolean isDeleted = messageCRUD.deleteMessagesByComplaintId(3);
        //System.out.println("Message deletion: " + (isDeleted ? "Success" : "Failed"));
    }*/
}
