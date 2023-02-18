import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
		int now = 1; int count = 0; int result = 0;
		for(int i=1; i<=1000; i++) {
			if(count == now) {
				count = 0; now++;
			}
			if(i > num2) break;
			if(i>= num1) result+= now;
			count++;
		}
		System.out.println(result);
	}
}