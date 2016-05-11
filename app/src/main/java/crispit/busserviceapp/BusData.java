package crispit.busserviceapp; /**
 * Created by Mikael on 2016-04-25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BusData {

    public static String getBusInfo(String dgwNr, String infoTxt) throws IOException {
        String dgw = "Ericsson$" + dgwNr;

        long t2 = System.currentTimeMillis();
        long t1 = t2 - (1000 * 120);

        StringBuffer response = new StringBuffer();
        String key = "Basic Z3JwMTA0OjRERnJlOXRrZ3U=";
        String url = "https://ece01.ericsson.net:4443/ecity?dgw=" + dgw + "&sensorSpec=Ericsson$" + infoTxt + "&t1=" + t1 + "&t2=" + t2;

        URL requestURL = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) requestURL
                .openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", key);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                con.getInputStream()));

        String inputLine;


        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();

    }
}
