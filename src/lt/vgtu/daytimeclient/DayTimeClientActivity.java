
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
    Button mygtukas;
    TextView tekstas;

    // Lango vaizdinimas
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Rodomas langas res/layout/activity_main.xml
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_time_client);

        // Apibreziamas mygtukas button1
        mygtukas = (Button)findViewById(R.id.button1);

        // Rodomas serverio prievado numeris lango tekste textView5
        tekstas = (TextView)findViewById(R.id.textView5);
        tekstas.setText(String.format("%d", ServerioParametrai.Prievadas));
        // Rodomas serverio adresas lango tekste textView6
        tekstas = (TextView)findViewById(R.id.textView6);
        tekstas.setText(ServerioParametrai.Adresas);

        // Paspaudus mygtuka
        mygtukas.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // startuojamas fonine gija, kurioje atliekama tinklo komunikacija
                new AsyncTask<Void, Void, String>() {

                    @Override
                    protected String doInBackground(Void... params) {
                        try {
                            return DayTimeClient.GetTimeTCP(ServerioParametrai.Adresas, ServerioParametrai.Prievadas);
                        } catch (NetworkErrorException e) {
                            return getResources().getString(R.string.activity_day_time_client_connection_error);
                        }
                    }

                    @Override
                    protected void onPostExecute(String timevalue) {
                        // Rodomas laikas lango tekste textView7
                        tekstas = (TextView)findViewById(R.id.textView7);
                        tekstas.setText(timevalue);
                    }
                }.execute();
            }
        });
    }

    // Meniu vaizdinimas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Rodomas meniu res/menu/main.xml
        getMenuInflater().inflate(R.menu.day_time_client, menu);
        return true;
    }

}
