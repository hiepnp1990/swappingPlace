import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;

public class TestCASRest {
		
	private static final String USER_AGENT = "Mozilla/5.0";
	static String url = "https://192.168.14.21:8443/cas/v1/tickets"; // trusted self-signed server; using InstallCert.java trick that I found online
	public static void main(String... args) throws Exception
	{
		sendPost();  
		//sendPost2();   //when enable this, we get HTTP 200 Success code; but it's from login via redirect as I checked on server log
	}
	
	@SuppressWarnings("deprecation")
	static void sendPost() throws Exception {
		 
        

        HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_0);// to specify HTTP POST 1.0 as the REST Protocol documentation highlighted 
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("User-Agent", USER_AGENT);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("username", "casuser"));
        urlParameters.add(new BasicNameValuePair("password", "Mellon"));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = client.execute(post);
        System.out.println("\n Sending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + post.getEntity());
        System.out.println("Response Code : " +
                                   response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
                       new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
                result.append(line);
        }

        System.out.println(result.toString());
	}
	
	// HTTP POST request 
	static void sendPost2() throws Exception {
	 
	         URL obj = new URL(url);
	         HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
	 
	         //add reuqest header
	         con.setRequestMethod("POST");
	         con.setRequestProperty("User-Agent", USER_AGENT);
	         con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 
	         String urlParameters = "username=casuser&password=Mellon";
	 
	         // Send post request
	         con.setDoOutput(true);
	         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	         wr.writeBytes(urlParameters);
	         wr.flush();
	         wr.close();
	 
	         int responseCode = con.getResponseCode();
	         System.out.println("\n Sending 'POST' request to URL : " + url);
	         System.out.println("Post parameters : " + urlParameters);
	         System.out.println("Response Code : " + responseCode);
	 
	         BufferedReader in = new BufferedReader(
	                        new InputStreamReader(con.getInputStream()));
	         String inputLine;
	         StringBuffer response = new StringBuffer();
	 
	         while ((inputLine = in.readLine()) != null) {
	                 response.append(inputLine);
	         }
	         in.close();
	 
	         //print result
	         System.out.println(response.toString());
	 
	}
}
