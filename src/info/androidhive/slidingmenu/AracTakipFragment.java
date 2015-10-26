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
import entity.AracTakipSistemi;
import entity.RestfulErisim;

public class AracTakipFragment extends Fragment {
	
	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Spinner spinnerDurum;
	Spinner spinnerHizmetveren;
	Button barkodaraButon;
	Spinner markaSpinner;
	Spinner modelSpinner;
	Sqlite db;
	final List<String> durumList=new ArrayList<String>();
	final List<String> markaList = new ArrayList<String>();
	final List<String> hizmetList = new ArrayList<String>();
	final List<String> modelList = new ArrayList<String>();
	View rootView ;
	
	public AracTakipFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.aractakip, container, false);
        
        araButon = (Button) rootView.findViewById(R.id.buttonAra);
        barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AracTakipFragment fragment = new AracTakipFragment();
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
		for (String marka : db.getAll("e10", "MARKA")) {
			markaList.add(marka);
		}
		
		db = new Sqlite(rootView.getContext());
		for (String model : db.getAll("e10", "MODEL")) {
			modelList.add(model);
		}
		
		db = new Sqlite(rootView.getContext());
		for (String hizmetVerenFirma : db.getAll("e10", "HIZMETVERENFIRMA")) {
			hizmetList.add(hizmetVerenFirma);
		}
        
        spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
        markaSpinner = (Spinner) rootView.findViewById(R.id.spinnerMarka);
        modelSpinner = (Spinner) rootView.findViewById(R.id.spinnerModel);
        spinnerHizmetveren = (Spinner) rootView.findViewById(R.id.spinnerHizmetverenfirma);
        
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
								spinnerHizmetveren.setSelected(true);
								spinnerHizmetveren.setSelection(hizmetList.indexOf(resultObject
										.getString("hizmetVerenFirma")));
								((EditText) getView().findViewById(
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("otobusKoseNo"));
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
							+ "aractakipsistemi/searchBarkod/"+ ((EditText) getView().findViewById(R.id.editText1)).getText().toString());
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
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								spinnerHizmetveren.setSelected(true);
								spinnerHizmetveren.setSelection(hizmetList.indexOf(resultObject
										.getString("hizmetVerenFirma")));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("otobusKoseNo"));
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
							+ "aractakipsistemi/searchSeriNo/"
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

		Button barkodOkutButton = (Button) rootView.findViewById(R.id.btnBarkodOkut);
		barkodOkutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QROkutFragment fragment = new QROkutFragment();
				Bundle args = new Bundle();
				args.putString("from", "AracTakipFragment");
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

				AracTakipSistemi aractakip = new AracTakipSistemi();
				String secilenDurum=durumList.get(spinnerDurum.getSelectedItemPosition());
				String secilenHizmet = null;

				try {
					secilenHizmet = hizmetList.get(spinnerHizmetveren
							.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}
				aractakip.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				aractakip.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				aractakip.setMarka(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				aractakip.setModel(((EditText) getView().findViewById(
						R.id.editText5)).getText().toString());
				aractakip.setHizmetVerenFirma(secilenHizmet);
				aractakip.setOtobusKoseNo(((EditText) getView().findViewById(
						R.id.editText7)).getText().toString());
				aractakip.setDurum(secilenDurum);
				
				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				aractakip.setKullaniciID(preferences.getInt("kullaniciID",0));
				aractakip.setBolgeID(preferences.getInt("bolgeID",1));
				
				if (aractakip.getCihazSeriNo() != null
						&& aractakip.getCihazSeriNo().equals("")
						&& !aractakip.getBarkod().equals(""))
					aractakip.setCihazSeriNo(aractakip.getBarkod());
				
				ObjectMapper mapper=new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(aractakip);
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

				if (aractakip.getBarkod() != null
						&& aractakip.getBarkod().equals(""))
					hataMesajý += " barkod";

				if (aractakip.getCihazSeriNo().equals(aractakip.getBarkod()) && !aractakip.getBarkod().equals("")) {
					aractakip.setCihazSeriNo(aractakip.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no boþ olduðu için barkod deðeriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (aractakip.getMarka() != null
						&& aractakip.getMarka().equals(""))
					hataMesajý += " marka";

				if (aractakip.getModel() != null
						&& aractakip.getModel().equals(""))
					hataMesajý += " model";

				if (hataMesajý != "Lütfen" && aractakip.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "aractakipsistemi", gs);
					AracTakipFragment fragment = new AracTakipFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(aractakip.toString());

			}
		});

	}
}