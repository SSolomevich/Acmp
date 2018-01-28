/**
 * Created by 15 on 28.01.2018.
 */


import java.io.*;
import java.util.*;
public class Task_579 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n1 = in.nextInt();

        ArrayList<Integer> plus = new ArrayList<>();
        ArrayList<Integer> minus = new ArrayList<>();
        ArrayList<Integer> z = new ArrayList<>();
        int plus1=0;
        int minus1=0;

        for (int i = 0; i < n1; i++) {

            int n = in.nextInt();
            z.add(n);
            if (n>0){
                plus.add(n);
                plus1+=n;
            }
            else if(n<0){
                minus.add(n);
                minus1+=n;
            }
        }

        if (plus1+minus1>=0){
            out.println(plus.size());
            for (int i=0; i<z.size();i++){
                if (z.get(i)>0) {

                    out.print(i+1);
                    out.print(" ");
                }
            }
        }
        else {
            out.println(minus.size());
            for (int i=0; i<z.size();i++){
                if (z.get(i)<0) {

                    out.print(i+1);
                    out.print(" ");
                }
            }
        }

        out.flush();
    }

}
