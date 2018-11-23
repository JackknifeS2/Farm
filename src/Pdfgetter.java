import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Pdfgetter {

    public static void main(String[] args) {
        Pdfmake pm= new Pdfmake();
        pm.getPdfString();

        try {

            URL url = new URL("http://www.sapporomirai.jp/pdf/yasai_shikyo.pdf");

            HttpURLConnection conn =
                    (HttpURLConnection) url.openConnection();
            conn.setAllowUserInteraction(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
            conn.connect();

            int httpStatusCode = conn.getResponseCode();

            if(httpStatusCode != HttpURLConnection.HTTP_OK){
                throw new Exception();
            }

            // Input Stream
            DataInputStream dataInStream
                    = new DataInputStream(
                    conn.getInputStream());

            // Output Stream
            DataOutputStream dataOutStream
                    = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream("C:\\Users\\SasakiYuki\\Desktop\\farm\\hokkaidou.pdf")));

            // Read Data
            byte[] b = new byte[4096];
            int readByte = 0;

            while(-1 != (readByte = dataInStream.read(b))){
                dataOutStream.write(b, 0, readByte);
            }

            // Close Stream
            dataInStream.close();
            dataOutStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
