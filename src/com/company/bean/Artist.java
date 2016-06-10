package com.company.bean;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by jiajie on 2016/5/23.
 */
public class Artist {
    protected int id;
    protected String artist;
    protected int gender;
    protected int lauange;
    protected int newSong;
    protected int totalNum;
    private HashSet<String> users ;
    private HashMap<String,Integer> songs ;
    public Artist() {
        users = new HashSet<>();
        songs = new HashMap<>();
    }
    public HashMap<String, Integer> getSongs() {
        return songs;
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
    }

    public int getTotalNum() {
        return totalNum;
    }
    public void addUsers(String user){
        users.add(user);
    }
    public int getUsersnum(){
        return users.size();
    }
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }
    public void addTotalNum(){
            totalNum++;
        }

    public int getNewSong() {
        return newSong;
    }

    public void setNewSong(int newSong) {
        this.newSong = newSong;
    }
    public void  addNewSong(){
        newSong++;
    }
    public int getLauange() {
        return lauange;
    }

    public void setLauange(int lauange) {
        this.lauange = lauange;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
