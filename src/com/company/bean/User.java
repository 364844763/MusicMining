package com.company.bean;

import java.util.HashMap;
import java.util.HashSet;

/**
 * �û�ʵ��
 * Created by jiajie on 2016/5/17.
 */
public class User {
    private String id;//�û�id
    private int onlineDays; //��Ծ����
    private HashMap<String,Integer> favorArtists;//ϲ��������
    private HashMap<String,Integer> favorSongs;//ϲ���ĸ�
    private HashSet<String> days;

    public HashSet<String> getDays() {
        return days;
    }
    public void addDays(String day){
        if (days==null)
            days = new HashSet<>();
        days.add(day);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOnlineDays() {
        onlineDays = days.size();
        return onlineDays;
    }

    public void setOnlineDays(int onlineDays) {
        this.onlineDays = onlineDays;
    }

    public HashMap<String, Integer> getFavorArtists() {
        return favorArtists;
    }

    public void setFavorArtists(HashMap<String, Integer> favorArtists) {
        this.favorArtists = favorArtists;
    }

    public HashMap<String, Integer> getFavorSongs() {
        return favorSongs;
    }

    public void setFavorSongs(HashMap<String, Integer> favorSongs) {
        this.favorSongs = favorSongs;
    }
    public void addFavorSongs(String favorSong) {
        if (favorSongs==null)
            favorSongs = new HashMap<>();
      if (favorSongs.containsKey(favorSong)){
          int i = favorSongs.get(favorSong);
          i++;
          favorSongs.replace(favorSong,i);
      }else {
          favorSongs.put(favorSong,1);
      }
    }
    public void addOnlineDays(){
        onlineDays++;
    }
    public void addFavorArtists(String artists) {
        if (favorArtists==null)
            favorArtists = new HashMap<>();
        if (favorArtists.containsKey(artists)){
            int i = favorArtists.get(artists);
            i++;
            favorArtists.replace(artists,i);
        }else {
            favorArtists.put(artists,1);
        }
    }
}
