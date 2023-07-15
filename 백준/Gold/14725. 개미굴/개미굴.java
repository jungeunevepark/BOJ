import java.util.*;
import java.io.*;

public class Main {
	static class Node{
		Map<String, Node> childNode = new HashMap<>();
	}
	static class MakeAntHole{
		Node rootNode = new Node();
		Node insert(Node root, String in) {
			return root.childNode.computeIfAbsent(in, key -> new Node());
		}
		void print(Node node, int depth) {
			List<String> antList = new ArrayList<>(node.childNode.keySet());
			antList.sort((o1, o2) -> o1.compareTo(o2));
			for(String hole : antList) {
				printDepth(depth);
				System.out.println(hole);
				if(node.childNode.getOrDefault(hole, null) != null) {
					print(node.childNode.get(hole), depth+1);
				}
			}
		}
		void printDepth(int cnt) {
			for(int i=0; i<cnt; i++) {
				System.out.print("--");
			}
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		MakeAntHole hole = new MakeAntHole();
		Node root = hole.rootNode;
		for(int i=0; i<T; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			Node node = root;
			for(int j=0; j<N; j++) {
				node = hole.insert(node, st.nextToken());
			}
		}
		hole.print(root, 0);
	}
	
}
