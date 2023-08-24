import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static StringBuilder sb;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		N = Integer.parseInt(bf.readLine());
		
		hanoi(N, 1, 2, 3);
		//System.out.println(count);
		sb.insert(0, count+"\n");
		System.out.println(sb.toString());
	}
	static void hanoi(int n, int from, int mid, int to) {
		if(n ==0) {
			return;
		}
		hanoi(n-1, from, to, mid);
		sb.append(from).append(" ").append(to).append("\n"); count++;
		hanoi(n-1, mid, from, to);
	}
}