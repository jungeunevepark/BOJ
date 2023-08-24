import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int now = 0; int max = 0;
		for(int t=0; t< 10; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			now += -1 * Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
			if(now > max) max = now;
		}
		System.out.println(max);
	}
}