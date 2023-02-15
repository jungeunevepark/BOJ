import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] touch = new boolean[100][100];
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			put(x, y);
		}
		int sum=0;
		for(int i=0; i<100; i++)
			for(int j=0; j<100; j++)
				if(touch[i][j]) sum++;
		System.out.println(sum);
		
	}
	static void put(int x, int y) {
		for(int i=x; i<x+10; i++)
			for(int j=y; j<y+10; j++)
				touch[i][j] = true;
	}
}