package userInterface.doctor;

import userInterface.doctor.*;
import java.net.*;
import java.io.*;

public class sendSMS {

    public static void sendMessage(String receiver, String message) {
        try {
            // Construct data
            String data = "";
            /*
             * Note the suggested encoding for certain parameters, notably
             * the username, password and especially the message.  ISO-8859-1
             * is essentially the character set that we use for message bodies,
             * with a few exceptions for e.g. Greek characters.  For a full list,
             * see:  http://bulksms.vsms.net/docs/eapi/submission/character_encoding/
             */
            data += "username=" + URLEncoder.encode("ilan17", "ISO-8859-1");
            data += "&password=" + URLEncoder.encode("ilan@1989", "ISO-8859-1");
            data += "&message=" + URLEncoder.encode(message, "ISO-8859-1");
            data += "&want_report=1";
            data += "&msisdn=" + receiver;

            // Send data
            URL url = new URL("http://usa.bulksms.com:5567/eapi/submission/send_sms/2/2.0?");
            /*
             * If your firewall blocks access to port 5567, you can fall back to port 80:
             * URL url = new URL("http://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");
             * (See FAQ for more details.)
             */

            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                // Print the response output...
                System.out.println(line);
            }
            wr.close();
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
