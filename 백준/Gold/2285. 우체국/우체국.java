import java.io.*;
import java.util.*;

public class Main {
    static class info implements Comparable<info>{
        long x;
        long a;
        long right;
        public info(long x, long a){
            this.x= x; this.a = a;
        }

        public void setRight(long right) {
            this.right = right;
        }

        @Override
        public int compareTo(info o) {
            return Long.compare(this.x, o.x);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        info[] I = new info[N];


        long population = 0;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            population += b;
            I[i] = new info(a, b);
        }

        Arrays.sort(I);
        I[N-1].right = I[N-1].a;

        for(int i=N-2; i>=0; i--)
            I[i].right += I[i+1].right + I[i].a;

        int result = -1;
        for(int i=1; i<N; i++){
            long p = population - I[i].right;

            long shortDist = I[i].x-I[i-1].x;
            if(p * shortDist >= I[i].right * shortDist){
                result = i-1; break;
            }

        }

        if(result == -1) result = N-1;

        System.out.println(I[result].x);

    }
}