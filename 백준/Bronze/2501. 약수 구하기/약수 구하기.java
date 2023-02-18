import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int original = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int count = 0;
		for(int i=1; i<=original; i++) {
			if(original % i == 0) {
				count++;
				if(count == K) {
					System.out.println(i); return;
				}
			}
		}
		System.out.println(0);
	}
}