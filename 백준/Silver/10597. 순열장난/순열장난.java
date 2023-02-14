import java.util.*;
import java.io.*;

public class Main {
	static int flag;
	static String check;
	static boolean[] v = new boolean[51];
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		check = bf.readLine();
		dfs(0, 0, "");
	}
	static void dfs(int idx, int cnt, String s) {
		if(idx<check.length() && check.charAt(idx) == '0') return;
		if(idx >= check.length()) { 
			if(flag == 0){
				for(int i=1; i<=cnt; i++)
					if(!v[i]) return;
				System.out.println(s);
			flag = 1;
			}
			return;
		}
		for(int i = idx+1; i<=idx+2; i++) {
			if(i > check.length()) break;
			int temp = Integer.parseInt(check.substring(idx, i));
			if(temp > 50 || v[temp]) continue;
			v[temp] = true;
			if(idx == 0) dfs(i, cnt+1, check.substring(idx, i));
			else dfs(i, cnt+1, s+" "+check.substring(idx, i));
			v[temp] = false;
		}
	}
}