package com.company.bean;

/**
 * ����ʵ��
 * Created by jiajie on 2016/5/19.
 */
public class Song {
    private int id;// ��Ϊ��Ӹ���id
    private String name;//��������Ψһ
    private int piancha; // �ݷ�����ʱ��
    private String artist;
    private int song_init_plays;
    private int publish_time;
    private int date;//����
    private int language;
    private int gender;
    private int playNum;// ������
    private  int downloadNum;
    private  int collectNum;
    private int artistId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPiancha() {
        return piancha;
    }

    public void setPiancha(int piancha) {
        this.piancha = piancha;
    }

    public String getArtist() {
        return artist;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSong_init_plays() {
        return song_init_plays;
    }

    public void setSong_init_plays(int song_init_plays) {
        this.song_init_plays = song_init_plays;
    }

    public int getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(int publish_time) {
        this.publish_time = publish_time;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public int getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(int downloadNum) {
        this.downloadNum = downloadNum;
    }

    public int getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(int collectNum) {
        this.collectNum = collectNum;
    }
    public void addNun(int action){
        if (action ==1){
            playNum++;
        }else if (action ==2){
            downloadNum++;
        }else if (action ==3){
            collectNum++;
        }
    }

}
