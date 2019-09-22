package com.shwetank.easygeo;

import android.app.Activity;

import com.google.android.gms.location.Geofence;

import java.util.List;

public interface EasyGeoListener {

    Activity getActivity();

    void onError();

    List<Geofence> getGeoFenceList();

    Class<?> getBroadcast();
}
