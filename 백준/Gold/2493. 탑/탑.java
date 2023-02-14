import java.io.*;
import java.util.*;
class Top {
	public int idx;
	public int height;
	public Top(int idx, int height) {
		this.idx = idx;
		this.height = height;
	}
}
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		int N = Integer.parseInt(bf.readLine());
		ArrayDeque<Top> top = new ArrayDeque<>();
		int[] result = new int[N+1];
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		f: for(int i=1; i<=N; i++) {
			int t= Integer.parseInt(st.nextToken());
			if(top.isEmpty()) {sb.append(0).append(" "); top.offer(new Top(i, t));}
			else {
				Top u = top.pollLast();
				if(u.height <= t) {
					while(!top.isEmpty()) {
						u = top.pollLast();
						if(u.height > t) {
							sb.append(u.idx).append(" ");
							top.offer(u);
							top.offer(new Top(i, t));
							continue f;
						}
					}
					sb.append(0).append(" ");
					top.offer(new Top(i, t));
				}
				else {
					top.offer(u);
					top.offer(new Top(i, t));
					sb.append(u.idx).append(" ");
				}
			}
		}
		System.out.println(sb);
	}
}