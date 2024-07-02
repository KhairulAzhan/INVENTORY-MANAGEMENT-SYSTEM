
/**
 * Write a description of class a here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryManagementSystem {
    private JFrame frame;
    private JTextField itemNameField;
    private JTextField itemQuantityField;
    private JComboBox<String> itemTypeComboBox;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                InventoryManagementSystem window = new InventoryManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public InventoryManagementSystem() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Tambun Safety Equipment Inventory");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel lblItemName = new JLabel("Item Name:");
        inputPanel.add(lblItemName);

        itemNameField = new JTextField();
        inputPanel.add(itemNameField);
        itemNameField.setColumns(10);

        JLabel lblItemQuantity = new JLabel("Item Quantity:");
        inputPanel.add(lblItemQuantity);

        itemQuantityField = new JTextField();
        inputPanel.add(itemQuantityField);
        itemQuantityField.setColumns(10);

        JLabel lblItemType = new JLabel("Item Type:");
        inputPanel.add(lblItemType);

        itemTypeComboBox = new JComboBox<>(new String[] {
            "Safety Equipment", "Life Vests", "Lifeguard Chairs", "Rescue Tubes", "First Aid Kits",
            "Automated External Defibrillators (AEDs)", "Safety Signs and Notices", "Emergency Communication Systems",
            "Pool Safety Covers", "Barrier Fences and Gates"
        });
        inputPanel.add(itemTypeComboBox);

        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Add Item");
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Update Item");
        buttonPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Delete Item");
        buttonPanel.add(btnDelete);

        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Item Name", "Quantity", "Type"}
        );
        inventoryTable = new JTable(tableModel);
        frame.getContentPane().add(new JScrollPane(inventoryTable), BorderLayout.CENTER);

        // Action listeners
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateItem();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteItem();
            }
        });

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu mnFile = new JMenu("File");
        menuBar.add(mnFile);

        JMenuItem mntmSave = new JMenuItem("Save");
        mnFile.add(mntmSave);

        JMenuItem mntmLoad = new JMenuItem("Load");
        mnFile.add(mntmLoad);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mnFile.add(mntmExit);

        mntmExit.addActionListener(e -> System.exit(0));
    }

    private void addItem() {
        String itemName = itemNameField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(itemQuantityField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Quantity must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String itemType = (String) itemTypeComboBox.getSelectedItem();
        tableModel.addRow(new Object[] {itemName, quantity, itemType});
    }

    private void updateItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.setValueAt(itemNameField.getText(), selectedRow, 0);
            tableModel.setValueAt(itemQuantityField.getText(), selectedRow, 1);
            tableModel.setValueAt(itemTypeComboBox.getSelectedItem(), selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an item to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteItem() {
        int selectedRow = inventoryTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Select an item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

