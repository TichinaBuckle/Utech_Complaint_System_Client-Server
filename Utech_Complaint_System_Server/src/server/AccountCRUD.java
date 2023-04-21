/*
Aurthors: Tichina Buckle, Khi Lewis, Aviel Reid and Shemar Williams
*/

package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import domain.Account;

public class AccountCRUD {
    private DataSource dataSource;

    public AccountCRUD() {
        this.dataSource = DatabaseConnection.getDataSource();
    }

    // Add Account to database
    public boolean addAccount(Account account) {
        String query = "INSERT INTO account (id, first_name, last_name, email, contact_number, password, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, account.getId());
            preparedStatement.setString(2, account.getFirstName());
            preparedStatement.setString(3, account.getLastName());
            preparedStatement.setString(4, account.getEmail());
            preparedStatement.setString(5, account.getContactNumber());
            preparedStatement.setString(6, account.getPassword());
            preparedStatement.setString(7, account.getRole());

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve Account from database by id
    public Account getAccountById(String id) {
        String query = "SELECT * FROM account WHERE id = ?";
        Account account = null;

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                account = new Account(id, firstName, lastName, email, contactNumber, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    // Retrieve all Accounts from database
    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM account";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String contactNumber = resultSet.getString("contact_number");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");

                Account account = new Account(id, firstName, lastName, email, contactNumber, password, role);
                accountList.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accountList;
    }

    // Update Account information in database
    public boolean updateAccount(Account account) {
        String query = "UPDATE account SET first_name = ?, last_name = ?, email = ?, contact_number = ?, password = ?, role = ? WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, account.getFirstName());
            preparedStatement.setString(2, account.getLastName());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getContactNumber());
            preparedStatement.setString(5, account.getPassword());
            preparedStatement.setString(6, account.getRole());
            preparedStatement.setString(7, account.getId());

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete Account from database
    public boolean deleteAccount(String id) {
        String query = "DELETE FROM account WHERE id = ?";

        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /*
    // Test
    public static void main(String[] args) {
        AccountCRUD accountCRUD = new AccountCRUD();

        // Test addAccount
        Account studentAccount = new Account("ST001", "John", "Doe", "john.doe@example.com", "1234567890", "password123", "student");
        Account supervisorAccount = new Account("SP001", "Jane", "Smith", "jane.smith@example.com", "9876543210", "password456", "supervisor");
        Account advisorAccount = new Account("AD001", "Sara", "Johnson", "sara.johnson@example.com", "5551234567", "password789", "advisor");

        System.out.println("Added student account: " + accountCRUD.addAccount(studentAccount));
        System.out.println("Added supervisor account: " + accountCRUD.addAccount(supervisorAccount));
        System.out.println("Added advisor account: " + accountCRUD.addAccount(advisorAccount));

        // Test getAccountById
        Account retrievedStudentAccount = accountCRUD.getAccountById("ST001");
        System.out.println("Retrieved student account: " + retrievedStudentAccount);

        // Test getAllAccounts
        System.out.println("All accounts:");
        for (Account account : accountCRUD.getAllAccounts()) {
            System.out.println(account.getId() + " " + account.getFirstName() + " " + account.getLastName());
        }

        // Test updateAccount
        studentAccount.setFirstName("James");
        System.out.println("Updated student account: " + accountCRUD.updateAccount(studentAccount));
        Account retrievedUpdatedStudentAccount = accountCRUD.getAccountById("ST001");
        System.out.println("Retrieved updated student account: " + retrievedUpdatedStudentAccount);

        // Test deleteAccount
        System.out.println("Deleted supervisor account: " + accountCRUD.deleteAccount("SP001"));
        System.out.println("All accounts after deletion:");
        for (Account account : accountCRUD.getAllAccounts()) {
            System.out.println(account.getId() + " " + account.getFirstName() + " " + account.getLastName());
        }
    }*/
    
}