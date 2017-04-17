import java.io.*;
import java.net.*;
import java.io.DataOutputStream;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class APIExamTranslate {

    public static void main(String[]args){

        String source = "That we beat to death in Tucson ";

        String clientId = "eKKhVXrp0AVCMj3Nax19";
        String clientSecret = "2nri1pDIQN";

        try {
            String text = URLEncoder.encode(source, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/language/translate";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            String postParams = "source= en&target=ko&text" + text;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader((new InputStreamReader(con.getInputStream())));
            } else {
                br = new BufferedReader((new InputStreamReader(con.getInputStream())));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
