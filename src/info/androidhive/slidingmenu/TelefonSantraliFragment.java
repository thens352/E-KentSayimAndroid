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
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import entity.RestfulErisim;
import entity.TelefonSantrali;

public class TelefonSantraliFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button barkodaraButon;
	Button temizleButon;
	Spinner s;
	final List<String> list = new ArrayList<String>();
	View rootView;

	public TelefonSantraliFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.telefonsantrali, container, false);

		araButon = (Button) rootView.findViewById(R.id.buttonAra);
		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TelefonSantraliFragment fragment = new TelefonSantraliFragment();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment,
						"Anasayfa");
				fragmentTransaction.commit();
			}
		});

		list.add("SAHADA");
		list.add("DEPODA");
		list.add("HURDA");

		s = (Spinner) rootView.findViewById(R.id.spinner1);

		barkodaraButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				class FragmantRest extends RestfulErisim {
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

						super.onPostExecute(results);
						JSONObject resultObject = null;
						try {
							resultObject = new JSONObject(results);
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (resultObject == null)
							Toast.makeText(rootView.getContext(),
									"Aranan deðerler için kayýt bulunamadý.",
									Toast.LENGTH_SHORT).show();
						else {
							Toast.makeText(rootView.getContext(),
									"Aranan deðerler için kayýt bulundu.",
									Toast.LENGTH_SHORT).show();
							try {
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("bulunduguOfis_SubeAdi"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("icHatSayisi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("disHatSayisi"));
								s.setSelected(true);
								s.setSelection(list.indexOf(resultObject
										.getString("durum")));

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
				;
				FragmantRest erisim = new FragmantRest();
				if (!((EditText) getView().findViewById(R.id.editText1))
						.getText().toString().equals("")) {
					erisim.execute(erisim.url
							+ "telefonsantrali/searchBarkod/"
							+ ((EditText) getView()
									.findViewById(R.id.editText1)).getText()
									.toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama için barkod no giriniz.", Toast.LENGTH_SHORT)
							.show();
			}
		});
		
		try {
			((EditText) rootView.findViewById(R.id.editText1))
					.setText(getArguments().getString("barkod"));
		} catch (Exception e) {
			// TODO: handle exception
		}

		Button barkodOkutButton = (Button) rootView
				.findViewById(R.id.btnBarkodOkut);
		barkodOkutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QROkutFragment fragment = new QROkutFragment();
				Bundle args = new Bundle();
				args.putString("from", "TelefonSantraliFragment");
				fragment.setArguments(args);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment,
						"Anasayfa");
				fragmentTransaction.commit();

			}
		});

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				getActivity(), android.R.layout.simple_spinner_item, list);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		s.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				TelefonSantrali telefonsantrali = new TelefonSantrali();
				String secilen = list.get(s.getSelectedItemPosition());

				telefonsantrali.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				telefonsantrali.setMarka(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				telefonsantrali.setModel(((EditText) getView().findViewById(
						R.id.editText5)).getText().toString());
				telefonsantrali.setBulunduguOfis_SubeAdi(((EditText) getView()
						.findViewById(R.id.editText6)).getText().toString());
				telefonsantrali.setIcHatSayisi(((EditText) getView()
						.findViewById(R.id.editText7)).getText().toString());
				telefonsantrali.setDisHatSayisi(((EditText) getView()
						.findViewById(R.id.editText8)).getText().toString());
				telefonsantrali.setDurum(secilen);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				telefonsantrali.setKullaniciID(preferences.getInt(
						"kullaniciID", 0));
				telefonsantrali.setBolgeID(preferences.getInt("bolgeID", 1));
				
				if (telefonsantrali.getCihazSeriNo() != null
						&& telefonsantrali.getCihazSeriNo().equals("")
						&& !telefonsantrali.getBarkod().equals(""))
					telefonsantrali.setCihazSeriNo(telefonsantrali.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(telefonsantrali);
				} catch (JsonGenerationException e) {

					e.printStackTrace();
				} catch (JsonMappingException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();
				}
				class FragmantRest extends RestfulErisim {
					@Override
					protected void onPostExecute(String results) {

						super.onPostExecute(results);
						if (results != null)
							Toast.makeText(rootView.getContext(),
									"Kayýt Gönderildi.", Toast.LENGTH_SHORT)
									.show();
						else
							Toast.makeText(rootView.getContext(),
									"Gönderme Ýþlemi Baþarasýz.",
									Toast.LENGTH_SHORT).show();
					}
				}
				;
				FragmantRest erisim = new FragmantRest();

				String hataMesajý = "Lütfen";

				if (telefonsantrali.getBarkod() != null
						&& telefonsantrali.getBarkod().equals(""))
					hataMesajý += " barkod";
				
				if (telefonsantrali.getMarka() != null
						&& telefonsantrali.getMarka().equals(""))
					hataMesajý += " marka";

				if (telefonsantrali.getModel() != null
						&& telefonsantrali.getModel().equals(""))
					hataMesajý += " model";

				if (hataMesajý != "Lütfen"
						&& telefonsantrali.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "telefonsantrali", gs);
					TelefonSantraliFragment fragment = new TelefonSantraliFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(telefonsantrali.toString());

			}
		});

	}
}