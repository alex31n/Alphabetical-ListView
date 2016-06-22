package com.alex.alphabeticallistview;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	TextView tvIndexChar;
	ListView listView;

	Map<String, Integer> mapIndex;

	Runnable mRunnable;
	Handler mHandler = new Handler();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<String> list = getCountry();

		tvIndexChar = (TextView) findViewById(R.id.tv_index_char);
		listView = (ListView) findViewById(R.id.listview);


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);

		getIndexList(list);

		displayIndex();


		// create a handler for hiding IndexChar view  after 2 second
		mRunnable = new Runnable() {

			@Override
			public void run() {
				if (tvIndexChar.getVisibility() == View.VISIBLE) {
					try {
						Animation outAnimation = AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out);
						tvIndexChar.setAnimation(outAnimation);
						tvIndexChar.setVisibility(View.GONE);
						mHandler.removeCallbacks(mRunnable);
					} catch (Exception e) {

					}
				}
			}
		};


	}


	private void getIndexList(List<String> list) {
		mapIndex = new LinkedHashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			String index = list.get(i).substring(0, 1);

			if (mapIndex.get(index) == null)
				mapIndex.put(index, i);
		}
	}


	private void displayIndex() {
		LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

		TextView textView;
		List<String> indexList = new ArrayList<String>(mapIndex.keySet());
		for (String index : indexList) {
			textView = (TextView) getLayoutInflater().inflate(
				  R.layout.row_side_index_item, null);
			textView.setText(index);
			textView.setOnClickListener(this);
			indexLayout.addView(textView);
		}
	}


	private List<String> getCountry() {
		List<String> countries = new ArrayList<String>();
		countries.add("254Chennai");
		countries.add("Chennai");
		countries.add("Coimbatore");
		countries.add("Thiruvarur");
		countries.add("Thirupur");
		countries.add("Ariyalur");
		countries.add("Virudhunagar");
		countries.add("Villupuram");
		countries.add("Vellore");
		countries.add("Thiruvannamalai");
		countries.add("Thiruvallur");
		countries.add("Thirunelveli");
		countries.add("Trichy");
		countries.add("Thoothukudi");
		countries.add("Nilgiris");
		countries.add("Theni");
		countries.add("Thanjavur");
		countries.add("Sivagangai");
		countries.add("Salem");
		countries.add("Ramanathapuram");
		countries.add("Pudukottai");
		countries.add("Perambalur");
		countries.add("Namakkal");
		countries.add("Nagapattinam");
		countries.add("Madurai");
		countries.add("Krishnagiri");
		countries.add("Karur");
		countries.add("Kanyakumari");
		countries.add("Kanchipuram");
		countries.add("Erode");
		countries.add("Dindigul");
		countries.add("Dharmapuri");
		countries.add("Cuddalore");
		Collections.sort(countries);
		return countries;
	}

	@Override
	public void onClick(View view) {
		TextView selectedIndex = (TextView) view;
		String text = selectedIndex.getText().toString();
		tvIndexChar.setText(text);
		listView.setSelection(mapIndex.get(text));
		//Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		showIndexChar();
	}


	void showIndexChar() {
		if (tvIndexChar.getVisibility() == View.GONE) {
			Animation inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
			tvIndexChar.setAnimation(inAnimation);
			tvIndexChar.setVisibility(View.VISIBLE);
		}
		mHandler.postDelayed(mRunnable, 1 * 1000);

	}
}
