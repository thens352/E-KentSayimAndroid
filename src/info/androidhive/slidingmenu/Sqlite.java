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
import org.json.JSONArray;
import org.json.JSONException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import entity.RestfulErisim;

public class Sqlite extends SQLiteOpenHelper {

	private JSONArray parametrelerJSON;

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "ekentsayim_db";

	public Sqlite(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static final String Ekran = "ekran";
	public static final String Alan = "alan";
	public static final String Deger = "deger";
	public static final String Tablo_Adi = "parametre";

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE parametre "
				+ "( id INTEGER PRIMARY KEY AUTOINCREMENT, " + "alan TEXT, "
				+ "ekran TEXT, deger TEXT ) ";
		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS parametre");
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);

		if (!db.isReadOnly()) {
			db.execSQL("PRAGMA foreign_keys=ON;");
		}
	}

	public void parametreEkle(String ekran, String alan, String deger) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("ekran", ekran);
		values.put("alan", alan);
		values.put("deger", deger);
		db.insert("parametre", null, values);
		db.close();
	}

	public void dbGuncelle() {
		new ParametreRest().execute(RestfulErisim.url + "parametre");
	}

	public void reset() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("parametre", null, null);
		db.close();
	}

	public List<String> getAll(String ekran, String alan) {
		List<String> resultList = new ArrayList<String>();
		String selectQuery = "SELECT deger FROM parametre WHERE ekran='"
				+ ekran + "' and alan='" + alan + "'";

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				resultList.add(cursor.getString(0));
			} while (cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return resultList;
	}

	class ParametreRest extends RestfulErisim {

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
			try {
				parametrelerJSON = new JSONArray(results);
				for (int i = 0; i < parametrelerJSON.length(); i++) {
					parametreEkle(
							parametrelerJSON.getJSONObject(i)
									.getString("ekran"), parametrelerJSON
									.getJSONObject(i).getString("alan"),
							parametrelerJSON.getJSONObject(i)
									.getString("deger"));
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Context getActivity() {
		// TODO Auto-generated method stub
		return null;
	}
}
