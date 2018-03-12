package edu.hanoi.service.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Connection;

public class RestClientTest {

	public static void main(String[] args) {
		URL url = null;
		HttpURLConnection con = null;
		InputStream stream = null;
		try{
			con= (HttpURLConnection) url.openConnection();
			url = new URL("http://localhost:8080/list/users");
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
