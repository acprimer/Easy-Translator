package com.translator.acprimer.easytranslator;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yaodh on 2016/6/2.
 */
public class TransResult {
    private String from;
    private String to;

    @SerializedName("trans_result")
    private List<TransPair> data;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<TransPair> getData() {
        return data;
    }

    public void setData(List<TransPair> data) {
        this.data = data;
    }
}
