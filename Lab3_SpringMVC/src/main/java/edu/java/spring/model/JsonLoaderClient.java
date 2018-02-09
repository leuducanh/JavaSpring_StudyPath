package edu.java.spring.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class JsonLoaderClient {

	public static void main(String[] args) {
		URL url;
		URLConnection con ;
		InputStream stream;
		try {
			url = new URL("http://localhost:8080/clazz/xml");
			con = url.openConnection();
			con.addRequestProperty("Accept", "Application/json");
			stream = con.getInputStream();
			
			int read;
			byte[] bytes = new byte[4*1024];
			
			while((read = stream.read(bytes)) != -1){
				System.out.println(new String(bytes,0,read));
			}
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}
}
