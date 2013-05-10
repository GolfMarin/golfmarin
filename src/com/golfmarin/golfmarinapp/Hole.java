package com.golfmarin.golfmarinapp;

import com.google.android.gms.maps.model.LatLng;
import android.os.Parcel;
import android.os.Parcelable;

import android.util.Log;

public class Hole implements Parcelable {
	
	String holeNum = "None";
	
	LatLng front = null;
	LatLng middle = null;
	LatLng back = null;
	
	Hole(String hole){
		holeNum = hole;
	}
	
    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
    	out.writeString(holeNum);
        out.writeParcelable(front, 0);
        out.writeParcelable(middle, 0);
        out.writeParcelable(back, 0);
    }

    public static final Parcelable.Creator<Hole> CREATOR
            = new Parcelable.Creator<Hole>() {
        public Hole createFromParcel(Parcel in) {
            return new Hole(in);
        }

        public Hole[] newArray(int size) {
            return new Hole[size];
        }
    };
    
    private Hole(Parcel in) {
    	holeNum = in.readString();
        front = in.readParcelable(LatLng.class.getClassLoader());
        middle = in.readParcelable(LatLng.class.getClassLoader());
        back = in.readParcelable(LatLng.class.getClassLoader());
//        Log.v("myApp", "Hole " + holeNum + " front: " + front);
    }
}

