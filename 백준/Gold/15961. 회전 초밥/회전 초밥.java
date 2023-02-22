import java.util.*;
import java.io.*;

public class Main {
	static int N, d, k, c;
	static int[] chobab;
	static int max;
    public static void main(String[] args) throws Exception {
       BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
       StringBuilder sb = new StringBuilder();
       StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
       N = Integer.parseInt(st.nextToken());
       d = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       c = Integer.parseInt(st.nextToken());
       
       chobab = new int[N];
       for(int i=0; i<N ;i++)
    	   chobab[i] = Integer.parseInt(bf.readLine());
       
       Map<Integer, Integer> bab = new HashMap<>();
       bab.put(c, 1);
       for(int j=0; j<k; j++)
    	   if(bab.get(chobab[j % N]) == null) {
			   bab.put(chobab[j % N], 1);
		   }
		   else
			   bab.replace(chobab[j % N], bab.get(chobab[j % N])+1);
       int max = bab.size();
       for(int i=1; i<N; i++) {
    	   //앞 제거
    	   if(bab.get(chobab[i-1]) == 1) bab.remove(chobab[i-1]);
    	   else  bab.replace(chobab[i-1], bab.get(chobab[i-1])-1);
    	   
    	   //뒤 추가
		   if(bab.get(chobab[(i+k-1) % N]) == null) {
			   bab.put(chobab[(i+k-1) % N], 1);
		   }
		   else
			   bab.replace(chobab[(i+k-1) % N], bab.get(chobab[(i+k-1) % N])+1);
		   
		   //다른 초밥의 개수
    	   if(max < bab.size()) max = bab.size();
       }
       System.out.println(max);
    }
}