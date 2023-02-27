import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s = bf.readLine();
		ArrayDeque<Character> left = new ArrayDeque<>();
		ArrayDeque<Character> right = new ArrayDeque<>();
		StringBuilder sb= new StringBuilder();
		for(int i=0; i<s.length(); i++)
			left.push(s.charAt(i));
		
		
		int N = Integer.parseInt(bf.readLine());
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			String command = st.nextToken();
			if(command.equals("P")) left.push(st.nextToken().charAt(0));
			else if(command.equals("L") && !left.isEmpty()) right.push(left.pop());
			else if(command.equals("D") && !right.isEmpty()) left.push(right.pop());
			else if(command.equals("B") && !left.isEmpty()) left.pop();
		}
		while(!left.isEmpty())
			sb.append(left.pollLast());
		while(!right.isEmpty())
			sb.append(right.pop());
		System.out.println(sb);
	}
}