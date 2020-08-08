package play;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Mine {
	static final int SIZE = 20;
    static String[][] map = new String[SIZE][SIZE];
    static JButton[][] button = new JButton[SIZE][SIZE];
    static JButton restart;
    static JButton end;
    static JPanel panelN;
    static JPanel panelC;
    static JLabel labelMessage;
    static int mineNum=50; // ���ڰ���
    static int count=0;
    static int count2=0;
    static int cnt=0;
    static class MyFrame extends JFrame implements ActionListener{
        public MyFrame(String title) {
            super(title);
            setLayout(new BorderLayout());
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setSize(900,950);

            panelC = new JPanel();
            panelN = new JPanel();
            restart = new JButton("재시작");
            end = new JButton("끝내기");
            labelMessage = new JLabel("지뢰 찾기");
            labelMessage.setForeground(Color.BLACK);
            labelMessage.setPreferredSize(new Dimension(700,50));
            labelMessage.setHorizontalAlignment(JLabel.CENTER);
            labelMessage.setFont(new Font("D2Coding", Font.BOLD, 20));
            restart.setPreferredSize(new Dimension(90,50));
            restart.addActionListener(this);
            end.setPreferredSize(new Dimension(90,50));
            end.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
            panelN.add(labelMessage);
            panelN.add(restart);
            panelN.add(end);
            panelN.setPreferredSize(new Dimension(900,50));
            panelN.setBackground(Color.DARK_GRAY);
            panelC.setPreferredSize(new Dimension(900,900));
            panelC.setLayout(new GridLayout(SIZE,SIZE));
            
			mineMap();
			
            add("North",panelN);
            add("Center",panelC);
            pack();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
        	setVisible(false);
        	new MyFrame("지뢰 찾기");
		}
        public void gameOver() {
            for(int i=0;i<SIZE;i++) {
                for(int j=0;j<SIZE;j++) {
                    button[i][j].setEnabled(false);
                    button[i][j].setText(map[i][j]);
                    labelMessage.setText("지뢰 찾기 실패");              
                }
            }
            JOptionPane.showMessageDialog(null, "지뢰 찾기 실패");
            setVisible(false);
            new MyFrame("지뢰 찾기");
        }
        public void startMap() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    button[i][j]=new JButton("");
                    panelC.add(button[i][j]);
                    button[i][j].setPreferredSize(new Dimension(30,30));
                    button[i][j].setFont(new Font("D2Coding", Font.BOLD, 20));
                    String c = map[i][j];
                    button[i][j].addMouseListener(new MouseAdapter() {
                    	public void mouseClicked(MouseEvent e) {
                    		JButton btn = (JButton)e.getSource();
                    		if(e.getButton()==3) {								 // ��Ŭ��
                    			if(btn.getText() == "") {
                    				btn.setText("!");
                    				cnt++; 										 // !����
                    				if(cnt==mineNum) {
                    					JOptionPane.showMessageDialog(null, "성공!!");
                    				}
                    			}
                    			else if(btn.getText() == "!") {
                    				btn.setText("?");
                    				cnt--;
                    			}
                     			else if(btn.getText() == "?") btn.setText("");
                    		}else if(e.getButton()==1){ 						 // ��Ŭ��
                    			if(c == "*") gameOver();
                                else if(btn.getText() == ""){
                                	btn.setText(c);
                                	btn.setEnabled(false);
                                	if(click(btn)) checkMine(btn);
                                }
                    		}else{  											 // ��Ŭ��
                    			if(btn.getText() != ""
                    					+ "") {
                    				surroundCheckMine(btn);
                    			}
                    		}
                    	}
					});
                }
            }
        }
        public int getX( JButton btn ) {
        	for(int i=0; i<button.length; i++) {
        		for(int j=0; j<button[i].length; j++) {
        			if(btn == button[i][j])
        				return i;
        		}
        	}
        	return 0;
        }
        public int getY( JButton btn ) {
        	for(int i=0; i<button.length; i++) {
        		for(int j=0; j<button[i].length; j++) {
        			if(btn == button[i][j])
        				return j;
        		}
        	}
        	return 0;
        }
        public boolean click(JButton btn) { // �ֺ��� 0�� ���� ��
        	int x = getX( btn );
            int y = getY( btn );
        	int[] dx = {1,-1,0,0,1,1,-1,-1};
        	int[] dy = {0,0,1,-1,1,-1,1,-1};
        	for(int k=0;k<8;k++) {
        		if (boundary(x+dx[k], y+dy[k])) {
        			String c2 = map[x+dx[k]][y+dy[k]];
        			if(c2 !="*") {	
        				if(button[x+dx[k]][y+dy[k]].getText() == "") {
        					if(map[x+dx[k]][y+dy[k]].equals("0")) return true;
        				}
        			}
        		}
        	}
        	return false;
        }
        public void checkMine( JButton btn ) {
        	int x = getX( btn );
            int y = getY( btn );
        	int[] dx = {1,-1,0,0,1,1,-1,-1};
        	int[] dy = {0,0,1,-1,1,-1,1,-1};
        	for(int k=0;k<8;k++) {
        		if (boundary(x+dx[k], y+dy[k])) {
        			String c2 = map[x+dx[k]][y+dy[k]];
        			if(c2 !="*") {	
        				if(button[x+dx[k]][y+dy[k]].getText() == "") {
        					if(!(map[x+dx[k]][y+dy[k]].equals("0"))) {
            					button[x+dx[k]][y+dy[k]].setText(c2);
                				button[x+dx[k]][y+dy[k]].setEnabled(false);
            				}else {
            					button[x+dx[k]][y+dy[k]].setText(c2);
            					button[x+dx[k]][y+dy[k]].setEnabled(false);
            					checkMine(button[x+dx[k]][y+dy[k]]);
            				}
        				}
        			}
        		}
        	}
        }
        public void surroundCheckMine( JButton btn ) { 		
        	int x = getX( btn );
            int y = getY( btn );
        	int[] dx = {1,-1,0,0,1,1,-1,-1};
        	int[] dy = {0,0,1,-1,1,-1,1,-1};
        	for(int k=0;k<8;k++) {
        		 if (boundary(x+dx[k], y+dy[k])) {
        			 String c2 = map[x+dx[k]][y+dy[k]];
        			 if(c2 == "*") count2++;
        			 if(button[x+dx[k]][y+dy[k]].getText() == "!") count++;
        			 if(c2 !="*" && count==count2) {
        				 button[x+dx[k]][y+dy[k]].setText(c2);
        				 button[x+dx[k]][y+dy[k]].setEnabled(false);
        			 }
        			 if(map[x+dx[k]][y+dy[k]].equals("0")) {
     					button[x+dx[k]][y+dy[k]].setText(c2);
         				button[x+dx[k]][y+dy[k]].setEnabled(false);
         				checkMine(button[x+dx[k]][y+dy[k]]);
     				}
        		 }
        	}
        	count=0; count2=0;
        }
        
        public boolean boundary(int x, int y) {
            return x>=0 && y>=0 && x<SIZE && y<SIZE;
        }
        public void init() {
            int[] dx = {1,-1,0,0,1,1,-1,-1};
            int[] dy = {0,0,1,-1,1,-1,1,-1};

            for(int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    map[i][j] = "0";
                }
            }
            //set mine
            int mine = mineNum; // ���� ����
            while(mine > 0) {
                int x=(int)(Math.random() * SIZE);
                int y=(int)(Math.random() * SIZE);
                if(map[x][y].equals("0")) {
                    map[x][y]="*";
                    mine--;
                }
            }
            for(int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if(!(map[i][j].equals("*"))) {
                        int cnt = 0;
                        for (int k = 0; k < 8; k++) {
                            int nx = j + dx[k], ny = i + dy[k];
                            if (boundary(nx, ny) && map[ny][nx].equals("*")) cnt++;
                        }
                        map[i][j]=String.valueOf(cnt);
                    }
                }
            }
        }
        public void mineMap() {
            init();
            startMap();
        }
    }
    public static void main(String[] args) {
       new MyFrame("지뢰 찾기");

    }
}