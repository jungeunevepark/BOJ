import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		for(int i=N/5; i>=0; i--) {
			int t = N - i*5;
			if(t % 3 == 0) {
				System.out.println(i+t/3);
				return;
			}
		}
		System.out.println(-1);
    }
}