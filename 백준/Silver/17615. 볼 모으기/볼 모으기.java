import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] array;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		String s = bf.readLine();
		int redcnt = 0; int bluecnt = 0; int idx = 0;
		if(s.charAt(N-1) == 'R') {
			for(int i=0; i<N; i++) {
				if(s.charAt(i) == 'B') {
					bluecnt++; idx = i;
				}
			}
			redcnt = N-bluecnt;
			System.out.println(Math.min(bluecnt, redcnt-(N-idx-1)));
			
		}
		else{
			for(int i=0; i<N; i++) {
				if(s.charAt(i) == 'R') {
					redcnt++; idx = i;
				}
			}
			bluecnt = N-redcnt;
			System.out.println(Math.min(redcnt, bluecnt-(N-idx-1)));
		}
	}
}