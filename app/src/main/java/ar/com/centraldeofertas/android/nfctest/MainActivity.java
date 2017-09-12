package ar.com.centraldeofertas.android.nfctest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Set;

import be.appfoundry.nfclibrary.activities.NfcActivity;
import be.appfoundry.nfclibrary.utilities.sync.NfcReadUtilityImpl;

public class MainActivity extends NfcActivity {
    private static final String TAG = NfcActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private boolean isValidId(byte[] arr) {
        byte[] pass = {111, 41, 99, 76};
        for (int i = 0; i < 4; i++) {
            if (arr[i] != pass[i]) {
                Log.d(TAG, "En posicion " + i + " No es igual");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle b = intent.getExtras();
        byte[] arr = b.getByteArray("android.nfc.extra.ID");

        if (arr != null) {
            if(isValidId(arr)){
                Toast.makeText(this,"Todo peola",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"Todo mal",Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.error_card_read), Toast.LENGTH_LONG).show();
        }
    }
}
