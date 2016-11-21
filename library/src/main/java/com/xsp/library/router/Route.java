package com.xsp.library.router;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public final class Route implements Parcelable {
    public String scheme;
    public String host;
    public String path;
    public Bundle bundle;

    String packageName;
    String activityName;

    private Route() {

    }

    static Route newInstance() {
        return new Route();
    }

    @Override
    public String toString() {
        return "Route{" +
                "activityName='" + activityName + '\'' +
                ", scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                ", packageName='" + packageName + '\'' +
                ", bundle='" + (null != bundle ? bundle.toString() : "") + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.scheme);
        dest.writeString(this.host);
        dest.writeString(this.path);
        dest.writeString(this.packageName);
        dest.writeString(this.activityName);
        dest.writeBundle(this.bundle);
    }

    private Route(Parcel in) {
        this.scheme = in.readString();
        this.host = in.readString();
        this.path = in.readString();
        this.packageName = in.readString();
        this.activityName = in.readString();
        this.bundle = in.readBundle(getClass().getClassLoader());
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
