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
import entity.ValidatorOtobusTipi;

public class Val�datorOtobusFragment extends Fragment {

	protected static final String EditText = null;
	Button kaydetButon;
	Button araButon;
	Button temizleButon;
	Spinner spinnerDurum;
	Button barkodaraButon;
	Spinner markaSpinner;
	Spinner modelSpinner;
	Spinner ozellikSpinner;
	Spinner alttipiSpinner;
	Spinner kullanimsekliSpinner;
	Sqlite db;
	final List<String> durumList = new ArrayList<String>();
	final List<String> markaList = new ArrayList<String>();
	final List<String> modelList = new ArrayList<String>();
	final List<String> ozellikList = new ArrayList<String>();
	final List<String> alttipiList = new ArrayList<String>();
	final List<String> kullanimsekliList = new ArrayList<String>();
	View rootView;

	public Val�datorOtobusFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.otobus, container, false);

		araButon = (Button) rootView.findViewById(R.id.buttonAra);
		barkodaraButon = (Button) rootView.findViewById(R.id.buttonBarkodAra);
		temizleButon = (Button) rootView.findViewById(R.id.buttonTemizle);
		temizleButon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Val�datorOtobusFragment fragment = new Val�datorOtobusFragment();
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
		for (String marka : db.getAll("e1", "MARKA")) {
			markaList.add(marka);
		}

		db = new Sqlite(rootView.getContext());
		for (String model : db.getAll("e1", "MODEL")) {
			modelList.add(model);
		}

		db = new Sqlite(rootView.getContext());
		for (String ozellik : db.getAll("e1", "OZELLIK")) {
			ozellikList.add(ozellik);
		}

		db = new Sqlite(rootView.getContext());
		for (String altTipi : db.getAll("e1", "ALTTIPI")) {
			alttipiList.add(altTipi);
		}

		db = new Sqlite(rootView.getContext());
		for (String kullanimSekli : db.getAll("e1", "KULLANIMSEKLI")) {
			kullanimsekliList.add(kullanimSekli);
		}

		spinnerDurum = (Spinner) rootView.findViewById(R.id.spinner1);
		markaSpinner = (Spinner) rootView.findViewById(R.id.spinnerMarka);
		modelSpinner = (Spinner) rootView.findViewById(R.id.spinnerModel);
		ozellikSpinner = (Spinner) rootView.findViewById(R.id.spinnerOzellik);
		alttipiSpinner = (Spinner) rootView.findViewById(R.id.spinnerAlttipi);
		kullanimsekliSpinner = (Spinner) rootView
				.findViewById(R.id.spinnerKullanimsekli);

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
								markaSpinner.setSelected(true);
								markaSpinner.setSelection(markaList
										.indexOf(resultObject
												.getString("marka")));
								modelSpinner.setSelected(true);
								modelSpinner.setSelection(modelList
										.indexOf(resultObject
												.getString("model")));
								ozellikSpinner.setSelected(true);
								ozellikSpinner.setSelection(ozellikList
										.indexOf(resultObject
												.getString("ozellik")));
								alttipiSpinner.setSelected(true);
								alttipiSpinner.setSelection(alttipiList
										.indexOf(resultObject
												.getString("altTipi")));
								kullanimsekliSpinner.setSelected(true);
								kullanimsekliSpinner.setSelection(kullanimsekliList
										.indexOf(resultObject
												.getString("kullanimSekli")));
								((EditText) getView().findViewById(
										R.id.editText3)).setText(resultObject
										.getString("cihazSeriNo"));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("otobusKoseNumarasi"));
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
							+ "validatorotobustipi/searchBarkod/"
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
								markaSpinner.setSelected(true);
								markaSpinner.setSelection(markaList
										.indexOf(resultObject
												.getString("marka")));
								modelSpinner.setSelected(true);
								modelSpinner.setSelection(modelList
										.indexOf(resultObject
												.getString("model")));
								ozellikSpinner.setSelected(true);
								ozellikSpinner.setSelection(ozellikList
										.indexOf(resultObject
												.getString("ozellik")));
								alttipiSpinner.setSelected(true);
								alttipiSpinner.setSelection(alttipiList
										.indexOf(resultObject
												.getString("altTipi")));
								kullanimsekliSpinner.setSelected(true);
								kullanimsekliSpinner.setSelection(kullanimsekliList
										.indexOf(resultObject
												.getString("kullanimSekli")));
								((EditText) getView().findViewById(
										R.id.editText1)).setText(resultObject
										.getString("barkod"));
								((EditText) getView().findViewById(
										R.id.editText9)).setText(resultObject
										.getString("otobusKoseNumarasi"));
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
							+ "validatorotobustipi/searchSeriNo/"
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
				args.putString("from", "Val�datorOtobusFragment");
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

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, ozellikList);
		ozellikSpinner.setAdapter(dataAdapter);

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, alttipiList);
		alttipiSpinner.setAdapter(dataAdapter);

		dataAdapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, kullanimsekliList);
		kullanimsekliSpinner.setAdapter(dataAdapter);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

		kaydetButon = (Button) getView().findViewById(R.id.button1);
		kaydetButon.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				ValidatorOtobusTipi otobus = new ValidatorOtobusTipi();
				String secilenDurum = durumList.get(spinnerDurum
						.getSelectedItemPosition());
				String secilenMarka = null;
				String secilenModel = null;
				String secilenOzellik = null;
				String secilenAlttipi = null;
				String secilenKullanimsekli = null;

				try {
					secilenMarka = markaList.get(markaSpinner
							.getSelectedItemPosition());

					secilenModel = modelList.get(modelSpinner
							.getSelectedItemPosition());

					secilenOzellik = ozellikList.get(ozellikSpinner
							.getSelectedItemPosition());

					secilenAlttipi = alttipiList.get(alttipiSpinner
							.getSelectedItemPosition());

					secilenKullanimsekli = kullanimsekliList
							.get(kullanimsekliSpinner.getSelectedItemPosition());
				} catch (Exception e) {
					// TODO: handle exception
				}
				otobus.setBarkod(((EditText) getView().findViewById(
						R.id.editText1)).getText().toString());
				otobus.setCihazSeriNo(((EditText) getView().findViewById(
						R.id.editText3)).getText().toString());
				otobus.setMarka(secilenMarka);
				otobus.setModel(secilenModel);
				otobus.setOzellik(secilenOzellik);
				otobus.setAltTipi(secilenAlttipi);
				otobus.setKullanimSekli(secilenKullanimsekli);
				otobus.setOtobusKoseNumarasi(((EditText) getView()
						.findViewById(R.id.editText9)).getText().toString());
				otobus.setDurum(secilenDurum);

				SharedPreferences preferences;
				preferences = PreferenceManager
						.getDefaultSharedPreferences(getView().getContext());
				final SharedPreferences.Editor editor = preferences.edit();
				otobus.setKullaniciID(preferences.getInt("kullaniciID", 0));
				otobus.setBolgeID(preferences.getInt("bolgeID", 1));

				if (otobus.getCihazSeriNo() != null
						&& otobus.getCihazSeriNo().equals("")
						&& !otobus.getBarkod().equals(""))
					otobus.setCihazSeriNo(otobus.getBarkod());

				ObjectMapper mapper = new ObjectMapper();
				String gs = null;
				try {
					gs = mapper.writeValueAsString(otobus);
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
									"Kay�t G�nderildi.", Toast.LENGTH_SHORT)
									.show();
						else
							Toast.makeText(rootView.getContext(),
									"G�nderme ��lemi Ba�ar�s�z.",
									Toast.LENGTH_SHORT).show();
					}
				}
				;
				FragmantRest erisim = new FragmantRest();

				String hataMesaj� = "L�tfen";

				if (otobus.getBarkod() != null && otobus.getBarkod().equals(""))
					hataMesaj� += " barkod";

				if (otobus.getCihazSeriNo().equals(otobus.getBarkod())
						&& !otobus.getBarkod().equals("")) {
					otobus.setCihazSeriNo(otobus.getBarkod());
					Toast.makeText(
							rootView.getContext(),
							"Cihaz seri no bo� oldu�u i�in barkod de�eriyle kaydedildi.",
							Toast.LENGTH_SHORT).show();
				}

				if (secilenMarka != null && secilenMarka.equals(""))
					hataMesaj� += " marka";

				if (secilenModel != null && secilenModel.equals(""))
					hataMesaj� += " model";

				if (hataMesaj� != "L�tfen" && otobus.getBarkod().equals("")) {
					hataMesaj� += " giriniz";
					Toast.makeText(rootView.getContext(), hataMesaj�,
							Toast.LENGTH_SHORT).show();

				} else {
					erisim.execute(RestfulErisim.url + "validatorotobustipi",
							gs);
					Val�datorOtobusFragment fragment = new Val�datorOtobusFragment();
					FragmentManager fragmentManager = getFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager
							.beginTransaction();
					fragmentTransaction.replace(R.id.frame_container, fragment,
							getTag());
					fragmentTransaction.commit();
				}

				System.out.println(otobus.toString());

			}
		});

	}
}