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
import entity.Parkomat;
import entity.RestfulErisim;

public class ParkomatFragment extends Fragment {
	
	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Spinner spinnerDurum;
	Button barkodaraButon;
	Sqlite db;
	final List<String> durumList=new ArrayList<String>();
	View rootView;
	public ParkomatFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.parkomat, container, false);
        
        araButon = (Button) rootView.findViewById(R.id.buttonAra);
        barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ParkomatFragment fragment = new ParkomatFragment();
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
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("adresi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("durakAdi"));
								spinnerDurum.setSelected(true);
								spinnerDurum.setSelection(durumList.indexOf(resultObject
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
							+ "parkomat/searchBarkod/"+ ((EditText) getView().findViewById(R.id.editText1)).getText().toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama i�in barkod no giriniz.",
							Toast.LENGTH_SHORT).show();
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
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("adresi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("durakAdi"));
								spinnerDurum.setSelected(true);
								spinnerDurum.setSelection(durumList.indexOf(resultObject
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
							+ "parkomat/searchSeriNo/"
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

		Button barkodOkutButton = (Button) rootView.findViewById(R.id.btnBarkodOkut);
		barkodOkutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QROkutFragment fragment = new QROkutFragment();
				Bundle args = new Bundle();
				args.putString("from", "ParkomatFragment");
				fragment.setArguments(args);
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager
						.beginTransaction();
				fragmentTransaction.replace(R.id.frame_container, fragment,
						"Anasayfa");
				fragmentTransaction.commit();

			}
		});
        
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, durumList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

				Parkomat parkomat = new Parkomat();
				String secilenDurum=durumList.get(spinnerDurum.getSelectedItemPosition());
				parkomat.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				parkomat.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				parkomat.setGpsKoordinati(latLon);
				parkomat.setAdresi(((EditText) getView().findViewById(
						R.id.editText7)).getText().toString());
				parkomat.setDurakAdi(((EditText) getView().findViewById(
						R.id.editText8)).getText().toString());
				parkomat.setDurum(secilenDurum);	
				
				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				parkomat.setKullaniciID(preferences.getInt("kullaniciID",0));
				parkomat.setBolgeID(preferences.getInt("bolgeID",1));
				
				if (parkomat.getCihazSeriNo() != null
						&& parkomat.getCihazSeriNo().equals("")
						&& !parkomat.getBarkod().equals(""))
					parkomat.setCihazSeriNo(parkomat.getBarkod());
				
				ObjectMapper mapper=new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(parkomat);
				} catch (JsonGenerationException e) {
					
					e.printStackTrace();
				} catch (JsonMappingException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
				
					e.printStackTrace();
				}
				class FragmantRest extends RestfulErisim{
					@Override
					protected void onPostExecute(String results) {
						
						super.onPostExecute(results);
						if(results!=null)
							Toast.makeText(rootView.getContext(), "Kay�t G�nderildi.", Toast.LENGTH_SHORT).show();
						else
							Toast.makeText(rootView.getContext(), "G�nderme ��lemi Ba�aras�z.", Toast.LENGTH_SHORT).show();
					}
				};
				FragmantRest erisim = new FragmantRest();

				String hataMesaj� = "L�tfen";

				if (parkomat.getBarkod() != null
						&& parkomat.getBarkod().equals(""))
					hataMesaj� += " barkod";

				if (parkomat.getCihazSeriNo().equals(parkomat.getBarkod()) && !parkomat.getBarkod().equals("")) {
					parkomat.setCihazSeriNo(parkomat.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no bo� oldu�u i�in barkod de�eriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (hataMesaj� != "L�tfen" && parkomat.getBarkod().equals("")) {
					hataMesaj� += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesaj�,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "parkomat", gs);
					ParkomatFragment fragment = new ParkomatFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(parkomat.toString());

			}
		});

	}
}
