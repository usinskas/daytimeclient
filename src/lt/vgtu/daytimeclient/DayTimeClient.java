package lt.vgtu.daytimeclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

import android.util.Log;

public class DayTimeClient {

	public static String GetTimeTCP(String ServerioAdresas,
			int ServerioPrievadas) {
		
		//Nustatomi kintamieji
		InetAddress serverAddr;
		Socket socket;
		InputStreamReader stream;
		BufferedReader serverInput;
		String time;

		try {
			// Nustatoma jungtis
			serverAddr = InetAddress.getByName(ServerioAdresas);
			socket = new Socket(serverAddr, ServerioPrievadas);
			stream = new InputStreamReader(socket.getInputStream());
			serverInput = new BufferedReader(stream);

			// Gaunamas laikas
			time = "";
			while (time.length() == 0) {
				time = serverInput.readLine();
			}

			// Nutraukiama jungtis
			socket.close();

			// Paliekami tik reikalingi duomenys
			time = time.substring(6, 20);

			// Gràþinamas laikas kaip funkcijos vertë
			return (time);

		} // Klaidos atveju
		catch (Exception e) {
            Log.d(DayTimeClient.class.getCanonicalName(), "Rysio klaida: " + e.getMessage());
            e.printStackTrace();
		}
        return "Rysio klaida";
	}

}
