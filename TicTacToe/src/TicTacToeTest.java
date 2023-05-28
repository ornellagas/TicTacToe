import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;

class TicTacToeTest {

    private TicTacToe ttt;

    @BeforeEach
    void setUp() {
        ttt = new TicTacToe();
        ttt.initApp();
    }

    @Test
    void testOpenMenu() {
        ttt.openMenu();
        assertTrue(ttt.startBtn.isVisible());     // Check if start and quit buttons are visible
        assertTrue(ttt.quitBtn.isVisible());      // Check if all buttons on the game board are invisible
        for (JButton button : ttt.buttons) {
            assertFalse(button.isVisible());
        }
    }

    @Test
    void testStartGame() {
        ttt.startGame();
        for (JButton button : ttt.buttons) {      // Check if all buttons on the game board are visible
            assertNotNull(button);
            assertTrue(button.isVisible());
        }
    }

    @Test
    void testRestart() {                          // Simulate the game state before restart
        ttt.frame.getContentPane().add(ttt.startBtn);
        ttt.startBtn.setVisible(false);
        ttt.quitBtn.setVisible(false);
        ttt.buttons[0] = new JButton();

        ttt.restart();                            // Call the restart method

        // Verify the game state after restart
        assertNull(ttt.frame.getContentPane().getComponent(0));
        // Verify other assertions for the game state after restart
    }

    @Test
    void testFirstTurn() {
        ttt.firstTurn();
        assertTrue(ttt.textField.getText().equals("X") || ttt.textField.getText().equals("O"));      // Check if the textField displays either "X" or "O"
    }

    @Test
    void testWincheck_NoWin() {               // Set up the game board with no winning combination
        for (int i = 0; i < 100; i++) {
            if (i != 0 && i % 10 != 0) {
                ttt.buttons[i] = new JButton();
                ttt.buttons[i].setText("X");
            }
        }
        ttt.wincheck();
        assertEquals("", ttt.textField.getText());              // Check if no winner is declared
    }

    @Test
    void testWincheck_WinHorizontal() {       // Set up the game board with a horizontal winning combination
        ttt.buttons[1] = new JButton();
        ttt.buttons[1].setText("X");
        ttt.buttons[2] = new JButton();
        ttt.buttons[2].setText("X");
        ttt.buttons[3] = new JButton();
        ttt.buttons[3].setText("X");
        ttt.buttons[4] = new JButton();
        ttt.buttons[4].setText("X");
        ttt.wincheck();
        assertEquals("X wins", ttt.textField.getText());        // Check if "X" is declared as the winner
    }

    @Test
    void testWincheck_WinVertical() {         // Testing the game board with a vertical winning combination
        ttt.buttons[11] = new JButton();
        ttt.buttons[11].setText("O");
        ttt.buttons[21] = new JButton();
        ttt.buttons[21].setText("O");
        // Add additional setup if needed
    }
}