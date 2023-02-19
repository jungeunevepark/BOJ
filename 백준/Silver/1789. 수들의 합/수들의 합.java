import java.io.*;
import java.util.*;

public class Main {
	static long S;
	static int flag;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		S = Long.parseLong(bf.readLine());
		long sum = 0; int i; int count =0;
		for(i=1; ; i++) {
			if(sum > S) break;
			sum += i; count++;
		}
		System.out.println(count-1);
	}
}