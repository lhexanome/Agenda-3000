package lhexanome.agenda3000;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;


public class SnoozedEventActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Toast.makeText(this, R.string.event_snoozed, Toast.LENGTH_SHORT).show();
    }
}
