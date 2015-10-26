package info.androidhive.slidingmenu;

import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import entity.RestfulErisim;

public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;

	private CharSequence mTitle;

	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ActionBar actionBar;
	private Menu optionsMenu;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		actionBar = getActionBar();

		actionBar.setDisplayShowTitleEnabled(false);

		mTitle = mDrawerTitle = getTitle();

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[7], navMenuIcons
				.getResourceId(7, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[8], navMenuIcons
				.getResourceId(8, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[9], navMenuIcons
				.getResourceId(9, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[10], navMenuIcons
				.getResourceId(10, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[11], navMenuIcons
				.getResourceId(11, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[12], navMenuIcons
				.getResourceId(12, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[13], navMenuIcons
				.getResourceId(13, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[14], navMenuIcons
				.getResourceId(14, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[15], navMenuIcons
				.getResourceId(15, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[16], navMenuIcons
				.getResourceId(16, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[17], navMenuIcons
				.getResourceId(17, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[18], navMenuIcons
				.getResourceId(18, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[19], navMenuIcons
				.getResourceId(19, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[20], navMenuIcons
				.getResourceId(20, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[21], navMenuIcons
				.getResourceId(21, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[22], navMenuIcons
				.getResourceId(22, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[23], navMenuIcons
				.getResourceId(23, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[24], navMenuIcons
				.getResourceId(24, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[25], navMenuIcons
				.getResourceId(25, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[26], navMenuIcons
				.getResourceId(26, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[27], navMenuIcons
				.getResourceId(27, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[28], navMenuIcons
				.getResourceId(28, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[29], navMenuIcons
				.getResourceId(29, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[30], navMenuIcons
				.getResourceId(30, -1)));

		navDrawerItems.add(new NavDrawerItem(navMenuTitles[31], navMenuIcons
				.getResourceId(31, -1)));

		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.dot, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);

				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);

				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {

			displayView(0);
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		new LogoutRest().execute(RestfulErisim.url + "kullanici/logout/"
				+ preferences.getInt("kullaniciID", 0));
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		new LogoutRest().execute(RestfulErisim.url + "kullanici/logout/"
				+ preferences.getInt("kullaniciID", 0));
	}

	@Override
	public void onBackPressed() {
		HomeFragment fragment = new HomeFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame_container, fragment, "Anasayfa");
		fragmentTransaction.commit();
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {

			displayView(position);
		}
	}

	class LogoutRest extends RestfulErisim {
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
			if (results != null) {
				try {
					JSONObject result = new JSONObject(results);
					if (result.getBoolean("result")) {
						finish();
					} else {
						Toast.makeText(getApplicationContext(),
								result.getString("error"), Toast.LENGTH_SHORT)
								.show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		this.optionsMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		new LogoutRest().execute(RestfulErisim.url + "kullanici/logout/"
				+ preferences.getInt("kullaniciID", 0));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		HomeFragment fragment = new HomeFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		switch (item.getItemId()) {
		case R.id.action_settings:
			break;
		case R.id.home:
			fragmentTransaction.replace(R.id.frame_container, fragment,
					"Anasayfa");
			fragmentTransaction.commit();
			break;
		case R.id.exit:
			onDestroy();
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	private void displayView(int position) {

		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeFragment();
			break;
		case 1:
			fragment = new ValýdatorOtobusFragment();
			break;
		case 2:
			fragment = new ValýdatorTurnýkeFragment();
			break;
		case 3:
			fragment = new KýoskFragment();
			break;
		case 4:
			fragment = new AracIcýEkranFragment();
			break;
		case 5:
			fragment = new KameraSýstemýFragment();
			break;

		case 6:
			fragment = new KameraNVRFragment();
			break;

		case 7:
			fragment = new KameraGücUnitesiFragment();
			break;

		case 8:
			fragment = new KameraSwitchFragment();
			break;

		case 9:
			fragment = new GuzergahPanosuFragment();
			break;

		case 10:
			fragment = new AracTakipFragment();
			break;

		case 11:
			fragment = new AkýllýDurakFragment();
			break;

		case 12:
			fragment = new ParkomatFragment();
			break;

		case 13:
			fragment = new ElTerminaliFragment();
			break;

		case 14:
			fragment = new TurnikeFragment();
			break;

		case 15:
			fragment = new PosFragment();
			break;

		case 16:
			fragment = new UpsFragment();
			break;

		case 17:
			fragment = new UpsResimFragment();
			break;

		case 18:
			fragment = new SunucuFragment();
			break;

		case 19:
			fragment = new LcdFragment();
			break;

		case 20:
			fragment = new KabinetFragment();
			break;

		case 21:
			fragment = new NetworkSwitchFragment();
			break;

		case 22:
			fragment = new NetworkRouterFragment();
			break;

		case 23:
			fragment = new NetworkModemFragment();
			break;

		case 24:
			fragment = new PrinterFragment();
			break;

		case 25:
			fragment = new NotebookFragment();
			break;

		case 26:
			fragment = new CepTelefonuFragment();
			break;

		case 27:
			fragment = new MasaFragment();
			break;

		case 28:
			fragment = new MasaTelefonuFragment();
			break;

		case 29:
			fragment = new TelefonSantraliFragment();
			break;

		case 30:
			fragment = new CctvFragment();
			break;

		case 31:
			fragment = new PdksFragment();
			break;

		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {

			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		mDrawerToggle.onConfigurationChanged(newConfig);
	}

}
