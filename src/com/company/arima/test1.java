package com.company.arima;

import java.io.*;
import java.util.*;

public class test1 {

	public static void main(String args[])
	{
		HashMap<String,TreeMap<Integer,Integer>> data = new HashMap<>();
		File file = new File("D:\\Users\\tianchi_music\\P2\\DateArtist.csv");
		try {
			FileWriter fw;
			fw = new FileWriter("D:\\Users\\tianchi_music\\p2\\mars_tianchi_artist_plays_predict.csv");
			BufferedWriter bw = new BufferedWriter(fw);
			FileInputStream inputStream = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String line = br.readLine();
			line = br.readLine();
			while (line !=null){
				String[] temp = line.split(",");
				if (data.containsKey(temp[12])){
					TreeMap<Integer,Integer> treeMap =data.get(temp[12]);
					treeMap.put(Integer.valueOf(temp[2]), Integer.valueOf(temp[0]));
					data.replace(temp[0], treeMap);
				}else {
					TreeMap<Integer,Integer> treeMap = new TreeMap<>();
					treeMap.put(Integer.valueOf(temp[2]), Integer.valueOf(temp[0]));
					data.put(temp[12],treeMap);
				}

				line = br.readLine();
			}
			Iterator iterator = data.entrySet().iterator();
			while (iterator.hasNext()){
				HashMap.Entry entry = (HashMap.Entry) iterator.next();
				String name = (String) entry.getKey();
				TreeMap<Integer,Integer> treeMap = (TreeMap<Integer, Integer>) entry.getValue();
				Iterator iter = treeMap.entrySet().iterator();
				ArrayList<Double> arraylist=new ArrayList<Double>();
				while (iter.hasNext()){
					Map.Entry entry1 = (Map.Entry) iter.next();
					int i = (int) entry1.getValue();
					arraylist.add((double) i);
				}

				for (int i = 0; i <60; i++) {
					int date;
					date = 20150801+i;
					if (i<30){
						date = 20150901+i;
					}else {
						date = 20151001+i-30;
					}
					StringBuilder sb = new StringBuilder();
					sb.append(name).append(",");
//					System.out.println(name);

					double[] dataArray=new double[arraylist.size()];
					for (int j = 0; j < arraylist.size() ; j++) {
						dataArray[j] = arraylist.get(j);
					}
					ARIMA arima=new ARIMA(dataArray);
					int []model=arima.getARIMAmodel();
					int result = arima.aftDeal(arima.predictValue(model[0],model[1]));
					if (result<0)
						result = 0;
					sb.append(result).append(",");
//					arraylist.add((double)result);
					System.out.println("Best model is [p,q]="+"["+model[0]+" "+model[1]+"]");
					System.out.println("Predict value="+arima.aftDeal(arima.predictValue(model[0],model[1])));

					sb.append(date);
					System.out.println(sb);
					bw.write(sb.toString());
					bw.newLine();
					bw.flush();
				}
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
