import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int t=1; t<= T; t++) {
			int n = Integer.parseInt(bf.readLine());
			int count = 0;
			while(n > 0) {
				if(n % 2 == 1) {sb.append(count).append(" ");}
				count++;
				n /= 2;
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}