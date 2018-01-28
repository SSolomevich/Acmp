/**
 * Created by 15 on 28.01.2018.
 */


import java.io.*;
import java.util.*;
public class Task_670 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int n1 = in.nextInt();



        int result=0;
        boolean a = true;
        for (int i = 0; i < n1; i++) {
            a=true;
            while(a==true){
                if(!isHasRepeatNumbers(result+1)) {
                    result+=1;
                    a=false;

                }
                else result++;
            }

        }
        out.print(result);
        out.flush();
    }
    public static boolean isHasRepeatNumbers(int incom) {
        StringBuilder sb = new StringBuilder(Integer.toString(incom));

        int[] array = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            array[i] = Character.getNumericValue(sb.charAt(i));
        }

        Arrays.sort(array);

        for (int i = 0; i < (array.length - 1); i++) {
            if (array[i] - array[i + 1] == 0) {
                return true;
            }
        }
        return false;
    }
}
