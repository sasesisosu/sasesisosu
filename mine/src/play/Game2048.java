package play;

import java.util.Scanner;

public class Game2048 {
	static int[][] map = new int[4][4];
	static int[][] map2 = new int[4][4];
	static int score=0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(true) {
			while(true) { // 2 or 4를 랜덤으로 배치
				int x=(int)(Math.random()*4);
				int y=(int)(Math.random()*4);
				int randNum = (int)(Math.random()*2);
				if(map[x][y]==0) {
					if(randNum==0) map[x][y]=2;
					else 		   map[x][y]=4;
					break;
				}
			}
			screenMap();
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) map2[i][j]=map[i][j];
			}
			gameOver();
			do {
				String dir = sc.next();
				switch(dir) {
					case "A": leftMove(); leftSum(); leftMove();
						break;
					case "D": rightMove(); rightSum(); rightMove();
						break;
					case "W": upMove(); upSum(); upMove();
						break;
					case "S": downMove(); downSum(); downMove();
						break;
					default:
						break;
				}
			}while(equalMap());
		}
	}
	public static boolean equalMap() {
		int cnt=0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) 
				if(map2[i][j]!=map[i][j]) cnt=1;
		}
		if(cnt==1) return false;
		else {
			System.out.println("움직일 곳 없음\n다시입력");
			return true;
		}
	}
	public static void screenMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}System.out.println();
		}System.out.println("score : "+score);
	}
	public static void gameOver() {
		int cnt=0;
		int dx[] = {-1,0,0,1};
		int dy[] = {0,1,-1,0};
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if(map[i][j]==0) cnt++;
				for(int k=0;k<4;k++) {
					if(i+dx[k]>=0 && j+dy[k]>=0 && i+dx[k]<4 && j+dy[k]<4 
						&& map[i][j]==map[i+dx[k]][j+dy[k]]) 
						cnt++;
				}
			}
		}
		if(cnt==0) System.out.println("GameOver");
	}
	public static void downSum() {
		for (int i = 3; i >0; i--) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==map[i-1][j]) {
					map[i-1][j]*=2;
					map[i][j]=0;
					score+=map[i-1][j];
				}
			}
		}
	}
	public static void upSum() {
		for (int i = 0; i < map.length-1; i++) {
			for(int j=0;j<map.length;j++) {
				if(map[i][j]==map[i+1][j]) {
					map[i][j]*=2;
					map[i+1][j]=0;
					score+=map[i][j];
				}
			}
		}
	}
	public static void rightSum() {
		for (int i = 0; i < map.length; i++) {
			for(int j=3;j>0;j--) {
				if(map[i][j]==map[i][j-1]) {
					map[i][j]*=2;
					map[i][j-1]=0;
					score+=map[i][j];
				}
			}
		}
	}
	public static void leftSum() {
		for (int i = 0; i < map.length; i++) {
			for(int j=0;j<map.length-1;j++) {
				if(map[i][j]==map[i][j+1]) {
					map[i][j]*=2;
					map[i][j+1]=0;
					score+=map[i][j];
				}
			}
		}
	}
	public static void leftMove() {
		for (int i = 0; i < map.length; i++) {
			for(int k=0;k<3;k++) {
				if(map[i][0]==0) {
					for (int j = 1; j < map.length; j++) {
						if(map[i][j]!=0 && map[i][j-1]==0) {
							int temp=map[i][j-1];
							map[i][j-1]=map[i][j];
							map[i][j]=temp;
						}
					}
				}else {
					for (int j = 2; j < map.length; j++) {
						if(map[i][j]!=0 && map[i][j-1]==0) {
							int temp=map[i][j-1];
							map[i][j-1]=map[i][j];
							map[i][j]=temp;
						}
					}
				}
			}
		}
	}
	public static void rightMove() {
		for (int i = 0; i < map.length; i++) {
			for(int k=0;k<3;k++) {
				if(map[i][3]==0) {
					for (int j = 2; j >= 0; j--) {
						if(map[i][j]!=0 && map[i][j+1]==0) {
							int temp=map[i][j+1];
							map[i][j+1]=map[i][j];
							map[i][j]=temp;
						}
					}
				}else {
					for (int j = 1; j >= 0; j--) {
						if(map[i][j]!=0 && map[i][j+1]==0) {
							int temp=map[i][j+1];
							map[i][j+1]=map[i][j];
							map[i][j]=temp;
						}
					}
				}
			}
		}
	}
	public static void upMove() {
		for (int i = 0; i < map.length; i++) {
			for(int k=0;k<3;k++) {
				if(map[0][i]==0) {
					for (int j = 1; j < map.length; j++) {
						if(map[j][i]!=0 && map[j-1][i]==0) {
							int temp=map[j-1][i];
							map[j-1][i]=map[j][i];
							map[j][i]=temp;
						}
					}
				}else {
					for (int j = 2; j < map.length; j++) {
						if(map[j][i]!=0 && map[j-1][i]==0) {
							int temp=map[j-1][i];
							map[j-1][i]=map[j][i];
							map[j][i]=temp;
						}
					}
				}
			}
		}
	}
	public static void downMove() {
		for (int i = 0; i < map.length; i++) {
			for(int k=0;k<3;k++) {
				if(map[3][i]==0) {
					for (int j = 2; j >= 0; j--) {
						if(map[j][i]!=0 && map[j+1][i]==0) {
							int temp=map[j+1][i];
							map[j+1][i]=map[j][i];
							map[j][i]=temp;
						}
					}
				}else {
					for (int j = 1; j >= 0; j--) {
						if(map[j][i]!=0 && map[j+1][i]==0) {
							int temp=map[j+1][i];
							map[j+1][i]=map[j][i];
							map[j][i]=temp;
						}
					}
				}
			}
		}
	}
}
