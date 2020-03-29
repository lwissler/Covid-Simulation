package auxiliaryClasses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;



/**
 * 
 * @author Lars Wissler
 *
 */
public class Tools {
	
	

	/**
	 * 
	 * @param arg
	 */
	public void print(Object arg) {
		System.out.println(new Date() + ": " + arg.toString());
	}

	public int addDays(int date, int numberDaysToAdd, int plusMinusDays) throws ParseException {
//		long start = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date d = format.parse(new Integer(date).toString());
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		
		long rng = Math.round((Math.random()-0.5)*2*plusMinusDays);
		for(int i = 0; i < numberDaysToAdd+rng;i++){
			c.add(Calendar.DAY_OF_YEAR,1);
		}
		
//		double elapsedTime = Math.round((System.currentTimeMillis() - start)/100.0);
//		System.out.println("Elapsed Time addDays: " + elapsedTime/10 + " Seconds");
		
		return Integer.parseInt(format.format(c.getTime()));
	}
	
	public void postToMongo(JSONObject data, String call) throws Exception{
		HttpURLConnectionImpl h = new HttpURLConnectionImpl();
		String url = Paths.API_ADDRESS+call;
		
//		JSONObject json = new JSONObject();
//		json.put("data", data);
//		System.out.println("Sending: "+ data.toString());
		
		h.sendPost(url, data.toJSONString());
	}
	
	public String formatDate(Date d){
		SimpleDateFormat ourFormat = new SimpleDateFormat ("yyyy-MM-dd");
		return ourFormat.format(d);
	}
	
	
	
	

}
