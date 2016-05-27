package hu.dpal.app.moblab.ui.reservation;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.model.Reservation;
import hu.dpal.app.moblab.util.Constants;

public class ReservationActivity extends AppCompatActivity implements IReservationScreen {

    private boolean isEditing = false;
    private Long reservationId;

    @Bind(R.id.ivImg) ImageView ivImg;
    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.tvDesc) TextView tvDesc;

    @Bind(R.id.etDate) EditText etDate;
    @Bind(R.id.etCategory) EditText etCategory;

    @Bind(R.id.btnDelete) Button btnDelete;
    @Bind(R.id.btnModify) Button btnModify;

    @Inject
    ReservationPresenter reservaitonPresenter;

    private Tracker mTracker;
    private String name = "Reservation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        ButterKnife.bind(this);

        this.reservationId = getIntent().getExtras().getLong(Constants.KEY_RESERVATION_ID);

        MobLabApplication.injector.inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        reservaitonPresenter.attachScreen(this);
        reservaitonPresenter.loadData(reservationId);
    }

    @Override
    protected void onStop() {
        super.onStop();
        reservaitonPresenter.detachScreen();
    }

    @OnClick(R.id.btnDelete)
    public void deleteReservation(View view) {
        if (!isEditing) {
            reservaitonPresenter.deleteReservation(reservationId);
        } else {
            reservaitonPresenter.cancelEditing();
        }

    }

    @OnClick(R.id.btnModify)
    public void modifyReservation(View view) {
        if (!isEditing) {
            reservaitonPresenter.startEditing();
        } else {
            reservaitonPresenter.modifyReservation(reservationId,
                    etDate.getText(),
                    etCategory.getText());
        }
    }

    @Override
    public void showPartner(Partner partner) {
        if (partner.getImg() != null) {
            Glide.with(this.getBaseContext()).load(partner.getImg()).into(ivImg);
        }
        tvTitle.setText(partner.getTitle());
        tvDesc.setText(partner.getDesc());
    }

    @Override
    public void showReservation(Reservation reservation) {
        etDate.setText(reservation.getReservationDate());
        etCategory.setText(reservation.getCategory());
    }

    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(getBaseContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void toogleReservationEdit() {
        if (isEditing) {
            etDate.setEnabled(false);
            etCategory.setEnabled(false);
            btnDelete.setText("Delete");
            btnModify.setText("Modify");
        } else {
            etDate.setEnabled(true);
            etCategory.setEnabled(true);
            btnDelete.setText("Cancel");
            btnModify.setText("Save");
        }
        isEditing = !isEditing;

    }

    @Override
    public void finishScreen() {
        finish();
    }
}
