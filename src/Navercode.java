import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class Navercode {
    public static void main(String[] args) {

        String clientId = "eKKhVXrp0AVCMj3Nax19";
        String clientSecret = "2nri1pDIQN";
        try {
            String text = URLEncoder.encode("황순성", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;

            URL url = new URL(apiURL);
//            URL url = new URL("https://api.github.com/users/soongon");

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        }catch (Exception e) {
            System.out.println(e);
        }
    }
}
