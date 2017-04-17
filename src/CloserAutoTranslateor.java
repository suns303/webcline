import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class CloserAutoTranslateor {
    public static void main(String[] args) {

        String clientId = "eKKhVXrp0AVCMj3Nax19";
        String clientSecret = "2nri1pDIQN";

        try {
            BufferedReader br = new BufferedReader(new FileReader("src\\closer.txt"));
            String line = "";

            while ((line = br.readLine()) != null) {
               if (line.trim().equals("")) continue;

                try {
                    String text = URLEncoder.encode(line, "UTF-8");
                    String apiURL = "https://openapi.naver.com/v1/language/translate";
                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
                    // post request
                    String postParams = "source=en&target=ko&text=" + text;
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    wr.writeBytes(postParams);
                    wr.flush();
                    wr.close();

                    int responseCode = con.getResponseCode();
                    BufferedReader br2;
                    if (responseCode == 200) { // 정상 호출
                        br2 = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    } else {  // 에러 발생
                        br2 = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                    }
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = br2.readLine()) != null) {
                        response.append(inputLine);
                    }

                    String jsonData = response.toString();

                    JSONObject obj = new JSONObject(jsonData);

                    String result = obj.getJSONObject("message")
                            .getJSONObject("result")
                            .getString("translatedText");



                    System.out.println(result);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
