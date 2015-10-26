package info.androidhive.slidingmenu;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import entity.RestfulErisim;

public class HomeFragment extends Fragment {

	Spinner bolgeSpinner;
	JSONArray bolgelerJSON;
	Button secButon;
	List<String> bolgeler;

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.home, container, false);

		final SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(rootView.getContext());
		final SharedPreferences.Editor editor = preferences.edit();

		bolgeSpinner = (Spinner) rootView.findViewById(R.id.bolgeSpinner);
		secButon = (Button) rootView.findViewById(R.id.btnSecBolge);

		

		secButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!bolgeSpinner.getSelectedItem().equals("Bölge Seçiniz")) {
					String secilen = bolgeler.get(bolgeSpinner
							.getSelectedItemPosition());
					for (int i = 0; i < bolgeler.size(); i++) {
						try {
							if (bolgelerJSON.getJSONObject(i).getString("ad")
									.equals(secilen)) {
								editor.putInt("bolgeID", bolgelerJSON
										.getJSONObject(i).getInt("id"));
								editor.putString("bolgeAdi", bolgelerJSON
										.getJSONObject(i).getString("ad"));
								editor.commit();
								Toast.makeText(rootView.getContext(),
										"Bölge seçildi.", Toast.LENGTH_LONG)
										.show();
//								BilgiFragment fragment = new BilgiFragment();
//								FragmentManager fragmentManager = getFragmentManager();
//								FragmentTransaction fragmentTransaction = fragmentManager
//										.beginTransaction();
//								fragmentTransaction.replace(R.id.frame_container, fragment,
//										"Bilgiler");
//								fragmentTransaction.commit();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else
					Toast.makeText(rootView.getContext(),
							"Lütfen bölge seçiniz.", Toast.LENGTH_SHORT).show();

			}
		});

		class BolgeRest extends RestfulErisim {

			@Override
			protected String doInBackground(String... params) {
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response;
				String responseString = null;
				try {
					response = httpclient
							.execute(new HttpGet(params[0]));
					StatusLine statusLine = response.getStatusLine();
					if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						response.getEntity().writeTo(out);
						responseString = out.toString();
						out.close();
					} else {
						// Closes the connection.
						responseString = "error";
						throw new IOException(statusLine
								.getReasonPhrase());
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
				// TODO Auto-generated method stub
				super.onPostExecute(results);
				bolgeler = new ArrayList<String>();
				try {
					bolgelerJSON = new JSONArray(results);
					bolgeler.add("Bölge Seçiniz");
					for (int i = 0; i < bolgelerJSON.length(); i++) {
						bolgeler.add(bolgelerJSON.getJSONObject(i).getString(
								"ad"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
						getActivity(), android.R.layout.simple_spinner_item,
						bolgeler);
				dataAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				bolgeSpinner.setAdapter(dataAdapter);
			}

		}
		;
		new BolgeRest().execute(RestfulErisim.url + "bolge");

		return rootView;
	}
}
