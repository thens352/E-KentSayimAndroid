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
import entity.NoteBook_MasaustuPC_Monitor;
import entity.RestfulErisim;

public class NotebookFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Button barkodaraButon;
	Spinner spinnerDurum;
	Spinner isletimsistemiSpinner;
	Spinner cesitSpinner;
	Sqlite db;
	final List<String> durumList = new ArrayList<String>();
	final List<String> isletimsistemiList = new ArrayList<String>();
	final List<String> cesitList = new ArrayList<String>();
	View rootView;

	public NotebookFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.notebook, container, false);

		araButon = (Button) rootView.findViewById(R.id.buttonAra);
		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NotebookFragment fragment = new NotebookFragment();
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

		db = new Sqlite(rootView.getContext());
		for (String uzerindekiIsletimSistemi : db.getAll("e24",
				"UZERINDEKIISLETIMSISTEMI")) {
			isletimsistemiList.add(uzerindekiIsletimSistemi);
		}

		db = new Sqlite(rootView.getContext());
		for (String cesit : db.getAll("e24", "CESIT")) {
			cesitList.add(cesit);
		}

		spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
		isletimsistemiSpinner = (Spinner) rootView
				.findViewById(R.id.spinnerIsletimsistemi);
		cesitSpinner = (Spinner) rootView.findViewById(R.id.spinnerCesit);

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
								isletimsistemiSpinner.setSelected(true);
								isletimsistemiSpinner.setSelection(isletimsistemiList.indexOf(resultObject
										.getString("uzerindekiIsletimSistemi")));
								cesitSpinner.setSelected(true);
								cesitSpinner.setSelection(cesitList
										.indexOf(resultObject
												.getString("cesit")));
								((EditText) getView().findViewById(
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("lokasyon_Ofis_SubeAdi"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("productNo"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("urunSahibi"));
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
							+ "notebookmasaustupcmonitor/searchBarkod/"
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
								isletimsistemiSpinner.setSelected(true);
								isletimsistemiSpinner.setSelection(isletimsistemiList.indexOf(resultObject
										.getString("uzerindekiIsletimSistemi")));
								cesitSpinner.setSelected(true);
								cesitSpinner.setSelection(cesitList
										.indexOf(resultObject
												.getString("cesit")));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText4)).setText(resultObject
										.getString("marka"));
								((EditText) getView().findViewById(
										R.id.editText5)).setText(resultObject
										.getString("model"));
								((EditText) getView().findViewById(
										R.id.editText6)).setText(resultObject
										.getString("lokasyon_Ofis_SubeAdi"));
								((EditText) getView().findViewById(
										R.id.editText7)).setText(resultObject
										.getString("productNo"));
								((EditText) getView().findViewById(
										R.id.editText8)).setText(resultObject
										.getString("urunSahibi"));
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
							+ "notebookmasaustupcmonitor/searchSeriNo/"
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
				args.putString("from", "NotebookFragment");
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
		isletimsistemiSpinner.setAdapter(dataAdapter);

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, cesitList);
		cesitSpinner.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				NoteBook_MasaustuPC_Monitor notebook = new NoteBook_MasaustuPC_Monitor();
				String secilenDurum = durumList.get(spinnerDurum
						.getSelectedItemPosition());
				String secilenIsletimsistemi = null;
				String secilenCesit = null;
				try {
					secilenIsletimsistemi = isletimsistemiList
							.get(isletimsistemiSpinner
									.getSelectedItemPosition());
					secilenCesit = cesitList.get(cesitSpinner
							.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}

				notebook.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				notebook.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				notebook.setMarka(((EditText) getView().findViewById(
						R.id.editText4)).getText().toString());
				notebook.setModel(((EditText) getView().findViewById(
						R.id.editText5)).getText().toString());
				notebook.setLokasyon_Ofis_SubeAdi(((EditText) getView()
						.findViewById(R.id.editText6)).getText().toString());
				notebook.setProductNo(((EditText) getView().findViewById(
						R.id.editText7)).getText().toString());
				notebook.setUrunSahibi(((EditText) getView().findViewById(
						R.id.editText8)).getText().toString());
				notebook.setCesit(secilenCesit);
				notebook.setUzerindekiIsletimSistemi(secilenIsletimsistemi);
				notebook.setDurum(secilenDurum);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				notebook.setKullaniciID(preferences.getInt("kullaniciID", 0));
				notebook.setBolgeID(preferences.getInt("bolgeID", 1));
				if (notebook.getCihazSeriNo() != null
						&& notebook.getCihazSeriNo().equals("")
						&& !notebook.getBarkod().equals(""))
					notebook.setCihazSeriNo(notebook.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(notebook);
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

				if (notebook.getBarkod() != null
						&& notebook.getBarkod().equals(""))
					hataMesajý += " barkod";

				if (notebook.getCihazSeriNo().equals(notebook.getBarkod())
						&& !notebook.getBarkod().equals("")) {
					notebook.setCihazSeriNo(notebook.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no boþ olduðu için barkod deðeriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (hataMesajý != "Lütfen" && notebook.getBarkod().equals("")) {
					hataMesajý += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesajý,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url
							+ "notebookmasaustupcmonitor", gs);
					NotebookFragment fragment = new NotebookFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(notebook.toString());

			}
		});

	}
}