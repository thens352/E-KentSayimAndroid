package info.androidhive.slidingmenu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.thensa.qrcodereaderview.QRCodeReaderView;
import com.thensa.qrcodereaderview.QRCodeReaderView.OnQRCodeReadListener;

public class QROkutFragment extends Fragment implements OnQRCodeReadListener {

	private QRCodeReaderView mydecoderview;
	private ImageView line_image;

	public QROkutFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_qrokut, container,
				false);

		mydecoderview = (QRCodeReaderView) rootView
				.findViewById(R.id.qrdecoderview);
		mydecoderview.setOnQRCodeReadListener(this);

		line_image = (ImageView) rootView.findViewById(R.id.red_line_image);

		TranslateAnimation mAnimation = new TranslateAnimation(
				TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE,
				0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
		mAnimation.setDuration(1000);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.REVERSE);
		mAnimation.setInterpolator(new LinearInterpolator());
		line_image.setAnimation(mAnimation);

		return rootView;
	}

	@Override
	public void onQRCodeRead(String text, PointF[] points) {
		// TODO Auto-generated method stub
		String from = null;
		try {
			from = getArguments().getString("from");
		} catch (Exception e) {
			// TODO: handle exception
		}
		Class<?> clazz;
		Object object = null;
		try {
			clazz=Class.forName("info.androidhive.slidingmenu."+from);
			object = clazz.newInstance();
			System.out.println(object.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.lang.InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		Bundle args = new Bundle();
		args.putString("barkod", text);
		((Fragment) object).setArguments(args);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.replace(R.id.frame_container, (Fragment) object, "Anasayfa");
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	@Override
	public void cameraNotFound() {
		// TODO Auto-generated method stub

	}

	@Override
	public void QRCodeNotFoundOnCamImage() {
		// TODO Auto-generated method stub

	}
}
