package com.tse.fdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Tse on 2016/2/25.
 * Email Via imyetse@gmail.com
 */
public class testService extends Service {

    private Handler testHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("handle message:", msg.what + ":message");
            super.handleMessage(msg);
        }
    };
    Messenger messenger = new Messenger(testHandler);

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }


}
