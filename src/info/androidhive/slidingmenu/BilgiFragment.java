package info.androidhive.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class BilgiFragment extends Fragment {

	ListView bilgiler;
	final List<String> bilgiList = new ArrayList<String>();
	Sqlite db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		final View rootView = inflater.inflate(R.layout.bilgi, container, false);

		bilgiler = (ListView) rootView.findViewById(R.id.listView1);

		db = new Sqlite(rootView.getContext());
//		for (String bilgi : db.getAllBilgi()) {
//			bilgiList.add(bilgi);
//		}

//		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//				rootView.getContext(), android.R.layout.simple_list_item_1,
//				bilgiList);
//
//		bilgiler.setAdapter(arrayAdapter);

		return rootView;
	}

}
