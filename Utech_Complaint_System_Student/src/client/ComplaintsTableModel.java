package client;

import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import domain.Complaint;

class ComplaintsTableModel extends AbstractTableModel {
	private List<Complaint> complaints;
    private Map<String, String> advisorFullNames;
    private String[] columnNames = {"ID", "Category", "Description", "Status", "Advisor", "Created At", "Updated At"};

    public ComplaintsTableModel(List<Complaint> complaints, Map<String, String> advisorFullNames) {
        this.complaints = complaints;
        this.advisorFullNames = advisorFullNames;
    }

    @Override
    public int getRowCount() {
        return complaints.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Complaint complaint = complaints.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return complaint.getComplaintId();
            case 1:
                return complaint.getCategory();
            case 2:
                return complaint.getDescription();
            case 3:
                return complaint.getStatus();
            case 4:
                String advisorName = advisorFullNames.get(complaint.getStaffId());
                System.out.println("Advisor name for complaint " + complaint.getComplaintId() + ": " + advisorName); // Add this print statement
                return advisorName;
            case 5:
                return complaint.getCreateAt();
            case 6:
                return complaint.getUpdatedAt();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}