package com.github.pdfviewer;

import android.os.Parcel;
import android.os.Parcelable;

public class PDFConfig implements Parcelable {

    public static final String EXTRA_CONFIG = "PDFConfig";

    private String filepath;
    private int swipeorientation;

    public PDFConfig() {

    }

    protected PDFConfig(Parcel in) {
        this.filepath = in.readString();
        this.swipeorientation = in.readInt();
    }


    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getSwipeorientation() {
        return swipeorientation;
    }

    public void setSwipeorientation(int swipeorientation) {
        this.swipeorientation = swipeorientation;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(filepath);
        dest.writeInt(swipeorientation);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PDFConfig> CREATOR = new Creator<PDFConfig>() {
        @Override
        public PDFConfig createFromParcel(Parcel in) {
            return new PDFConfig(in);
        }

        @Override
        public PDFConfig[] newArray(int size) {
            return new PDFConfig[size];
        }
    };
}
