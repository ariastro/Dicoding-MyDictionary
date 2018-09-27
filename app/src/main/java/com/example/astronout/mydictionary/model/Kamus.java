package com.example.astronout.mydictionary.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Kamus implements Parcelable {

    private int id;
    private String kata;
    private String translate;

    public Kamus(int id, String kata, String translate) {
        this.id = id;
        this.kata = kata;
        this.translate = translate;
    }

    public Kamus(){

    }

    public Kamus(String kata, String translate){
        this.kata = kata;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKata() {
        return kata;
    }

    public void setKata(String kata) {
        this.kata = kata;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kata);
        dest.writeString(this.translate);
    }

    protected Kamus(Parcel in) {
        this.id = in.readInt();
        this.kata = in.readString();
        this.translate = in.readString();
    }

    public static final Parcelable.Creator<Kamus> CREATOR = new Parcelable.Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel source) {
            return new Kamus(source);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };
}
