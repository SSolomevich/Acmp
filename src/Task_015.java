import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Task_015 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        int n = Integer.parseInt(str);
        int sum=0;
        for (int i=0;i<n;i++){
            String strNext = reader.readLine();
            String[] s = strNext.split(" ");
            for (int j=0;j<n;j++){
                if (Integer.parseInt(s[j])==1) sum++;
            }
        }
        sum=sum/2;
        System.out.println(sum);
    }
}
