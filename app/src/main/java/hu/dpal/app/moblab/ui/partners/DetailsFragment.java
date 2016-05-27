package hu.dpal.app.moblab.ui.partners;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import hu.dpal.app.moblab.ui.reservation.ReservationActivity;
import hu.dpal.app.moblab.util.Constants;


public class DetailsFragment extends Fragment implements IDetailsScreen {

    //TODO: move to an activity
    //https://codelabs.developers.google.com/codelabs/material-design-style/index.html?index=..%2F..%2Findex#6

    public static final String TAG = "DF_TAG";

    private static final String ARG_PARTNER_ID = "partnerId";

    private Long partnerId;

    @Bind(R.id.imageView) ImageView imageView;
    @Bind(R.id.tvTitle) TextView tvTitle;
    @Bind(R.id.tvDesc) TextView tvDesc;
    @Bind(R.id.tvAddress) TextView tvAddress;
    @Bind(R.id.tvEmail) TextView tvEmail;
    @Bind(R.id.tvPhone) TextView tvPhone;
    @Bind(R.id.tvWeb) TextView tvWeb;

    @Bind(R.id.fab) FloatingActionButton fab;

    @Inject
    DetailsPresenter detailsPresenter;

    private Tracker mTracker;
    private String name = "PartnerDetails";

    public DetailsFragment() {
        // Required empty public constructor
        MobLabApplication.injector.inject(this);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param partnerId Parameter 1.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(Long partnerId) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_PARTNER_ID, partnerId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        this.partnerId = bundle.getLong(ARG_PARTNER_ID);
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        ButterKnife.bind(this, view);
        detailsPresenter.loadPartner(partnerId);
        return view;
    }

    @OnClick(R.id.fab)
    public void makeReservation(View view) {
        detailsPresenter.beginReservation();
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        detailsPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        detailsPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public void showPartner(Partner partner) {
        if (partner.getImg() != null) {
            Glide.with(this.getContext()).load(partner.getImg()).centerCrop().crossFade().into(this.imageView);
        }
        this.tvTitle.setText(partner.getTitle());
        this.tvDesc.setText(partner.getDesc());
        // TODO: email - this.tvEmail.setText();
        this.tvAddress.setText(partner.getAddress());
        this.tvPhone.setText(partner.getPhone());
        this.tvWeb.setText(partner.getWebsite());
    }

    @Override
    public void showNetworkError(String errorMsg) {
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showReservationScreen(Long reservationId) {
        Intent intent = new Intent(this.getActivity(), ReservationActivity.class);
        intent.putExtra(Constants.KEY_RESERVATION_ID, reservationId);
        startActivity(intent);
    }

    @Override
    public void showReservationDialog() {
        Dialog dialog;

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_reserve, null);
        builder.setView(dialogView)
                .setTitle("Make Reservation")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        TextView tvDate = (TextView) dialogView.findViewById(R.id.tvDate);
                        TextView tvCategory = (TextView) dialogView.findViewById(R.id.tvCategory);
                        detailsPresenter.makeReservation(new Reservation(
                                partnerId,
                                "CODE",
                                tvDate.getText().toString(),
                                tvCategory.getText().toString()));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        dialog = builder.create();
        dialog.show();

    }

}
