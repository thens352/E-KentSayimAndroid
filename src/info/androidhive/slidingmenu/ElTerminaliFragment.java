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
import entity.MufettisElTerminali;
import entity.RestfulErisim;

public class ElTerminaliFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Spinner spinnerDurum;
	Button barkodaraButon;
	final List<String> durumList = new ArrayList<String>();
	View rootView;

	public ElTerminaliFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.elterminali, container, false);

		araButon = (Button) rootView.findViewById(R.id.buttonAra);
		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ElTerminaliFragment fragment = new ElTerminaliFragment();
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment,
						"Anasayfa");
				fragmentTransaction.commit();
			}
		});

		durumList.add("SAHADA");
		durumList.add("DEPODA");
		durumList.add("HURDA");

		spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);

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
									"Aranan de�erler i�in kay�t bulunamad�.",
									Toast.LENGTH_SHORT).show();
						else {
							Toast.makeText(rootView.getContext(),
									"Aranan de�erler i�in kay�t bulundu.",
									Toast.LENGTH_SHORT).show();
							try {
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("kullaniciAdiSoyadi"));
								spinnerDurum.setSelected(true);
								spinnerDurum.setSelection(durumList
										.indexOf(resultObject
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
							+ "mufettiselterminali/searchBarkod/"
							+ ((EditText) getView()
									.findViewById(R.id.editText1)).getText()
									.toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama i�in barkod no giriniz.", Toast.LENGTH_SHORT)
							.show();
			}
		});

		araButon.setOnClickListener(new OnClickListener() {

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
									"Aranan de�erler i�in kay�t bulunamad�.",
									Toast.LENGTH_SHORT).show();
						else {
							Toast.makeText(rootView.getContext(),
									"Aranan de�erler i�in kay�t bulundu.",
									Toast.LENGTH_SHORT).show();
							try {
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("kullaniciAdiSoyadi"));
								spinnerDurum.setSelected(true);
								spinnerDurum.setSelection(durumList
										.indexOf(resultObject
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
				if (!((EditText) getView().findViewById(R.id.editText3))
						.getText().toString().equals("")) {
					erisim.execute(erisim.url
							+ "mufettiselterminali/searchSeriNo/"
							+ ((EditText) getView()
									.findViewById(R.id.editText3)).getText()
									.toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama i�in cihaz seri no giriniz.",
							Toast.LENGTH_SHORT).show();
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
				args.putString("from", "ElTerminaliFragment");
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
				getActivity(), android.R.layout.simple_spinner_item, durumList);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDurum.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				MufettisElTerminali elterminali = new MufettisElTerminali();
				String secilenDurum = durumList.get(spinnerDurum
						.getSelectedItemPosition());
				
				elterminali.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				elterminali.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				elterminali.setMarka(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				elterminali.setModel(((EditText) getView().findViewById(
						R.id.editText5)).getText().toString());
				elterminali.setKullaniciAdiSoyadi(((EditText) getView()
						.findViewById(R.id.editText6)).getText().toString());
				elterminali.setDurum(secilenDurum);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				elterminali.setKullaniciID(preferences.getInt("kullaniciID", 0));
				elterminali.setBolgeID(preferences.getInt("bolgeID", 1));
				
				if (elterminali.getCihazSeriNo() != null
						&& elterminali.getCihazSeriNo().equals("")
						&& !elterminali.getBarkod().equals(""))
					elterminali.setCihazSeriNo(elterminali.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(elterminali);
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
									"Kay�t G�nderildi.", Toast.LENGTH_SHORT)
									.show();
						else
							Toast.makeText(rootView.getContext(),
									"G�nderme ��lemi Ba�aras�z.",
									Toast.LENGTH_SHORT).show();
					}
				}
				;
				FragmantRest erisim = new FragmantRest();

				String hataMesaj� = "L�tfen";

				if (elterminali.getBarkod() != null
						&& elterminali.getBarkod().equals(""))
					hataMesaj� += " barkod";

				if (elterminali.getCihazSeriNo()
						.equals(elterminali.getBarkod())
						&& !elterminali.getBarkod().equals("")) {
					elterminali.setCihazSeriNo(elterminali.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no bo� oldu�u i�in barkod de�eriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (elterminali.getMarka() != null
						&& elterminali.getMarka().equals(""))
					hataMesaj� += " marka";

				if (elterminali.getModel() != null
						&& elterminali.getModel().equals(""))
					hataMesaj� += " model";

				if (hataMesaj� != "L�tfen"
						&& elterminali.getBarkod().equals("")) {
					hataMesaj� += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesaj�,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "mufettiselterminali",
							gs);
					ElTerminaliFragment fragment = new ElTerminaliFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(elterminali.toString());

			}
		});

	}
}