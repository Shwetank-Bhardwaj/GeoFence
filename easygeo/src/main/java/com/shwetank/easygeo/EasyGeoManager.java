package com.shwetank.easygeo;

import android.content.BroadcastReceiver;
import android.content.Context;

public interface EasyGeoManager {

    void addGeoFence(int initialTrigger);

    void removeGeoFence();

    void addEasyGeoListener(EasyGeoListener easyGeoListener);
}
