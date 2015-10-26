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
import entity.AkilliDurak;
import entity.RestfulErisim;

public class AkýllýDurakFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button barkodaraButon;
	Button temizleButon;
	Button barkodOkutButton;
	Sqlite db;
	Spinner markaSpinner;
	Spinner modelSpinner;
	Spinner spinnerDurum;
	final List<String> durumList = new ArrayList<String>();
	final List<String> markaList = new ArrayList<String>();
	final List<String> modelList = new ArrayList<String>();
	View rootView;

	public AkýllýDurakFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			final Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.akillidurak, container, false);

		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AkýllýDurakFragment fragment = new AkýllýDurakFragment();
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
		for (String marka : db.getAll("e11", "MARKA")) {
			markaList.add(marka);
		}

		db = new Sqlite(rootView.getContext());
		for (String model : db.getAll("e11", "MODEL")) {
			modelList.add(model);
		}

		spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
		markaSpinner = (Spinner) rootView.findViewById(R.id.spinnerMarka);
		modelSpinner = (Spinner) rootView.findViewById(R.id.spinnerModel);

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
								responseString = "error";
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
								markaSpinner.setSelection(markaList
										.indexOf(resultObject
												.getString("marka")));
								modelSpinner.setSelected(true);
								modelSpinner.setSelection(modelList
										.indexOf(resultObject
												.getString("model")));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("termId"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("adresi"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("durakAdi"));
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
							+ "akillidurak/searchBarkod/"
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

		barkodOkutButton = (Button) rootView.findViewById(R.id.btnBarkodOkut);
		barkodOkutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				QROkutFragment fragment = new QROkutFragment();
				Bundle args = new Bundle();
				args.putString("from", "AkýllýDurakFragment");
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
				android.R.layout.simple_spinner_item, markaList);
		markaSpinner.setAdapter(dataAdapter);

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, modelList);
		modelSpinner.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) rootView.findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				LocationManager locationManager = (LocationManager) getActivity()
						.getSystemService(
								rootView.getContext().LOCATION_SERVICE);
				Criteria criteria = new Criteria();
				String provider = locationManager.getBestProvider(criteria,
						true);
				Location location = locationManager
						.getLastKnownLocation(provider);
				String latLon = location.getLatitude() + "/"
						+ location.getLongitude();
				AkilliDurak akilliDurak = new AkilliDurak();
				String secilenDurum = durumList.get(spinnerDurum
						.getSelectedItemPosition());

				String secilenMarka = null;
				String secilenModel = null;

				try {
					secilenMarka = markaList.get(markaSpinner
							.getSelectedItemPosition());

					secilenModel = modelList.get(modelSpinner
							.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}

				akilliDurak.setBarkod(((EditText) rootView
						.findViewById(R.id.editText1)).getText().toString());
				akilliDurak.setMarka(secilenMarka);
				akilliDurak.setModel(secilenModel);
				akilliDurak.setGpsKoordinati(latLon);
				akilliDurak.setAdresi(((EditText) rootView
						.findViewById(R.id.editText7)).getText().toString());
				akilliDurak.setDurakAdi(((EditText) rootView
						.findViewById(R.id.editText8)).getText().toString());
				akilliDurak.setTermId(((EditText) rootView
						.findViewById(R.id.editText9)).getText().toString());
				akilliDurak.setDurum(secilenDurum);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(rootView.getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				akilliDurak.setKullaniciID(preferences.getInt("kullaniciID", 0));
				akilliDurak.setBolgeID(preferences.getInt("bolgeID", 1));

				if (akilliDurak.getCihazSeriNo() != null
						&& akilliDurak.getCihazSeriNo().equals("")
						&& !akilliDurak.getBarkod().equals(""))
					akilliDurak.setCihazSeriNo(akilliDurak.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(akilliDurak);
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
						if (results != "error")
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

				if (akilliDurak.getBarkod() != null
						&& akilliDurak.getBarkod().equals(""))
					hataMesajý += " barkod";

				if (secilenMarka != null && secilenMarka.equals(""))
					hataMesajý += " marka";

				if (secilenModel != null && secilenModel.equals(""))
					hataMesajý += " model";

				if (hataMesajý != "Lütfen"
						&& akilliDurak.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "akillidurak", gs);
					AkýllýDurakFragment fragment = new AkýllýDurakFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(akilliDurak.toString());

			}
		});

	}
}
