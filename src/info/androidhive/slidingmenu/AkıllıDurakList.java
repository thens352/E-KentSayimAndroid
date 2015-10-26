package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.adapter.AkilliDurakListesiAdapter;

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
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import entity.AkilliDurak;
import entity.RestfulErisim;

public class AkýllýDurakList extends Fragment {

	View rootView;
	ListView akilliDurakListView;
	JSONArray akilliDuraklar;
	List<AkilliDurak> akilliDurakList = new ArrayList<AkilliDurak>();

	class AkilliDurakRest extends RestfulErisim {

		@Override
		protected String doInBackground(String... params) {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse response;
			String responseString = null;
			try {
				response = httpclient.execute(new HttpGet(params[0]));
				StatusLine statusLine = response.getStatusLine();
				if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
					ByteArrayOutputStream out = new ByteArrayOutputStream();
					response.getEntity().writeTo(out);
					responseString = out.toString();
					out.close();
				} else {
					// Closes the connection.
					responseString = "error";
					throw new IOException(statusLine.getReasonPhrase());
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
			akilliDurakList = new ArrayList<AkilliDurak>();
			try {
				akilliDuraklar = new JSONArray(results);
				for (int i = 0; i < akilliDuraklar.length(); i++) {
					AkilliDurak akilliDurak = new AkilliDurak();
					 akilliDurak.setBarkod(akilliDuraklar.getJSONObject(i)
					 .getString("barkod"));
					 akilliDurak.setMarka(akilliDuraklar.getJSONObject(i)
					 .getString("marka"));
					 akilliDurak.setModel(akilliDuraklar.getJSONObject(i)
					 .getString("model"));
					 akilliDurak.setAdresi(akilliDuraklar.getJSONObject(i)
					 .getString("adresi"));
					 akilliDurak.setDurum(akilliDuraklar.getJSONObject(i)
					 .getString("durum"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.akilliduraklistesi, container,
				false);

		akilliDurakListView = (ListView) rootView.findViewById(R.id.listView1);

		AkilliDurakListesiAdapter adapter = new AkilliDurakListesiAdapter(
				getActivity(), akilliDurakList);

		akilliDurakListView.setAdapter(adapter);
		return rootView;
	}
}
