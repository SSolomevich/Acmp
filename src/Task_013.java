import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Task_013 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();

        String[] s = str.split(" ");

        int n1 = Integer.parseInt(s[0]);
        int n2 = Integer.parseInt(s[1]);

        ArrayList<Integer> listN1=new ArrayList<>();
        ArrayList<Integer> listN2=new ArrayList<>();

        int b=0;
        int k=0;

        for (int i=0;i<4;i++){
            listN1.add(n1%10);
            listN2.add(n2%10);
            if (n1%10==n2%10){
                b++;
            }
            n1/=10;
            n2/=10;

        }

        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
               if (listN1.get(i)==listN2.get(j)&&j!=i){
                   k++;
               }
            }
        }

        System.out.println(b+" "+k);


    }
}
