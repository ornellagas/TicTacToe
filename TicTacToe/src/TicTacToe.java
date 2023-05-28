import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {
    Random random;
    JFrame frame;
    JPanel titlePanel;
    JPanel buttonPanel;
    JLabel textField;
    JButton[] buttons;

    JButton startBtn;
    JButton quitBtn;
    boolean player1Turn;

    TicTacToe() {
        init();
        initApp();
        openMenu(); // open main menu
    }

    public void init() {
        random = new Random();
        frame = new JFrame();
        titlePanel = new JPanel();
        buttonPanel = new JPanel();
        textField = new JLabel();
        buttons = new JButton[100]; // setting the game board (number of buttons for the game)
        startBtn = new JButton();
        quitBtn = new JButton();
    }

    public void initApp() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);       //setting size of the frame
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setForeground(Color.white);
        textField.setFont(new Font("Arial", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");

     // Setting the Start button
        startBtn.setBackground(Color.white);
        startBtn.setFont(new Font("Arial", Font.BOLD, 50));
        startBtn.setFocusable(false);
        startBtn.addActionListener(this);
        startBtn.setText("Start");

     // Setting the Quit Game button
        quitBtn.setBackground(Color.white);
        quitBtn.setFont(new Font("Arial", Font.BOLD, 50));
        quitBtn.setFocusable(false);
        quitBtn.addActionListener(this);
        quitBtn.setText("Quit Game");

        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 200);

        buttonPanel.setLayout(new GridLayout(10, 10));

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER); // Move the button panel to the center

        titlePanel.add(textField, BorderLayout.CENTER); // Move the textField  to the center
        frame.add(startBtn, BorderLayout.WEST); // Move the Start button to the left side
        frame.add(quitBtn, BorderLayout.EAST); // Move the Quit Game button to the right side

  // Make buttons visible
        startBtn.setVisible(true);
        quitBtn.setVisible(true);
    }
  // Menu display
    public void openMenu() {
        textField.setText("Tic-Tac-Toe");
        startBtn.setVisible(true);
        quitBtn.setVisible(true);

        for (int i = 0; i < 100; i++) {
            if (buttons[i] != null)
                buttons[i].setVisible(false);
        }
    }

    public void startGame() {
    	// Hide buttons on application startup
        startBtn.setVisible(false);   
        quitBtn.setVisible(false);

        for (int i = 0; i < 100; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
			buttons[i].setBackground(Color.white);
		}
	}

	public void restart() {
		frame.getContentPane().removeAll();
		init();
		initApp();
		openMenu();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getSource().equals(startBtn)) { // if btn was clicked
	        frame.remove(startBtn);
	        startGame(); // start game  
	        firstTurn(); // set first turn
	    } else if (e.getSource() == quitBtn) {
	        System.exit(0);
	    } else {
	        for (int i = 0; i < 100; i++) {
	            if (!e.getSource().equals(buttons[i])) {
	                continue;
	            }
	            if (!buttons[i].getText().equals("")) {
	                return;
	            }

	            if (player1Turn) {
	                buttons[i].setForeground(new Color(40, 70, 90));
	                buttons[i].setText("X");
	                player1Turn = false;
	                textField.setText("O");
	                wincheck();
	            } else {
	                buttons[i].setForeground(new Color(90, 20, 40));
	                buttons[i].setText("O");
	                player1Turn = true;
	                textField.setText("X");
	                wincheck();
	            }
	        }
	    }
	}
	
		public void firstTurn() {              //sets the first move, if the first player starts, the button is filled with X, if not, then O
		    if (random.nextInt(2) == 0) {
		        player1Turn = true;
		        textField.setText("X");
		    } else {
		        player1Turn = false;
		        textField.setText("O");
		    }
		}

	public void wincheck() { 

		for (int y = 0; y < 10; y++) {
			for (int x = 0; x < 10; x++) {
				int btnNum = y*10 + x;
				try {
				    if (buttons[btnNum].getText().equals(buttons[btnNum + 1].getText()) && buttons[btnNum + 1].getText().equals(buttons[btnNum + 2].getText()) && buttons[btnNum + 2].getText().equals(buttons[btnNum + 3].getText()) && !buttons[btnNum].getText().equals("")) {
				        System.out.println("yes");
				        win(buttons[btnNum].getText(), btnNum, btnNum + 1, btnNum + 2, btnNum + 3);
				        return;
				    }
				    if (buttons[btnNum].getText().equals(buttons[btnNum + 10].getText()) && buttons[btnNum + 10].getText().equals(buttons[btnNum + 20].getText()) && buttons[btnNum + 20].getText().equals(buttons[btnNum + 30].getText()) && !buttons[btnNum].getText().equals("")) {
				        System.out.println("yes");
				        win(buttons[btnNum].getText(), btnNum, btnNum + 10, btnNum + 20, btnNum + 30);
				        return;
				    }
				    if (buttons[btnNum].getText().equals(buttons[btnNum + 11].getText()) && buttons[btnNum + 11].getText().equals(buttons[btnNum + 22].getText()) && buttons[btnNum + 22].getText().equals(buttons[btnNum + 33].getText()) && !buttons[btnNum].getText().equals("")) {
				        System.out.println("yes");
				        win(buttons[btnNum].getText(), btnNum, btnNum + 11, btnNum + 22, btnNum + 33);
				        return;
				    }
				    if (buttons[btnNum].getText().equals(buttons[btnNum + 9].getText()) && buttons[btnNum + 9].getText().equals(buttons[btnNum + 18].getText()) && buttons[btnNum + 18].getText().equals(buttons[btnNum + 27].getText()) && !buttons[btnNum].getText().equals("")) {
				        System.out.println("yes");
				        win(buttons[btnNum].getText(), btnNum, btnNum + 9, btnNum + 18, btnNum + 27);
				        return;
				    }
				    if (buttons[btnNum].getText().equals(buttons[btnNum + 12].getText()) && buttons[btnNum + 12].getText().equals(buttons[btnNum + 24].getText()) && buttons[btnNum + 24].getText().equals(buttons[btnNum + 36].getText()) && !buttons[btnNum].getText().equals("")) {
				        System.out.println("yes");
				        win(buttons[btnNum].getText(), btnNum, btnNum + 12, btnNum + 24, btnNum + 36);
				        return;
				    }
				} catch (Exception e) {
				    //
				}
			  }
			}
		}


	public void win(String side, int a,int b,int c, int d) {   // Turns the winning field green
		buttons[a].setBackground(Color.GREEN);
		buttons[b].setBackground(Color.GREEN);
		buttons[c].setBackground(Color.GREEN);
		buttons[d].setBackground(Color.GREEN);

		for(int i=0;i<100;i++) {
			buttons[i].setEnabled(false);
		}
		textField.setText(side + " wins");

		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
		Runnable runnable = () -> {
			restart();
			executorService.shutdown();
		};

		executorService.scheduleWithFixedDelay(runnable, 2, 1, TimeUnit.SECONDS);

	}

	

}