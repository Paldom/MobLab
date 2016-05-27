package hu.dpal.app.moblab.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.ui.partners.PartnersActivity;
import hu.dpal.app.moblab.ui.reservation.ReservationActivity;
import hu.dpal.app.moblab.util.Constants;

public class MainActivity extends AppCompatActivity implements IMainScreen {

    EditText etId;

    @Inject
    MainPresenter mainPresenter;

    private Tracker mTracker;
    private String name = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobLabApplication.injector.inject(this);

        MobLabApplication application = (MobLabApplication) getApplication();
        mTracker = application.getDefaultTracker();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        etId = (EditText) findViewById(R.id.etId);

        Button btnPartners = (Button) findViewById(R.id.btnPartners);
        Button btnReservation = (Button) findViewById(R.id.btnReservation);

        btnPartners.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainPresenter.showPartners();
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String idString = etId.getText().toString();
                if (idString == null) return;
                try {
                    Long id = Long.parseLong(idString);
                    mainPresenter.showReservation(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("Image~" + name);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.detachScreen();
    }


    @Override
    public void showPartnersScreen() {
        Intent intent = new Intent(MainActivity.this, PartnersActivity.class);
        startActivity(intent);
    }

    @Override
    public void showReservationScreen(Long reservationId) {
        Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
        intent.putExtra(Constants.KEY_RESERVATION_ID, reservationId);
        startActivity(intent);
    }
}
