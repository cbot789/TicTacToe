package game;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

import javax.swing.*;

public class TicTacToe extends JFrame {

	  JFrame frame = new JFrame("Game");                  
	  JButton[][] board = new JButton[3][3];
	  boolean[][] pressedButtons= new boolean[3][3];
	  int[][] isO=new int[3][3]; //1 for O 2 for X
	  JButton resetBoard = new JButton("Reset");
	  JLabel message = new JLabel();
	  int moves;
	  ImageIcon marker;
	  boolean player1Turn;
	  boolean gameEnd;
	
	
	public TicTacToe() {
		super();
		frame.setSize(800, 800);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public class Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource().equals(resetBoard)) {
				int i,j;
				changeTurn();
				changeTurn();
				gameEnd=false;
				moves=0;
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						board[i][j].setIcon(null);
						isO[i][j]=0;
						pressedButtons[i][j]=false;
					}
					}
			}else if (gameEnd==false){
				int i,j;
				for(i=0; i<3; i++) {
					for(j=0; j<3; j++) {
						if(e.getSource().equals(board[i][j])) {
							if(pressedButtons[i][j]==false) {
						
						board[i][j].setIcon(marker);
						board[i][j].repaint();
						pressedButtons[i][j]=true;
						if(player1Turn) {
						isO[i][j]=1;
						}
						else {
							isO[i][j]=2;
						}
						changeTurn();
						checkWin();
							
						
						if(moves>=9) {
							message.setText("It is a draw!");
						}
						}
						}
					}
				}
			}
			
		}
		
	}
	
	public void checkWin() {
		if(1==(isO[0][0]&isO[0][1]&isO[0][2])) {
			endGame(1);
		}
		else if(1==(isO[1][0]&isO[1][1]&isO[1][2])) {
			endGame(1);
		}
		else if(1==(isO[2][0]&isO[2][1]&isO[2][2])) {
			endGame(1);
		}
		else if(1==(isO[0][0]&isO[1][0]&isO[2][0])) {
			endGame(1);
		}
		else if(1==(isO[0][1]&isO[1][1]&isO[2][1])) {
			endGame(1);
		}
		else if(1==(isO[0][2]&isO[1][2]&isO[2][2])) {
			endGame(1);
		}
		else if(1==(isO[0][0]&isO[1][1]&isO[2][2])) {
			endGame(1);
		}
		else if(1==(isO[2][0]&isO[1][1]&isO[0][2])) {
			endGame(1);
		}
		
		else if(2==(isO[0][0]&isO[0][1]&isO[0][2])) {
			endGame(2);
		}
		else if(2==(isO[1][0]&isO[1][1]&isO[1][2])) {
			endGame(2);
		}
		else if(2==(isO[2][0]&isO[2][1]&isO[2][2])) {
			endGame(2);
		}
		else if(2==(isO[0][0]&isO[1][0]&isO[2][0])) {
			endGame(2);
		}
		else if(2==(isO[0][1]&isO[1][1]&isO[2][1])) {
			endGame(2);
		}
		else if(2==(isO[0][2]&isO[1][2]&isO[2][2])) {
			endGame(2);
		}
		else if(2==(isO[0][0]&isO[1][1]&isO[2][2])) {
			endGame(2);
		}
		else if(2==(isO[2][0]&isO[1][1]&isO[0][2])) {
			endGame(2);
		}
		
	}
	public void endGame(int winner) {
		gameEnd=true;
		if(winner==1) { //O's win
			message.setText("O Wins!");
		}else { //X's win
			message.setText("X Wins!");
		}
		
	}
	
	public void changeTurn() {
		moves++;
	    URL url1 = TicTacToe.class.getResource("/resources/o.jpg");
	    URL url2 = TicTacToe.class.getResource("/resources/x.jpg");
		if(player1Turn) {
			marker=new ImageIcon(url2); //switching to player 2 turn
			message.setText("X's Turn");
			player1Turn=false;
		}else {
			marker=new ImageIcon(url1); //switching to player 1 turn
			message.setText("O's Turn");
			player1Turn=true;
		}
	}
	
	public void initialize() throws IOException {
		gameEnd=false;
		 resetBoard.addActionListener(new Listener());
		JPanel outerPanel= new JPanel(new BorderLayout());
		JPanel upperPanel= new JPanel(new BorderLayout());
	    JPanel gameBoard = new JPanel(new GridLayout(3,3));
	    player1Turn=true;
	    message.setText("O's Turn");
	    message.setHorizontalAlignment(SwingConstants.CENTER);
	    moves=0;
	    URL url1 = TicTacToe.class.getResource("/resources/o.jpg");
	    URL url2 = TicTacToe.class.getResource("/resources/x.jpg");
	   marker=new ImageIcon(url1); //player 1 will use O's
	   
	    gameBoard.setPreferredSize(new Dimension(600,700));
	    outerPanel.setPreferredSize(new Dimension(600,700));
	    
	    int i,j;
	   
	    
	    for(i=0; i<3; i++) {
	    	for(j=0; j<3; j++) {
	    		board[i][j]=new JButton();
	    		board[i][j].setVisible(true);
	    		board[i][j].addActionListener(new Listener());	 
	    		isO[i][j]=0;
	    		pressedButtons[i][j]=false;
	    		gameBoard.add(board[i][j]);
	    		
	    	}
	    	
	    }
	    
	    outerPanel.add(gameBoard,BorderLayout.SOUTH);
	    upperPanel.add(resetBoard,BorderLayout.NORTH);
	    upperPanel.add(message,BorderLayout.SOUTH);
	    frame.add(outerPanel,BorderLayout.SOUTH);
	    frame.add(upperPanel,BorderLayout.NORTH);
	    frame.setVisible(true);
	    repaint();
		
	}
	public static void main(String args[]) throws IOException {
		TicTacToe gui=new TicTacToe();
		gui.initialize();
	}

}
