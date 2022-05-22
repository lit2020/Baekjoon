package 백준1013;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("(100+1+|01)+");
        int amount = Integer.parseInt(br.readLine());
        for(int i = 0; i < amount; i++) {
            String value = br.readLine();
            if (pattern.matcher(value).matches()) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

}