package hu.dpal.app.moblab.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import hu.dpal.app.moblab.ui.partners.DetailsFragment;

/**
 * Created by dpal on 20/04/16.
 */
public abstract class FragmentHolderActivity extends AppCompatActivity {

    protected abstract int getFragmentContainerId();

    public Fragment getActiveFragment() {
        return this.getSupportFragmentManager().findFragmentById(getFragmentContainerId());
    }

    public void pushFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.add(getFragmentContainerId(), fragment, tag);
        } else {
            fragmentTransaction.replace(getFragmentContainerId(), fragment,  tag);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getActiveFragment();

        if (fragment == null) return;
        if (fragment instanceof IFragmentBackPressed) {
            if (((IFragmentBackPressed) fragment).onBackPressed()) return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() <= 1) {
            finish();
            return;
        } else {
            fragmentManager.popBackStackImmediate();
        }

        super.onBackPressed();
    }

}
