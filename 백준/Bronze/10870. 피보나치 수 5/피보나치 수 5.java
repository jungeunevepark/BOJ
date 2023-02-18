import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		int first = 0; int second = 1;
		for(int t=2; t<=N; t++) {
			int temp = second;
			second = first+second;
			first = temp;
		}
		if(N == 0) System.out.println(0);
		else System.out.println(second);
	}
}