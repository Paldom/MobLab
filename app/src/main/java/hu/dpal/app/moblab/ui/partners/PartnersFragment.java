package hu.dpal.app.moblab.ui.partners;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;

import hu.dpal.app.moblab.MobLabApplication;
import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.model.Partner;
import hu.dpal.app.moblab.ui.FragmentHolderActivity;
import hu.dpal.app.moblab.ui.IFragmentBackPressed;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PartnersFragment extends Fragment implements IPartnersScreen, IFragmentBackPressed {

    private EditText etSearch;
    private TextView tvEmpty;
    private RecyclerView rvPartners;
    private SwipeRefreshLayout srlPartners;

    private List<Partner> partnersList;
    private PartnersRecyclerViewAdapter partnersAdapter;

    private IFragmentNavigator fragmentNavigator;

    @Inject
    PartnersPresenter partnerPresenter;

    public PartnersFragment() {
        MobLabApplication.injector.inject(this);
    }

    private Tracker mTracker;
    private String name = "Partners";

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        try {
            fragmentNavigator = (IFragmentNavigator) context;
        } catch (ClassCastException e) {
            throw new RuntimeException("The activity does not implement the navigator interface");
        }

        partnerPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        partnerPresenter.detachScreen();
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_partners, container, false);

        etSearch = (EditText) view.findViewById(R.id.etSearch);
        tvEmpty = (TextView) view.findViewById(R.id.tvEmpty);

        rvPartners = (RecyclerView) view.findViewById(R.id.recyclerViewPartners);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPartners.setLayoutManager(llm);

        partnersList = new ArrayList<>();
        partnersAdapter = new PartnersRecyclerViewAdapter(getContext(), partnersList);
        rvPartners.setAdapter(partnersAdapter );

        rvPartners.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        partnerPresenter.showDetails(partnersAdapter.getItem(position).getId());
                    }
                })
        );

        srlPartners = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayoutPartners);

        srlPartners.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String query = etSearch.getText().toString();
                partnerPresenter.searchPartner(query);
            }
        });

        return view;
    }


    @Override
    public void showPartners(List<Partner> partners) {
        if (srlPartners != null) {
            srlPartners.setRefreshing(false);
        }

        partnersList.clear();
        partnersList.addAll(partners);
        partnersAdapter.notifyDataSetChanged();

        if (partnersList.isEmpty()) {
            rvPartners.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        } else {
           rvPartners.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (srlPartners != null) {
            srlPartners.setRefreshing(false);
        }
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPartnerDetailsScreen(Long partnerId) {
        // TODO: http://stackoverflow.com/questions/26561579/how-to-start-shared-element-transition-using-fragments
        // TODO: MVP fragment & activity
        fragmentNavigator.pushFragment(
                DetailsFragment.newInstance(partnerId),
                DetailsFragment.TAG,
                true);
    }

    @Override
    public boolean onBackPressed() {
        Log.d("FRAGMENT_MSG", "bye");
        return false;
    }
}
