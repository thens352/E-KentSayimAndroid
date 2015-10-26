package info.androidhive.slidingmenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import entity.RestfulErisim;

public class SplashActivity extends Activity {
	String userName, passWord;
	EditText username, password;
	Button login;

	Sqlite db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		db = new Sqlite(getApplicationContext());
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		login = (Button) findViewById(R.id.login);

		login.setOnClickListener(loginListener);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	private OnClickListener loginListener = new OnClickListener() {
		public void onClick(View v) {

			SharedPreferences preferences;
			preferences = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			final SharedPreferences.Editor editor = preferences.edit();

			class LoginRest extends RestfulErisim {

				@Override
				protected String doInBackground(String... params) {
					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse response;
					String responseString = null;
					try {
						response = httpclient.execute(new HttpGet(params[0]));
						StatusLine statusLine = response.getStatusLine();
						if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							response.getEntity().writeTo(out);
							responseString = out.toString();
							out.close();
						} else {
							// Closes the connection.
							responseString = "error";
							throw new IOException(statusLine.getReasonPhrase());
						}
					} catch (ClientProtocolException e) {
						// TODO Handle problems..
					} catch (IOException e) {
						// TODO Handle problems..
					}
					return responseString;
				}

				@Override
				protected void onPostExecute(String results) {
					if (results != null) {
						try {
							JSONObject result = new JSONObject(results);
							if (result.getBoolean("result")) {
								editor.putInt("kullaniciID",
										result.getInt("id"));
								editor.commit();
								db.reset();
								db.dbGuncelle();
								db.close();
								Intent myIntent = new Intent(
										SplashActivity.this, MainActivity.class);
								startActivity(myIntent);
							} else {
								Toast.makeText(getApplicationContext(),
										result.getString("error") + " yanlýþ",
										Toast.LENGTH_SHORT).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
			;
			new LoginRest().execute(RestfulErisim.url + "kullanici/login/"
					+ username.getText().toString() + "/"
					+ password.getText().toString());
		}
	};
}