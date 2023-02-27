import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Map<String, Integer> memo = new HashMap<String, Integer>();
		for(int i=0; i<N; i++) {
			memo.put(bf.readLine(), 1);
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(bf.readLine(), ",");
			while(st.hasMoreTokens()) {
				String check = st.nextToken();
				try {
					memo.remove(check);
				}catch(Exception e) {
					
				}
			}
			System.out.println(memo.size());
		}
	}
}