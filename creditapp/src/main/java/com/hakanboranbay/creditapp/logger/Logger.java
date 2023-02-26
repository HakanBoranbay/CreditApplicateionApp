package com.hakanboranbay.creditapp.logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
	
	public void logToFile(String message) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("./creditapplogs.txt", true));
			bw.append(message + "\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
