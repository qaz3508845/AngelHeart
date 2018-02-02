package com.example.ShowVoiceListView;

/**
 * Created by qaz35 on 2018/1/24/024.
 */

public class voiceItem {

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    int v_id;
    int v_number;
    String v_translationed;
    String v_translation;
    String v_datetime;


    public int getV_number() {
        return v_number;
    }

    public void setV_number(int v_number) {
        this.v_number = v_number;
    }
    public voiceItem() {
    }

    public String getV_translationed() {
        return v_translationed;
    }

    public void setV_translationed(String v_translationed) {
        this.v_translationed = v_translationed;
    }

    public String getV_translation() {
        return v_translation;
    }

    public void setV_translation(String v_translation) {
        this.v_translation = v_translation;
    }

    public String getV_datetime() {
        return v_datetime;
    }

    public void setV_datetime(String v_datetime) {
        this.v_datetime = v_datetime;
    }

    public voiceItem(String v_translationed, String v_translation, String v_datetime) {

        this.v_translationed = v_translationed;
        this.v_translation = v_translation;
        this.v_datetime = v_datetime;
    }
}
