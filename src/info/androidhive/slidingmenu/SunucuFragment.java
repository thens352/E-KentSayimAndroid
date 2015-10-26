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
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
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
import entity.Sunucu;

public class SunucuFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Spinner spinnerDurum;
	Spinner spinnerIsletimsistemi;
	Button barkodaraButon;
	Sqlite db;
	final List<String> durumList = new ArrayList<String>();
	final List<String> isletimsistemiList = new ArrayList<String>();
	View rootView;

	public SunucuFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.sunucu, container, false);

		araButon = (Button) rootView.findViewById(R.id.buttonAra);
		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SunucuFragment fragment = new SunucuFragment();
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

		db = new Sqlite(rootView.getContext());
		for (String uzerindekiIsletimSistemi : db.getAll("e17",
				"UZERINDEKIISLETIMSISTEMI")) {
			isletimsistemiList.add(uzerindekiIsletimSistemi);
		}

		spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
		spinnerIsletimsistemi = (Spinner) rootView
				.findViewById(R.id.spinnerIsletimsistemi);

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
								spinnerIsletimsistemi.setSelected(true);
								spinnerIsletimsistemi.setSelection(isletimsistemiList.indexOf(resultObject
										.getString("uzerindekiIsletimSistemi")));
								((EditText) getView().findViewById(
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("ipNo"));
								((EditText) getView().findViewById(
										R.id.editText10)).setText(resultObject
										.getString("diskYuvaSayisi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("adresi"));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("productNo"));
								((EditText) getView().findViewById(
										R.id.editText11)).setText(resultObject
										.getString("powerSupplySayisi"));
								((EditText) getView().findViewById(
										R.id.editText12)).setText(resultObject
										.getString("lokasyon_Ofis_SubeAdi"));
								((EditText) getView().findViewById(
										R.id.editText13)).setText(resultObject
										.getString("hangiOdada"));
								((EditText) getView().findViewById(
										R.id.editText15)).setText(resultObject
										.getString("uzerindekiUygulamalar"));
								((EditText) getView().findViewById(
										R.id.editText16)).setText(resultObject
										.getString("bulunduguKabininBarkodNo"));
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
							+ "sunucu/searchBarkod/"
							+ ((EditText) getView()
									.findViewById(R.id.editText1)).getText()
									.toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama için barkod no giriniz.", Toast.LENGTH_SHORT)
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
									"Aranan deðerler için kayýt bulunamadý.",
									Toast.LENGTH_SHORT).show();
						else {
							Toast.makeText(rootView.getContext(),
									"Aranan deðerler için kayýt bulundu.",
									Toast.LENGTH_SHORT).show();
							try {
								spinnerIsletimsistemi.setSelected(true);
								spinnerIsletimsistemi.setSelection(isletimsistemiList.indexOf(resultObject
										.getString("uzerindekiIsletimSistemi")));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("ipNo"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText10)).setText(resultObject
										.getString("diskYuvaSayisi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("adresi"));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("productNo"));
								((EditText) getView().findViewById(
										R.id.editText11)).setText(resultObject
										.getString("powerSupplySayisi"));
								((EditText) getView().findViewById(
										R.id.editText12)).setText(resultObject
										.getString("lokasyon_Ofis_SubeAdi"));
								((EditText) getView().findViewById(
										R.id.editText13)).setText(resultObject
										.getString("hangiOdada"));
								((EditText) getView().findViewById(
										R.id.editText15)).setText(resultObject
										.getString("uzerindekiUygulamalar"));
								((EditText) getView().findViewById(
										R.id.editText16)).setText(resultObject
										.getString("bulunduguKabininBarkodNo"));
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
							+ "sunucu/searchSeriNo/"
							+ ((EditText) getView()
									.findViewById(R.id.editText3)).getText()
									.toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama için cihaz seri no giriniz.",
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
				args.putString("from", "SunucuFragment");
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

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, isletimsistemiList);
		spinnerIsletimsistemi.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				LocationManager locationManager = (LocationManager) getActivity()
						.getSystemService(
								getView().getContext().LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				String provider = locationManager.getBestProvider(criteria,
						true);
				Location location = locationManager
						.getLastKnownLocation(provider);
				String latLon = location.getLatitude() + "/"
						+ location.getLongitude();

				Sunucu sunucu = new Sunucu();
				String secilenDurum = durumList.get(spinnerDurum
						.getSelectedItemPosition());
				String secilenIsletimsistemi = null;
				try {
					secilenIsletimsistemi = isletimsistemiList
							.get(spinnerIsletimsistemi
									.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}
				sunucu.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				sunucu.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				sunucu.setMarka(((EditText) getView().findViewById(
						R.id.editText5)).getText().toString());
				sunucu.setModel(((EditText) getView().findViewById(
						R.id.editText6)).getText().toString());
				sunucu.setIpNo(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				sunucu.setGpsKoordinat(latLon);
				sunucu.setAdresi(((EditText) getView().findViewById(
						R.id.editText8)).getText().toString());
				sunucu.setProductNo(((EditText) getView().findViewById(
						R.id.editText9)).getText().toString());
				sunucu.setDiskYuvaSayisi(((EditText) getView().findViewById(
						R.id.editText10)).getText().toString());
				sunucu.setPowerSupplySayisi(((EditText) getView().findViewById(
						R.id.editText11)).getText().toString());
				sunucu.setLokasyon_Ofis_SubeAdi(((EditText) getView()
						.findViewById(R.id.editText12)).getText().toString());
				sunucu.setHangiOdada(((EditText) getView().findViewById(
						R.id.editText13)).getText().toString());
				sunucu.setUzerindekiIsletimSistemi(secilenIsletimsistemi);
				sunucu.setBulunduguKabininBarkodNo(((EditText) getView()
						.findViewById(R.id.editText16)).getText().toString());
				sunucu.setDurum(secilenDurum);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				sunucu.setKullaniciID(preferences.getInt("kullaniciID", 0));
				sunucu.setBolgeID(preferences.getInt("bolgeID", 1));
				
				if (sunucu.getCihazSeriNo() != null
						&& sunucu.getCihazSeriNo().equals("")
						&& !sunucu.getBarkod().equals(""))
					sunucu.setCihazSeriNo(sunucu.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(sunucu);
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

				if (sunucu.getBarkod() != null && sunucu.getBarkod().equals(""))
					hataMesajý += " barkod";

				if (sunucu.getCihazSeriNo().equals(sunucu.getBarkod())
						&& !sunucu.getBarkod().equals("")) {
					sunucu.setCihazSeriNo(sunucu.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no boþ olduðu için barkod deðeriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (hataMesajý != "Lütfen" && sunucu.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "sunucu", gs);
					SunucuFragment fragment = new SunucuFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(sunucu.toString());

			}
		});

	}
}
