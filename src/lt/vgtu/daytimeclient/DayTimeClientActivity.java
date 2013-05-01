
package lt.vgtu.daytimeclient;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DayTimeClientActivity extends Activity {

    // Nustatomi kintamieji
    Button mGetTimeB;
    TextView mTimeValueTV;

    // Lango vaizdinimas
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Rodomas langas res/layout/activity_time.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_time_client);

        // Apibreziamas mygtukas activity_time_get_time_b
        mGetTimeB = (Button)findViewById(R.id.activity_time_get_time_b);

        // Rodomas serverio prievado numeris lango tekste activity_time_port_number_tv
        ((TextView)findViewById(R.id.activity_time_port_number_tv)).setText(String.format("%d", ServerioParametrai.SERVER_PORT_NUMBER));
        // Rodomas serverio adresas lango tekste activity_time_url_tv
        ((TextView)findViewById(R.id.activity_time_url_tv)).setText(ServerioParametrai.SERVER_URL);


        // Paspaudus mygtuka
        mGetTimeB.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // startuojamas fonine gija, kurioje atliekama tinklo komunikacija
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {
                        try {
                            return DayTimeClient.GetTimeTCP(ServerioParametrai.SERVER_URL, ServerioParametrai.SERVER_PORT_NUMBER);
                        } catch (NetworkErrorException e) {
                            return getResources().getString(R.string.activity_time_connection_error);
                        }
                    }

                    @Override
                    protected void onPostExecute(String timevalue) {
                        // Rodomas laikas lango tekste activity_time_value_tv
                        mTimeValueTV = (TextView)findViewById(R.id.activity_time_value_tv);
                        mTimeValueTV.setText(timevalue);
                    }
                }.execute();
            }
        });
    }

    // Meniu vaizdinimas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Rodomas meniu res/menu/activity_time.xml
        getMenuInflater().inflate(R.menu.day_time_client, menu);
        return true;
    }

}
