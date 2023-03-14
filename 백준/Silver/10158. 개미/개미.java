import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
	   BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	   StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
	   int w = Integer.parseInt(st.nextToken());
	   int h = Integer.parseInt(st.nextToken());
	   st = new StringTokenizer(bf.readLine(), " ");
	   int x = Integer.parseInt(st.nextToken());
	   int y = Integer.parseInt(st.nextToken());
	   long t = Long.parseLong(bf.readLine());
	   long tmpx = x+t; long tmpy = y+t;
	   tmpx %= 2*w; tmpy %= 2*h;
	   if(tmpx >= w) tmpx = 2*w - tmpx;
	   if(tmpy >= h) tmpy = 2*h - tmpy;
	   System.out.println(tmpx+" "+tmpy);
	}
}