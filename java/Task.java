import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**

 * @author arzamasov.maksim1998@yandex.ru

 * @Date  01.09.2022

 */



public class Task {

    public static void main(String[] args) {

        int number = (int) (Math.random() * 1000);
        String query = " http://numbersapi.com/" + number + "/trivia";
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");

                }
                System.out.println(sb);
                parseString(sb.toString());
            } else {
                System.out.println("fail " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void parseString(String s){
        TreeMap<String, Integer> values = new TreeMap<>();
        for (int i = 0; i < s.length(); i++){
            String t = (s.substring(i, i+1));
            values.computeIfPresent(t, (k,v) -> v+1);
            values.putIfAbsent(t, 1);
        }


        for(Map.Entry<String, Integer> entry : values.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " - " + value + " times ");
        }
    }
}
