import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int num1 = Integer.parseInt(bf.readLine());
		int num2 = Integer.parseInt(bf.readLine());
		int sum = 0; int min = 0;
		for(int i=num1; i<=num2; i++) {
			if(isPrime(i)) {
				if(min == 0) min = i;
				sum+=i;
			}
		}
		if(sum == 0) sum = -1;
		System.out.println(sum);
		if(min != 0) System.out.println(min);
	}
	static boolean isPrime(int x) {
		if(x == 1) return false;
		if(x == 2) return true;
		for(int i=2; i<=Math.sqrt(x)+1; i++) {
			if(x % i == 0) return false;
		}
		return true;
	}
}