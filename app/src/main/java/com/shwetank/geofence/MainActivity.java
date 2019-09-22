package com.shwetank.geofence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.shwetank.easygeo.EasyGeoImpl;
import com.shwetank.easygeo.EasyGeoListener;
import com.shwetank.easygeo.EasyGeoManager;
import com.shwetank.easygeo.GeoInitialTrigger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EasyGeoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EasyGeoManager instance = EasyGeoImpl.getInstance();
        instance.addEasyGeoListener(this);
        instance.addGeoFence(GeoInitialTrigger.INITIAL_TRIGGER_ENTER);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onError() {
        Toast.makeText(getApplicationContext(),"Error occurred", Toast.LENGTH_LONG).show();
    }

    @Override
    public List<Geofence> getGeoFenceList() {
        List<Geofence> geofenceList = new ArrayList<Geofence>();
        geofenceList.add(new Geofence.Builder()
                // Set the request ID of the geofence. This is a string to identify this
                // geofence.
                .setRequestId("Home")
                .setCircularRegion(
                        33.4188355,
                        -111.9101875,
                        500
                )
                .setExpirationDuration(86400000L)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
                        Geofence.GEOFENCE_TRANSITION_EXIT)
                .build());
        return geofenceList;
    }

    @Override
    public Class<?> getBroadcast() {
        return GeofenceBroadcastReceiver.class;
    }
}
