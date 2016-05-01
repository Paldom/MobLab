package hu.dpal.app.moblab.ui.partners;

import android.support.v4.app.Fragment;

/**
 * Created by dpal on 01/05/16.
 */
public interface IFragmentNavigator {

    void pushFragment(Fragment fragment, String tag, boolean addToBackStack);

}
