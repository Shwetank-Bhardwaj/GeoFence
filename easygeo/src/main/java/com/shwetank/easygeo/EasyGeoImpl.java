package com.shwetank.easygeo;

import android.app.PendingIntent;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class EasyGeoImpl implements EasyGeoManager {

    private static EasyGeoManager mEasyGeoManager;
    private EasyGeoListener mEasyGeoListener;
    private GeofencingClient mGeofencingClient;
    private PendingIntent geofencePendingIntent;

    private EasyGeoImpl() {

    }

    public static EasyGeoManager getInstance() {
        if (mEasyGeoManager == null) {
            mEasyGeoManager = new EasyGeoImpl();
        }
        return mEasyGeoManager;
    }

    @Override
    public void addGeoFence(int initialTrigger) {
        mGeofencingClient.addGeofences(getInitialTriggerGeofencingRequest(initialTrigger), getGeofencePendingIntent())
                .addOnSuccessListener(mEasyGeoListener.getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(mEasyGeoListener.getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mEasyGeoListener.onError();
                    }
                });
    }

    @Override
    public void removeGeoFence() {
        mGeofencingClient.removeGeofences(getGeofencePendingIntent())
                .addOnSuccessListener(mEasyGeoListener.getActivity(), new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Geofences removed
                        // ...
                    }
                })
                .addOnFailureListener(mEasyGeoListener.getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        mEasyGeoListener.onError();
                    }
                });
    }

    @Override
    public void addEasyGeoListener(EasyGeoListener easyGeoListener) {
        if (easyGeoListener == null) {
            return;
//            return error code;
        }
        mEasyGeoListener = easyGeoListener;
        mGeofencingClient = LocationServices.getGeofencingClient(mEasyGeoListener.getActivity());
    }

    private GeofencingRequest getInitialTriggerGeofencingRequest(int initialTrigger) {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(initialTrigger);
        builder.addGeofences(mEasyGeoListener.getGeoFenceList());
        return builder.build();
    }


    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (geofencePendingIntent != null) {
            return geofencePendingIntent;
        }
        Intent intent = new Intent(mEasyGeoListener.getActivity().getApplicationContext(), mEasyGeoListener.getBroadcast());
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when
        // calling addGeofences() and removeGeofences().
        geofencePendingIntent = PendingIntent.getBroadcast(mEasyGeoListener.getActivity().getApplicationContext(), 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
        return geofencePendingIntent;
    }
}
