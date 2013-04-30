package com.daytimeclient;

import java.io.*;
import java.net.*;

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
			return ("Ryðio klaida");
		}
	}

}
