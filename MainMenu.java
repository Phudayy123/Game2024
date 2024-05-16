package SnakeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import CSDL.PeopleDAO;
import CSDL.runDatabase;

public class MainMenu extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainMenu() {
        setTitle("Main Menu");
        setSize(418, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));            

        JButton playButton = new JButton("Play Game");
        JButton leaderboardButton = new JButton("Leader Board");
        JButton InstructionsButton = new JButton("Game Instructions");
        
        playButton.setMaximumSize(new Dimension(200, 30));
        leaderboardButton.setMaximumSize(new Dimension(200, 30));
        InstructionsButton.setMaximumSize(new Dimension(200,30));
        
        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        InstructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thêm các nút vào panel
        buttonPanel.add(playButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(InstructionsButton);

        // Bao bọc buttonPanel trong một panel khác sử dụng GridBagLayout để canh giữa
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(buttonPanel, gbc);
        add(centerPanel);       
        
        //Xoá người có điểm bằng 0 khi ấn nút tắt MainMenu 
        addWindowListener((WindowListener) new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {              
                PeopleDAO dao = PeopleDAO.getInstance();
                dao.deletePlayersWithZeroScore();
            }
        });
        
        //3 sự kiện khi ấn nút
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();            
               new FrameScreen();
               
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDatabase rd = new runDatabase();
                rd.LeaderBoard();;
            }
        });
        
        InstructionsButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				new GameInstructions();				
			}
		});
        
        setVisible(true);
    }    
}
