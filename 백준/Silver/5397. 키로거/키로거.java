import java.util.*;
import java.io.*;

public class Main {
	static int N, M, T;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringBuilder sb = new StringBuilder();
	   int N = Integer.parseInt(bf.readLine());
	   for(int i=0; i<N; i++) {
		   ArrayDeque<Character> left = new ArrayDeque<>();
		   ArrayDeque<Character> right = new ArrayDeque<>();
		   String s = bf.readLine();
		   for(int j=0; j<s.length(); j++) {
			   char c = s.charAt(j);
			   if(c == '<' && !left.isEmpty()) {
				  right.push(left.pop());
			   }
			   else if(c == '>' && !right.isEmpty()) {
				   left.push(right.pop());
			   }
			   else if(c == '-' && !left.isEmpty()) {
				   left.pop();
			   }
			   else if(c != '-' && c != '<' && c != '>') {
				   left.push(c);
			   }
		   }
		   while(!left.isEmpty()) {
			   sb.append(left.pollLast());
		   }
		   while(!right.isEmpty()) {
			   sb.append(right.pop());
		   }
		   sb.append("\n");
	   }
	   System.out.println(sb);
	}
}