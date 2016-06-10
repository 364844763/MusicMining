package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Verification {
	public static void main(String[] args) {
		double predictData[][]=new double[100][100];
		double realData[][]=new double[100][100];
		HashMap<String, Integer> artist = new HashMap<>();
		HashMap<String, Integer> date = new HashMap<>();
		toPreprocess(predictData,realData,artist,date);
		int m=artist.size();
		int n=date.size();
		double variance=0;
		double temp1=0;
		double temp2=0;
		for(int i=0;i<m;i++){
			temp1=0;
			temp2=0;
			for(int j=0;j<n;j++){
				temp1+=Math.pow((predictData[i][j]-realData[i][j])/realData[i][j], 2);
				temp2+=realData[i][j];
			}
			variance+=(1-Math.sqrt(temp1/n))*Math.sqrt(temp2);
		}
		System.out.println("result is:"+variance);
	}

	public static void toPreprocess(double[][] predictData, double[][] realData, HashMap<String, Integer> artist, HashMap<String, Integer> date) {
		int artistIndex=0;
		int dateIndex=0;
		//‘§≤‚
		File file = new File(
				"D:\\Users\\tianchi_music\\mars_tianchi_artist_plays_predict2.csv");
		try {
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			while (line != null) {
				String[] temp = line.split(",");
				if(!artist.containsKey(temp[0])){
					artist.put(temp[0], artistIndex++);
				}
				if(!date.containsKey(temp[2])){
					date.put(temp[2], dateIndex++);
				}
				predictData[artist.get(temp[0])][date.get(temp[2])]=Integer.parseInt(temp[1]);
				line = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		//’Ê µ
		file = new File(
				"D:\\Users\\tianchi_music\\p2\\real.csv");
		try {
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			while (line != null) {
				String[] temp = line.split(",");
				if(!artist.containsKey(temp[0])){
					artist.put(temp[0], artistIndex++);
				}
				if(!date.containsKey(temp[2])){
					date.put(temp[2], dateIndex++);
				}
				realData[artist.get(temp[0])][date.get(temp[2])]=Integer.parseInt(temp[1]);
				line = br.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
