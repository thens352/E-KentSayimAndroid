package info.androidhive.slidingmenu.adapter;

import info.androidhive.slidingmenu.R;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import entity.AkilliDurak;

public class AkilliDurakListesiAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<AkilliDurak> akilliDuraklar;

	public AkilliDurakListesiAdapter(Activity activity,
			List<AkilliDurak> akilliDurakList) {
		mInflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.akilliDuraklar = akilliDurakList;
	}

	@Override
	public int getCount() {
		return akilliDuraklar.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return akilliDuraklar.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;

		view = mInflater.inflate(R.layout.akillidurak_satir, null);

		TextView barkod = (TextView) view.findViewById(R.id.barkod);
		TextView gps = (TextView) view.findViewById(R.id.gps);
		TextView marka = (TextView) view.findViewById(R.id.marka);
		TextView model = (TextView) view.findViewById(R.id.model);
		TextView cihazSeriNo = (TextView) view.findViewById(R.id.cihazSeriNo);
		TextView durum = (TextView) view.findViewById(R.id.durum);
		TextView durakAdi = (TextView) view.findViewById(R.id.durakAdi);
		TextView termID = (TextView) view.findViewById(R.id.termID);

		AkilliDurak akilliDurak = akilliDuraklar.get(position);

		barkod.setText(akilliDurak.getBarkod());
		gps.setText(akilliDurak.getGpsKoordinati());
		marka.setText(akilliDurak.getMarka());
		model.setText(akilliDurak.getModel());
		durum.setText(akilliDurak.getDurum());
		durakAdi.setText(akilliDurak.getDurakAdi());
		termID.setText(akilliDurak.getTermId());

		return view;
	}

}
