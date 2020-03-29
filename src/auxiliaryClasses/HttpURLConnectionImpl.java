package auxiliaryClasses;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionImpl {
	
	Tools t = new Tools();

//	private static final String USER_AGENT = "Mozilla/5.0";
//
//	private static final String GET_URL = "https://www.isindb.com/";
//
//	private static final String POST_URL = "https://www.isindb.com/action/s.php";
//
//	private static final String POST_PARAMS = "search=CH0012221716";
//
//	public static void main(String[] args) throws IOException {
//
//		sendGET();
//		t.print("GET DONE");
//		sendPOST();
//		t.print("POST DONE");
//	}
//
//	private static void sendGET() throws IOException {
//		URL obj = new URL(GET_URL);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		con.setRequestProperty("User-Agent", USER_AGENT);
//		int responseCode = con.getResponseCode();
//		t.print("GET Response Code :: " + responseCode);
//		if (responseCode == HttpURLConnection.HTTP_OK) { // success
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			t.print(response.toString());
//		} else {
//			t.print("GET request not worked");
//		}
//
//	}
//
//	private static void sendPOST() throws IOException {
//		URL obj = new URL(POST_URL);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("POST");
//		con.setRequestProperty("User-Agent", USER_AGENT);
//
//		// For POST only - START
//		con.setDoOutput(true);
//		OutputStream os = con.getOutputStream();
//		os.write(POST_PARAMS.getBytes());
//		os.flush();
//		os.close();
//		// For POST only - END
//
//		int responseCode = con.getResponseCode();
//		t.print("POST Response Code :: " + responseCode);
//
//		if (responseCode == HttpURLConnection.HTTP_OK) { //success
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			t.print(response.toString());
//		} else {
//			t.print("POST request not worked");
//		}
//	}
	
	private final String USER_AGENT = "Mozilla/5.0";
	private final String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImxhcnMud2lzc2xlckA3bWFya2V0cy5kZSIsInVzZXJfaWQiOiI1Y2RhZGY1MWZhYjk3NTRkYzc5MDVjNTUiLCJpc0FkbWluIjp0cnVlLCJpc1Rlc3RlciI6dHJ1ZSwiaXNCYXNpYyI6ZmFsc2UsImlzRnJlZSI6ZmFsc2UsImlzSW50ZXJtZWRpYXRlIjpmYWxzZSwiaXNQcmVtaXVtIjpmYWxzZSwiaWF0IjoxNTc1NDc1NjI4fQ.74feU2ydny7b5a2Jc4FjIPREqHr1BHBfw8V8rUr3qIM";
//	private final String token = Paths.API_TOKEN;	
	
	/**
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public String sendGet(String url) throws Exception {
//		t.print("Sending GET request to URL : " + url);
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	
		con.setRequestMethod("GET");

		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty ("x-auth-token", token);

		
		int responseCode = con.getResponseCode();
//		System.out.print("Response : " + responseCode +" "+con.getResponseMessage());

		BufferedReader in;
		if (con.getResponseCode() <= 399) {
		    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
		    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		 
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		if(con.getResponseCode()>=400){
			t.print(response.toString());
		}

		return response.toString();

	}
	
/**
 * 
 * @param url
 * @return
 * @throws Exception
 */
	public String sendPost(String url, String content) throws Exception {

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty ("x-auth-token", token);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//		t.print("Sending POST request to URL : " + url);
//		t.print("Post parameters : " + content);
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(content);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
//		System.out.print("Response Code : " + responseCode);
//		t.print(" : " + con.getResponseMessage());
//		t.print("---------------");
//		System.out.println();

		BufferedReader in;
		if (con.getResponseCode() <= 399) {
		    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
		    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		 
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		if(con.getResponseCode()>=400){
			t.print(response.toString());
		}
		

		return response.toString();
	}
	
	/**
	 * 
	 * @param url
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String sendPut(String url, String content) throws Exception {
		t.print("Sending PUT to "+url);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty ("x-auth-token", token);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(content);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.print("Response Code : " + responseCode +" " +con.getResponseMessage());
//		t.print(" : " + );
//		t.print(" : " + con.getContent().toString());
		
		BufferedReader in;
		if (con.getResponseCode() <= 399) {
		    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
		    in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		 
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		if(con.getResponseCode()>=400){
			t.print(response.toString());
		}
		

		return response.toString();
	}
}