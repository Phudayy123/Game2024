package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import CSDL.People;
import CSDL.PeopleDAO;

public class EntryForm extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
    private JButton submitButton;
    private JButton cancelButton;
    private static String playerName;
    
    public EntryForm() {
    	
    }
    
	public EntryForm(JFrame parent) {
        super(parent, "Enter Name", true);
        setupUI();
        setupActions();
    }
	
    public static String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		EntryForm.playerName = playerName;
	}

	private void setupUI() {
		setTitle("Enter Name");
        setSize(300, 120);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        // Tạo panel cho nhập tên
        JPanel inputPanel = new JPanel();
        nameField = new JTextField(20);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        // Tạo panel cho các nút
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void setupActions() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String name= nameField.getText().trim();
                if (!name.isEmpty()) {
                    playerName = name;                	        
                	PeopleDAO pd= new PeopleDAO();                	
                	boolean NameExists = pd.nameExists(name);
                	if (NameExists) {                        
                        JOptionPane.showMessageDialog(EntryForm.this,
                                "The name already exists, please enter another name.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        People t = new People();
                        t.setTenNguoiChoi(name);
                        pd.insert(t);
                        
                        dispose();                    
                        MainMenu mm= new MainMenu();                    
                        mm.setVisible(true);
                    }} else {
                    JOptionPane.showMessageDialog(EntryForm.this,
                            "Please enter a name.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    
}
