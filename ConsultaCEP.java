import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;



public class ConsultaCEP {
    private static final String API_URL = "https://viacep.com.br/ws/";

    public static JSONObject consultarCEP(String cep) {
        String urlString = API_URL + cep + "/json/";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }

            conn.disconnect();

            JSONObject json = new JSONObject(sb.toString());
            if (json.has("erro")) {
                return null; // CEP não encontrado
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
            return null;

       }
  
  }
}
