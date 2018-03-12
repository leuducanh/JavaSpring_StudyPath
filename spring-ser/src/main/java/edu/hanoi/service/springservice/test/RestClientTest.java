package edu.hanoi.service.springservice.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;

public class RestClientTest {

	public static void main(String[] args) {
		URL url = null;
		HttpURLConnection con = null;
		InputStream stream = null;
		try{
			url = new URL("http://localhost:8080/list/users");
			con= (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Accept", "Application/Json");
		
			String account = "abc:abc";
			String basicAuth = "Basic " + DatatypeConverter.printBase64Binary(account.getBytes());
			con.setRequestProperty("Authorization", basicAuth);
			
			 stream = con.getInputStream();
				byte[] bytes = new byte[4*1024];
				int read = -1;
			while((read = stream.read(bytes))!=-1){
				System.out.println(new String(bytes,0,read));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
