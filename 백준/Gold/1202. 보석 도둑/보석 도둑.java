import java.io.*;
import java.util.*;

public class Main {
	static class jewel implements Comparable<jewel>{
		int weight;
		int value;

		public jewel(int weight, int value){
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(jewel jewel) {
			int r = Integer.compare(this.weight, jewel.weight);
			if(r == 0)
				r = -Integer.compare(this.value, jewel.value);
			return r;
			// 가벼운 거 >> 더 가치있는 거
		}
	}
	static PriorityQueue<Long> bags;
	static PriorityQueue<jewel> findJewels;
	static int K;
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		jewel[] jewels = new jewel[N];
		findJewels = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.value, o2.value));
		bags = new PriorityQueue<>();
		for(int i=0; i<N; i++){
			st = new StringTokenizer(bf.readLine(), " ");
			jewels[i] = new jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(jewels);

		for(int i=0; i<K; i++)
			bags.offer(Long.parseLong(bf.readLine()));


		int i = 0; long result = 0;

		while(!bags.isEmpty()){
			long b = bags.poll();
			for(; i<N; i++){
				if(b < jewels[i].weight) break;
				findJewels.offer(jewels[i]);
			}
			if(findJewels.isEmpty()) continue;

			jewel j = findJewels.poll();
			result += j.value;

		}
		System.out.println(result);
	}
}