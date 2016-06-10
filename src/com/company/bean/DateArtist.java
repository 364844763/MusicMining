package com.company.bean;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 每日艺术家统计
 * Created by jiajie on 2016/5/17.
 */
public class DateArtist extends Artist{
    private int date;
//    private String artist;
    private int num;
    private int songNum;
    private HashMap<String,Integer> songs ;
    private HashSet<String> newSongs = new HashSet<>();

    public int getNewSongNum() {
        return newSongNum;
    }

    public void setNewSongNum(int newSongNum,String id) {
        if (newSongs.contains(id))
            return;
        newSongs.add(id);
        this.newSongNum += newSongNum;
    }

    private int newSongNum;
    public DateArtist() {
        songs = new HashMap<>();

    }
    public DateArtist(Artist artist) {
        songs = new HashMap<>();
        this.id = artist.getId();
        this.artist = artist.getArtist();
        this.gender = artist.gender;
        this.lauange = artist.lauange;
        this.newSong = artist.newSong;
        this.songNum = artist.getSongs().size();
    }
    public HashMap<String, Integer> getSongs() {
        return songs;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public void setSongs(HashMap<String, Integer> songs) {
        this.songs = songs;
    }
    public void addSong(String song){
        if (songs.containsKey(song)){
            songs.replace(song,songs.get(song)+1);
        }else {
            songs.put(song,1);
        }
        num++;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
