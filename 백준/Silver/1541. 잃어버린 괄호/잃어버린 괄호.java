import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), "-");
	   int result = 0;
	   StringTokenizer k = new StringTokenizer(st.nextToken(), "+");
	   while(k.hasMoreTokens()) {
		   result += Integer.parseInt(k.nextToken());
	   }
	   while(st.hasMoreTokens()) {
		   int next = 0;
		   k = new StringTokenizer(st.nextToken(), "+");
		   next = Integer.parseInt(k.nextToken());
		   while(k.hasMoreTokens()) {
			   int t = Integer.parseInt(k.nextToken());
			   next += t;
		   }
		   result -= next;
	   }
	   System.out.println(result);
	}
}