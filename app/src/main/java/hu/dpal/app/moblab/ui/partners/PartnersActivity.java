package hu.dpal.app.moblab.ui.partners;

import android.os.Bundle;

import hu.dpal.app.moblab.R;
import hu.dpal.app.moblab.ui.FragmentHolderActivity;

public class PartnersActivity extends FragmentHolderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partners);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment;
    }
}
