package com.company.bean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jiajie on 2016/5/24.
 */
public class NewSong {
    private Date date;
    private String id;
    private int song_init_plays;
    private int month;
    private int day;

    public NewSong() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        month =cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSong_init_plays() {
        return song_init_plays;
    }

    public void setSong_init_plays(int song_init_plays) {
        this.song_init_plays = song_init_plays;
    }

    public int playsNum(String temp){
        SimpleDateFormat dd=new SimpleDateFormat("yyyyMMdd");
        try {
            Date now = dd.parse(temp);
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            int result =(2 + cal.get(Calendar.MONTH)-month)*31 +cal.get(Calendar.DAY_OF_MONTH)  - day;
            if (result<3){
                if (result<=0)
                    return song_init_plays;
                return song_init_plays/(result+1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
