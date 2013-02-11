package test.wallpaperchanger;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsPage extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static int period;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final EditText periodInMilliseconds = (EditText) findViewById(R.id.periodInMilliSeconds);
        periodInMilliseconds.setText("10000");

        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                period = Integer.parseInt(periodInMilliseconds.getText().toString());

                Intent serviceIntent = new Intent(SettingsPage.this,WallpaperChangerService.class);
                startService(serviceIntent);

                Toast.makeText(SettingsPage.this,"Service has started...",Toast.LENGTH_LONG).show();
            }
        });
    }
}
