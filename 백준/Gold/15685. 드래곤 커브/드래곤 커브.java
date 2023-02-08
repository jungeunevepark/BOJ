import java.util.*;
import java.io.*;

class Node{
	public int x;
	public int y;
	public int d;
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Node(int x, int y, int d) {
		this(x, y);
		this.d = d;
	}
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", d=" + d + "]";
	}
}
public class Main {
	static int dx[] = {0, -1, 0, 1};
	static int dy[] = {1, 0, -1, 0};
	static int dots[][] = new int[101][101];
	static ArrayDeque<Node> dot;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		int sx[] = {0, 1, 0, 1};
		int sy[] = {0, 0, 1, 1};
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			dot = new ArrayDeque<>();
			dragon(Integer.parseInt(st.nextToken()), new Node(y, x, d));
			while(!dot.isEmpty()) {
				Node s = dot.poll();
				dots[s.x][s.y] = 1;
			}
		}
		for(int i= 0; i< 100; i++) {
			f: for(int j=0; j< 100; j++) {
				for(int d= 0; d<4; d++) {
					int nx = i+sx[d]; int ny = j+sy[d];
					if(dots[nx][ny] != 1) continue f;
				}
				result++;
			}
		}
		System.out.println(result);
	}
	
	static void dragon(int gene, Node node){
		if(gene == 0) {
			int nx = node.x + dx[node.d];
			int ny = node.y + dy[node.d];
			dot.add(node);
			dot.add(new Node(nx, ny));
			return;
		}
		dragon(gene-1, node);
		Node n = dot.getLast();
		ArrayDeque<Node> add = new ArrayDeque<>();
		for(Node d : dot) {
			if(d.x == n.x && d.y == n.y) continue;
			int tempx = d.x - n.x;
			int tempy = d.y - n.y;
			add.add(new Node(n.x + tempy, n.y - tempx));
		}
		n = add.getLast();
		while(! add.isEmpty()) {
			Node temp = add.pollLast();
			dot.add(temp);
		}
	}
}