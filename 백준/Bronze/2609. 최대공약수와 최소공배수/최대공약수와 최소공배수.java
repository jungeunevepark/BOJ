import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		int GCD = 0;
		for(int i=1; i<=Math.min(num1, num2); i++) {
			if(num1 % i == 0 && num2 % i == 0) {
				GCD = i;
			}
		}
		System.out.println(GCD);
		System.out.println(GCD * (num1 / GCD) * (num2 / GCD));
	}
}