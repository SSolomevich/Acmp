/**
 * Created by 15 on 28.01.2018.
 */


import java.io.*;
import java.util.*;
public class Task_278 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        String s1 = in.next();
        String s2 = in.next();


        boolean isContain2 = isHas(s1, s2);

        if (isContain2){
            out.print("YES");
        }
        else out.print("NO");


        out.flush();
    }

    public static boolean isHas (String incom1, String incom2) {



        char arrayS[] = incom1.toCharArray();
        char arrayT[] = incom2.toCharArray();
        if (arrayS.length>arrayT.length)  {
            return false;
        }
        int number=0;
        for (int i=0;i<arrayS.length;i++) {

            for (int j = number; j < arrayT.length; j++) {
                if (arrayS[i]==arrayT[j]){
                    number = j+1;
                    break;
                }
                else {
                    number=-1;
                }
            }
            if (number==-1) return false;
            else if (number==arrayT.length&&i<arrayS.length-1) return false;
        }
        return true;
    }
}
