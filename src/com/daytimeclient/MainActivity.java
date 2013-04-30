package com.daytimeclient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Nustatomi kintamieji
	Button mygtukas;
	TextView tekstas;

	// Lango vaizdinimas
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// Rodomas langas res/layout/activity_main.xml
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Apibrëþiamas mygtukas button1
		mygtukas = (Button) findViewById(R.id.button1);

		// Rodomas serverio prievado numeris lango tekste textView5
		tekstas = (TextView) findViewById(R.id.textView5);
		tekstas.setText(String.format("%d", ServerioParametrai.Prievadas));
		// Rodomas serverio adresas lango tekste textView6
		tekstas = (TextView) findViewById(R.id.textView6);
		tekstas.setText(ServerioParametrai.Adresas);

		// Paspaudus mygtukà
		mygtukas.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// Nustatomas kintamasis
				String timevalue;

				// Kreipiamasi á funkcijà GetTimeTCP
				timevalue = DayTimeClient.GetTimeTCP(
						ServerioParametrai.Adresas,
						ServerioParametrai.Prievadas);

				// Rodomas laikas lango tekste textView7
				tekstas = (TextView) findViewById(R.id.textView7);
				tekstas.setText(timevalue);
			}
		});

	}

	// Meniu vaizdinimas
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Rodomas meniu res/menu/main.xml
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
