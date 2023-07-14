import java.util.*;
import java.io.*;

public class Main {
	static boolean[] v = new boolean[26];
	static int N, K, result;
	static List<String> words;
    public static void main(String args[]) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        words = new ArrayList<>();
        for(int i=0; i<N; i++)
        	words.add(bf.readLine());
        comb(0, 0);
        System.out.println(result);
    }
    static void comb(int cnt, int start) {
    	if(cnt == K) {
    		int c = 0;
    		f: for(int i=0; i<N; i++) {
    			String check = words.get(i);
    			for(int k=0; k<check.length(); k++) {
    				if(!v[check.charAt(k)-'a']) continue f;
    			}
    			c++;
    		}
    		result = Math.max(result, c);
    		return;
    	}
    	for(int i=start; i<26; i++) {
    		v[i] = true;
    		comb(cnt+1, i+1);
    		v[i] = false;
    	}
    }
}