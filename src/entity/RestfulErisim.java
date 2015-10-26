package entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;

public class RestfulErisim extends AsyncTask<String, Void, String> {

	public static String url = "http://ofis.kayiyazilim.com:38080/E-KentSayim/rest/";

	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	@Override
	protected String doInBackground(String... params) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(params[0]);
		String text = "";
		try {
			StringEntity entity = new StringEntity(params[1],HTTP.UTF_8);
			httpPost.setEntity(entity);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-type", "application/json");
			HttpResponse response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() != 200)
				text = "error";
			else {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								(response.getEntity().getContent())));
				text = reader.readLine();
			}
		} catch (Exception e) {
			return e.getLocalizedMessage();
		}
		return text;
	}

	@Override
	protected void onPostExecute(String results) {
	}
}
