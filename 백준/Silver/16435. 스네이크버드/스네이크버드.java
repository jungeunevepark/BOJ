import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] height = new int[N];
        for(int i=0; i<N; i++) {
        	height[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(height);
        for(int i=0; i<N; i++) {
        	if(L >= height[i]) L++;
        }
        System.out.println(L);
    }
}