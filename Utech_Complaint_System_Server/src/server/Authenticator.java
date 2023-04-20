package server;

import domain.Account;

public class Authenticator {
    private AccountCRUD accountCRUD;

    public Authenticator() {
        accountCRUD = new AccountCRUD();
    }

    // Authenticates a student using id and password
    public boolean authenticateStudent(String studentId, String password) {
        Account account = accountCRUD.getAccountById(studentId);
        return account != null && account.getPassword().equals(password) && account.getRole().equals("student");
    }

    // Authenticates a staff member (advisor or supervisor) using id and password
    public boolean authenticateStaff(String staffId, String password) {
        Account account = accountCRUD.getAccountById(staffId);
        return account != null && account.getPassword().equals(password)
                && (account.getRole().equals("advisor") || account.getRole().equals("supervisor"));
    }

    // Gets the role of a staff member based on their identifier
    public String getStaffRole(String staffId) {
        Account account = accountCRUD.getAccountById(staffId);
        return account != null ? account.getRole() : null;
    }
}
