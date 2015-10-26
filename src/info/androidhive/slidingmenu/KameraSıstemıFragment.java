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
import entity.KsKamera;
import entity.RestfulErisim;

public class KameraSýstemýFragment extends Fragment {
	
	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Button barkodaraButon;
	Spinner spinnerDurum;
	Spinner spinnerYer;
	Spinner markaSpinner;
	Sqlite db;
	final List<String> durumList=new ArrayList<String>();
	final List<String> yerList = new ArrayList<String>();
	final List<String> markaList = new ArrayList<String>();
	View rootView ;
	
	public KameraSýstemýFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.kamera, container, false);
        
        araButon = (Button) rootView.findViewById(R.id.buttonAra);
        barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				KameraSýstemýFragment fragment = new KameraSýstemýFragment();
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
		for (String marka : db.getAll("e5", "MARKA")) {
			markaList.add(marka);
		}
        
        spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
        spinnerYer = (Spinner) rootView.findViewById(R.id.spinnerYer);
        markaSpinner = (Spinner) rootView.findViewById(R.id.spinnerMarka);
        
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
								markaSpinner.setSelected(true);
								markaSpinner.setSelection(markaList.indexOf(resultObject
										.getString("marka")));
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("otobusKoseNo"));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("termId"));
								spinnerYer.setSelected(true);
								spinnerYer.setSelection(yerList.indexOf(resultObject
										.getString("kameraninYeri")));
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
							+ "kskamera/searchBarkod/"+ ((EditText) getView().findViewById(R.id.editText1)).getText().toString());
				} else
					Toast.makeText(rootView.getContext(),
							"Arama için barkod no giriniz.",
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
									"Aranan deðerler için kayýt bulunamadý.",
									Toast.LENGTH_SHORT).show();
						else {
							Toast.makeText(rootView.getContext(),
									"Aranan deðerler için kayýt bulundu.",
									Toast.LENGTH_SHORT).show();
							try {
								markaSpinner.setSelected(true);
								markaSpinner.setSelection(markaList.indexOf(resultObject
										.getString("marka")));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("otobusKoseNo"));
								spinnerYer.setSelected(true);
								spinnerYer.setSelection(yerList.indexOf(resultObject
										.getString("kameraninYeri")));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("termId"));
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
				if (!((EditText) getView().findViewById(R.id.editText4))
								.getText().toString().equals("")) {
					erisim.execute(erisim.url
							+ "kskamera/searchSeriNo/"
							+ ((EditText) getView()
									.findViewById(R.id.editText4)).getText()
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

		Button barkodOkutButton = (Button) rootView.findViewById(R.id.btnBarkodOkut);
		barkodOkutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QROkutFragment fragment = new QROkutFragment();
				Bundle args = new Bundle();
				args.putString("from", "KameraSýstemýFragment");
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
        
        dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, markaList);
		markaSpinner.setAdapter(dataAdapter);
		
		  
        yerList.add("ON");
		yerList.add("ORTA");
        yerList.add("ARKA");
        
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, yerList);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerYer.setAdapter(dataAdapter1);
         
        return rootView;
    }
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {	

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				KsKamera kamera = new KsKamera();
				String secilenDurum=durumList.get(spinnerDurum.getSelectedItemPosition());
				String secilenYer=yerList.get(spinnerYer.getSelectedItemPosition());
				String secilenMarka = null;

				try {
					secilenMarka = markaList.get(markaSpinner
							.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}
				kamera.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				kamera.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				kamera.setMarka(secilenMarka);
				kamera.setOtobusKoseNo(((EditText) getView().findViewById(	
						R.id.editText7)).getText().toString());
				kamera.setKameraninYeri(secilenYer);
				kamera.setTermId(((EditText) getView().findViewById(
						R.id.editText9)).getText().toString());
				kamera.setDurum(secilenDurum);
				
				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				kamera.setKullaniciID(preferences.getInt("kullaniciID",0));
				kamera.setBolgeID(preferences.getInt("bolgeID",1));
				
				if (kamera.getCihazSeriNo() != null
						&& kamera.getCihazSeriNo().equals("")
						&& !kamera.getBarkod().equals(""))
					kamera.setCihazSeriNo(kamera.getBarkod());
				
				ObjectMapper mapper=new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(kamera);
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
							Toast.makeText(rootView.getContext(), "Kayýt Gönderildi.", Toast.LENGTH_SHORT).show();
						else
							Toast.makeText(rootView.getContext(), "Gönderme Ýþlemi Baþarasýz.", Toast.LENGTH_SHORT).show();
					}
				};
				FragmantRest erisim = new FragmantRest();

				String hataMesajý = "Lütfen";

				if (kamera.getBarkod() != null
						&& kamera.getBarkod().equals(""))
					hataMesajý += " barkod";

				if (kamera.getCihazSeriNo().equals(kamera.getBarkod()) && !kamera.getBarkod().equals("")) {
					kamera.setCihazSeriNo(kamera.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no boþ olduðu için barkod deðeriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (secilenMarka != null && secilenMarka.equals(""))
					hataMesajý += " marka";

				if (hataMesajý != "Lütfen" && kamera.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "kskamera", gs);
					KameraSýstemýFragment fragment = new KameraSýstemýFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(kamera.toString());

			}
		});

	}
}