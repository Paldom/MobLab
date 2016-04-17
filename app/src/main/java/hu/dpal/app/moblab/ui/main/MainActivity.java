package hu.dpal.app.moblab.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.ui.partners.PartnersActivity;
import hu.dpal.app.moblab.ui.reservation.ReservationActivity;

public class MainActivity extends AppCompatActivity implements IMainScreen {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobLabApplication.injector.inject(this);

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

        Button btnPartners = (Button) findViewById(R.id.btnPartners);
        Button btnReservation = (Button) findViewById(R.id.btnReservation);

        btnPartners.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainPresenter.showPartners();
            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mainPresenter.showReservation("TODO");
            }
        });

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
    public void showReservationScreen(String reservationCode) {
        Intent intent = new Intent(MainActivity.this, ReservationActivity.class);
        intent.putExtra("RESERVATION_CODE", reservationCode);
        startActivity(intent);
    }
}
