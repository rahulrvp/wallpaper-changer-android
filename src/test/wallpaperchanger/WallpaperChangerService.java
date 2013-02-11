package test.wallpaperchanger;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 * User: blackbell
 * Date: 1/12/13
 * Time: 11:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class WallpaperChangerService extends Service {
    private Timer _wallpaperTimer;
    private int counter;
    ArrayList _imageFilePathList;
    long _dayDurationMilliSec;

    @Override
    public void onCreate(){
        super.onCreate();
        counter = 0;
        _wallpaperTimer = new Timer();
        _dayDurationMilliSec = 1000*60*60*24;
    }

    @Override
    public int onStartCommand(Intent intent, int flag, int startId){
        FileDiscovery _fileDiscovery = new FileDiscovery("/mnt/");
        _fileDiscovery.startSearch();
        _imageFilePathList = _fileDiscovery._filePathList;

        _dayDurationMilliSec = SettingsPage.period;

        _wallpaperTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (counter < _imageFilePathList.size()) {
                        // Log.v("LOG_TEST","Timer task executed " + (counter++) + " times");
                        WallpaperManager.getInstance(WallpaperChangerService.this).setBitmap(BitmapFactory.decodeFile((String) _imageFilePathList.get(counter++)));
                    } else {
                        counter = 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0,_dayDurationMilliSec);
        return super.onStartCommand(intent,flag,startId);
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
