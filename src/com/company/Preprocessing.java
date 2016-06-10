package com.company;

import com.company.bean.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 * 数据预处理工具类
 * Created by jiajie on 2016/5/17.
 */
public class Preprocessing {
    private static Preprocessing preprocessing;

    private Preprocessing() {

    }

    ;

    public static void toUser() {
        HashMap<String, User> users = new HashMap<>();
        HashMap<String, String> song2Artist = new HashMap<>();
        File file = new File("D:\\Users\\tianchi_music\\mars_tianchi_songs.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                song2Artist.put(temp[0], temp[1]);
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        file = new File("D:\\Users\\tianchi_music\\mars_tianchi_user_actions.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                String artist = song2Artist.get(temp[1]);
                String useID = temp[0];
                String song = temp[1];
                if (users.containsKey(useID)) {
                    User user = users.get(useID);
                    user.addFavorArtists(artist);
                    user.addDays(temp[4]);
                    user.addFavorSongs(song);
                    users.replace(useID, user);
                } else {
                    User user = new User();
                    user.setId(useID);
                    user.addFavorArtists(artist);
                    user.addDays(temp[4]);
                    user.addFavorSongs(song);
                    users.put(useID, user);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\Users\\tianchi_music\\user.csv");
            Iterator iter = users.entrySet().iterator();
            while (iter.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) iter.next();
                User user = (User) entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(user.getId()).append(",").
                        append(user.getOnlineDays()).append(",");

                HashMap<String, Integer> songs = user.getFavorSongs();
                Iterator iterator = songs.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry entry2 = (HashMap.Entry) iterator.next();
                    sb.append(entry2.getKey()).append(",")
                            .append(entry2.getValue()).append(",");
                }
                HashMap<String, Integer> artists = user.getFavorArtists();
                Iterator iterator2 = artists.entrySet().iterator();
                while (iterator2.hasNext()) {
                    HashMap.Entry entry2 = (HashMap.Entry) iterator2.next();
                    sb.append(entry2.getKey()).append(",")
                            .append(entry2.getValue()).append(",");
                }
                sb.append("\n");
                fileWriter.write(sb.toString());
                fileWriter.flush();

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toDateArtist() {
        HashMap<String, String> song2Artist = new HashMap<>();
        HashMap<DateAndArtist, DateArtist> result = new HashMap<>();
        HashMap<String, Integer> artists = new HashMap<>();
        File file = new File("D:\\Users\\tianchi_music\\mars_tianchi_songs.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            int artistId = 0;
            while (line != null) {
                String[] temp = line.split(",");
                song2Artist.put(temp[0], temp[1]);
                if (!artists.containsKey(temp[1])) {
                    artists.put(temp[1], artistId);
                    artistId++;
//                    System.out.print(artistId);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        file = new File("D:\\Users\\tianchi_music\\mars_tianchi_user_actions.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                String artist = song2Artist.get(temp[1]);
                DateAndArtist dateAndArtist = new DateAndArtist(temp[4], artist);
                if (result.containsKey(dateAndArtist)) {
                    DateArtist dateArtist = result.get(dateAndArtist);
                    dateArtist.addSong(temp[1]);
                    result.replace(dateAndArtist, dateArtist);
                } else {
                    DateArtist dateArtist = new DateArtist();
                    dateArtist.setArtist(dateAndArtist.artist);
                    dateArtist.setDate(Integer.parseInt(dateAndArtist.date));
                    dateArtist.addSong(temp[1]);
                    result.put(dateAndArtist, dateArtist);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\Users\\tianchi_music\\DateArtist.csv");
            Iterator iter = result.entrySet().iterator();
            while (iter.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) iter.next();
                DateArtist dateArtist = (DateArtist) entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(dateArtist.getDate()).append(",").
                        append(dateArtist.getArtist()).append(",")
                        .append(dateArtist.getNum()).append(",");
                HashMap<String, Integer> songs = dateArtist.getSongs();
                Iterator iterator = songs.entrySet().iterator();
                while (iterator.hasNext()) {
                    HashMap.Entry entry2 = (HashMap.Entry) iterator.next();
                    sb.append(entry2.getKey()).append(",")
                            .append(entry2.getValue()).append(",");
                }
                sb.append("\n");
                fileWriter.write(sb.toString());
                fileWriter.flush();

            }
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void toSongs() {
        HashMap<String, SongInformation> songInformationHashSet = new HashMap<>();
        HashMap<SongAndDate, Song> songs = new HashMap<>();
        HashMap<String, Integer> artists = new HashMap<>();
        File file = new File("D:\\Users\\tianchi_music\\mars_tianchi_songs.csv");
        try {
            FileInputStream is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            int artistId = 0;
            int id = 0;
            while (line != null) {

                SongInformation songInformation = new SongInformation();
                String[] temp = line.split(",");
                if (!artists.containsKey(temp[1])) {
                    artists.put(temp[1], artistId);
                    artistId++;
//                    System.out.print(artistId);
                }

                songInformation.id = id;
                songInformation.name = temp[0];
                songInformation.artist = temp[1];
                songInformation.publish_time = Integer.parseInt(temp[2]);
                songInformation.song_init_plays = Integer.parseInt(temp[3]);
                songInformation.language = Integer.parseInt(temp[4]);
                songInformation.gender = Integer.parseInt(temp[5]);
                songInformationHashSet.put(songInformation.name, songInformation);
                line = br.readLine();
                id++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        file = new File("D:\\Users\\tianchi_music\\mars_tianchi_user_actions.csv");
        FileInputStream is = null;
        try {
            is = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (line != null) {
                String[] temp = line.split(",");
                SongAndDate songAndDate = new SongAndDate(temp[1], temp[4]);
                if (songs.containsKey(songAndDate)) {
                    Song song = songs.get(songAndDate);
                    song.addNun(Integer.parseInt(temp[3]));
                    songs.replace(songAndDate, song);
                } else {
                    Song song = new Song();
                    SongInformation songInformation = songInformationHashSet.get(temp[1]);
                    song.setName(temp[1]);
                    song.setArtistId(artists.get(songInformation.artist));
                    song.setArtist(songInformation.artist);
                    song.setDate(Integer.parseInt(temp[4]));
                    song.setGender(songInformation.gender);
                    song.setId(songInformation.id);
                    song.setLanguage(songInformation.language);
                    song.setPublish_time(songInformation.publish_time);
                    song.setSong_init_plays(songInformation.song_init_plays);
                    int date = song.getDate();
                    int getPublish_time = song.getPublish_time();
                    int day = date - getPublish_time;
//                    System.out.println(day);
                    day = (day / 10000) * 365 + (((day % 10000) % 1200) / 100) * 30 + (day % 100);
//                    System.out.println(day);
                    song.setPiancha(day);
                    song.addNun(Integer.parseInt(temp[3]));
                    songs.put(songAndDate, song);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\Users\\tianchi_music\\songs4.csv");
            Iterator iter = songs.entrySet().iterator();
            while (iter.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) iter.next();
                Song song = (Song) entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(song.getPlayNum()).append(",");
                sb.append(song.getId()).append(",");
                int day = song.getDate() - 20150301;
                day = (day / 100) * 30 + day % 100;
//                if (day > 5)
//                    continue;
                sb.append(day).append(",");
                sb.append(song.getArtistId()).append(",");

//                sb.append(song.getPlayNum()).append(",");
//                sb.append(song.getDownloadNum()).append(",");
//                sb.append(song.getCollectNum()).append(",");
                sb.append(song.getGender()).append(",");
                sb.append(song.getLanguage()).append(",");

                sb.append(song.getPublish_time() / 10000).append(",");
//                sb.append(song.getPiancha()).append(",");
                sb.append(song.getSong_init_plays()).append(",");
//                sb.append(song.getName()).append(",");
//                sb.append(song.getArtist()).append(",");
                sb.append("\n");
                fileWriter.write(sb.toString());
                fileWriter.flush();
                System.out.print(sb);
            }

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void toFeature() {
        HashMap<String, String> song2Artist = new HashMap<>();
        HashMap<DateAndArtist, DateArtist> result = new HashMap<>();
        HashMap<String, Artist> artists = new HashMap<>();
//        HashMap<String,NewSong> newSongs = new HashMap<>();
        File file = new File("D:\\Users\\tianchi_music\\p2\\mars_tianchi_songs.csv");
        try {
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            int artistId = 0;
            while (line != null) {
                String[] temp = line.split(",");
                if (Integer.parseInt(temp[2]) / 10000 == 2015) {
                    SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
                    Date date = dd.parse(temp[2]);
                    NewSong song = new NewSong();
                    song.setDate(date);
                    song.setId(temp[0]);
                    song.setSong_init_plays(Integer.parseInt(temp[3]));
//                    newSongs.put(temp[0],song);
                }
                song2Artist.put(temp[0], temp[1]);
                if (!artists.containsKey(temp[1])) {
                    Artist artist = new Artist();
                    artist.setId(artistId);
                    artist.setArtist(temp[1]);
//                    Date date = new Date(Integer.parseInt(temp[2]));
                    if (Integer.parseInt(temp[2]) / 10000 == 2015)
                        artist.addNewSong();
                    artist.addSong(temp[0]);
                    artist.setLauange(Integer.parseInt(temp[4]));
                    artist.setGender(Integer.parseInt(temp[5]));
                    artists.put(temp[1], artist);
                    artistId++;

//                    System.out.print(artistId);
                } else {
                    Artist artist = artists.get(temp[1]);
                    SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
                    Date date = dd.parse(temp[2]);
                    ;
                    if (date.getYear() == 2015) {

                        artist.addNewSong();

                    }
                    artist.addSong(temp[0]);
                    artists.replace(temp[1], artist);
                }
                line = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        file = new File("D:\\Users\\tianchi_music\\p2\\mars_tianchi_user_actions.csv");
        FileInputStream is = null;
        FileWriter fileWriter = null;
        try {
            is = new FileInputStream(file);
//            fileWriter = new FileWriter("D:\\Users\\tianchi_music\\songs4.csv");
////            FileInputStream os = new FileInputStream("D:\\Users\\tianchi_music\\feature.csv");
//            BufferedWriter bw = new BufferedWriter(fileWriter);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
//            bw.write("id,"+line);
//            bw.newLine();

            while (line != null) {
                String[] temp = line.split(",");
                String artistName = song2Artist.get(temp[1]);
                DateAndArtist dateAndArtist = new DateAndArtist(temp[4], artistName);
                if (result.containsKey(dateAndArtist)) {
                    DateArtist dateArtist = result.get(dateAndArtist);
                    Artist artist = artists.get(artistName);
//                    if (newSongs.containsKey(temp[1])){
//                        NewSong newSong = newSongs.get(temp[1]);
//                        dateArtist.setNewSongNum(newSong.playsNum(temp[4]),temp[1]);
//                    }

                    artist.addTotalNum();
                    dateArtist.addSong(temp[1]);
                    artist.addUsers(temp[0]);
                    artists.replace(artistName, artist);
                    result.replace(dateAndArtist, dateArtist);
                } else {
                    Artist artist = artists.get(artistName);
//                   artist.addUsers();
                    DateArtist dateArtist = new DateArtist(artist);
//                   artist.setDate(temp[4]);
                    dateArtist.addSong(temp[1]);
//                    if (newSongs.containsKey(temp[1])){
//                        NewSong newSong = newSongs.get(temp[1]);
//                        dateArtist.setNewSongNum(newSong.playsNum(temp[4]),temp[1]);
//                    }
//                    Date date = new Date(Integer.parseInt(temp[4])/10000,Integer.parseInt(temp[4])/10000);
//                    int day = Integer.parseInt(temp[4])- 20150301;
//                    day = (day / 100) * 30 + day % 100;
                    dateArtist.setDate(Integer.parseInt(temp[4]));
                    artist.addTotalNum();
                    artist.addUsers(temp[0]);
                    artists.replace(artistName, artist);
                    result.put(dateAndArtist, dateArtist);
                }
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            fileWriter = new FileWriter("D:\\Users\\tianchi_music\\p2\\DateArtistTrain.csv");
            BufferedWriter bw = new BufferedWriter(fileWriter);
//            bw.write("播放量,id,日期,性别,语言,歌曲数量,新歌数量,日,月,年,周");
            bw.write("播放量,id,日期,性别,语言,歌曲数量,新歌数量,日,月,年,周");
//            bw.write("播放量,id,日期");
            bw.newLine();
            bw.flush();

            Iterator iter = result.entrySet().iterator();
            while (iter.hasNext()) {
                HashMap.Entry entry = (HashMap.Entry) iter.next();
                DateArtist dateArtist = (DateArtist) entry.getValue();
                StringBuilder sb = new StringBuilder();
//                if (dateArtist.getDate()<20150700||dateArtist.getDate()==20150731||dateArtist.getDate()==20150831)
//                    continue;
//                System.out.println();
//                if (dateArtist.getDate()<20150800)
//                    continue;
                int total = artists.get(dateArtist.getArtist()).getTotalNum();
                int num = dateArtist.getNum();
//                平滑曲线
//                if ((total-num)/60<num)
//                    continue;

//                sb .append(dateArtist.getArtist()).append(",")
//                        .append(dateArtist.getNum()).append(",")
//                        .append(dateArtist.getDate());
                sb.append(dateArtist.getNum()).append(",").
                        append(dateArtist.getId()).append(",")
                        .append(dateArtist.getDate());
                sb.append(",")
                        .append(artists.get(dateArtist.getArtist()).getTotalNum()/180.0);
//                        .append(dateArtist.getGender()).append(",")
//                        .append(dateArtist.getLauange()).append(",")
//                        .append(dateArtist.getSongNum());
//                                 .append(",")
//                sb.append(dateArtist.getNewSong()).append(",")
//                        .append(artists.get(dateArtist.getArtist()).getTotalNum()).append(",")
//                        .append(artists.get(dateArtist.getArtist()).getTotalNum()/180.0).append(",")
//                        .append(artists.get(dateArtist.getArtist()).getUsersnum());
                SimpleDateFormat dd = new SimpleDateFormat("yyyyMMdd");
                Date date = dd.parse((dateArtist.getDate()) + "");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                sb.append(",").append(cal.get(Calendar.DAY_OF_MONTH));
                sb.append(",").append(cal.get(Calendar.MONTH));
                sb.append(",").append(cal.get(Calendar.DAY_OF_WEEK));
////                         sb.append(",").append(dateArtist.getNewSongNum());
//                        sb.append(",").append(dateArtist.getArtist());

                System.out.println(sb.toString());
                bw.write(sb.toString());
                bw.newLine();
                bw.flush();

            }
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SongInformation {
        private int id;// 人为添加歌曲id
        private String name;//歌曲名称唯一
        private String artist;
        private int song_init_plays;
        private int publish_time;
        private int language;
        private int gender;
    }

    private static class SongAndDate {
        private String name;//歌曲名称唯一
        private String date;

        public SongAndDate(String name, String date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SongAndDate that = (SongAndDate) o;

            if (name != null ? !name.equals(that.name) : that.name != null) return false;
            return !(date != null ? !date.equals(that.date) : that.date != null);

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (date != null ? date.hashCode() : 0);
            return result;
        }
    }

    private static class DateAndArtist {

        private String date;
        private String artist;

        public DateAndArtist(String date, String artist) {
            this.date = date;
            this.artist = artist;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getArtist() {
            return artist;
        }

        public void setArtist(String artist) {
            this.artist = artist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DateAndArtist that = (DateAndArtist) o;

            return date.equals(that.date) && artist.equals(that.artist);

        }

        @Override
        public int hashCode() {
            int result = date.hashCode();
            result = 31 * result + artist.hashCode();
            return result;
        }
    }
}
