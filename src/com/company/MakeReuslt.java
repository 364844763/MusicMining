package com.company;

import java.io.*;
import java.util.HashMap;

/**
 * Created by jiajie on 2016/5/23.
 */
public class MakeReuslt {
    public void make() {
        HashMap<DateAndID,Integer> result = new HashMap<>();
        File file = new File("D:\\Users\\tianchi_music\\DateArtistTestResult.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            br.readLine();
            String line = br.readLine();
            while (line != null){
                String[] temp = line.split(",");
                double d = Double.parseDouble(temp[0]);
                if (d<=0)
                    d =5;
                int  date = (int) Double.parseDouble(temp[2]);
                DateAndID id = new DateAndID((int) Double.parseDouble(temp[1])+"",date+"");
                result.put(id, (int) d);
                line = br.readLine();
            }
            br.close();
            isr.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file = new File("D:\\Users\\tianchi_music\\p2\\resultMaker.csv");
        try {
            FileWriter fileWriter = new FileWriter("D:\\Users\\tianchi_music\\mars_tianchi_artist_plays_predict2.csv");
            BufferedWriter bw = new BufferedWriter(fileWriter);
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null){
                String[] temp = line.split(",");
                DateAndID id = new DateAndID(temp[1],temp[2]);
                if (!result.containsKey(id)){
                    line = br.readLine();
                    continue;
                }
                int i = result.get(id);
                StringBuilder sb = new StringBuilder();
                sb.append(temp[11]).append(",")
                        .append(i).append(",").append(id.date);
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();
                line = br.readLine();
                System.out.println(sb.toString());
            }
            bw.close();
            br.close();
            isr.close();
            fileWriter.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class DateAndID{
        private String date;
        private String id;

        public DateAndID(String id, String date) {
            this.id = id;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DateAndID dateAndID = (DateAndID) o;

            if (date != null ? !date.equals(dateAndID.date) : dateAndID.date != null) return false;
            return !(id != null ? !id.equals(dateAndID.id) : dateAndID.id != null);

        }

        @Override
        public int hashCode() {
            int result = date != null ? date.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }
}
