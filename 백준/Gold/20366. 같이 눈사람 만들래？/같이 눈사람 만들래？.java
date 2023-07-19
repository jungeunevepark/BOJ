import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] isCheck;
    static int[] snow;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        isCheck = new boolean[N];
        snow = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
        for(int i=0; i<N; i++){
            snow[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(snow);
        comb(0, 0, 0);

        System.out.println(result);

    }
    static void comb(int start, int count, int snowPeople){
        if(result == 0) return;
        if(count == 2){
            account(snowPeople);
            return;
        }
        for(int i=start; i<N; i++){
            isCheck[i] = true;
            comb(i+1, count+1, snowPeople+snow[i]);
            isCheck[i] = false;
        }
    }
    static void account(int already){
        int start = 0; int end = N-1;
        while(start<end){
            if(isCheck[start]) start++;
            else if(isCheck[end]) end--;
            else{
                result = Math.min(result, Math.abs(already-snow[start]-snow[end]));

                if(snow[start]+snow[end] < already){
                    start++;
                }
                else if(snow[start]+snow[end] == already){
                    result = 0; break;
                }else end--;

            }
        }

    }
}