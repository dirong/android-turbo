package net.dirong.turbo.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import net.dirong.turbo.R;

public abstract class FragmentActivity extends SherlockFragmentActivity {

    private MenuItem reloadMenuItem;
    private View loadingIndicator;

    private boolean isLoading;
    
    public final void setActionBarLoadingIndicatorVisible(boolean visible) {
	isLoading = visible;
	if (reloadMenuItem != null) {
	    reloadMenuItem.setActionView(visible ? loadingIndicator : null);
	} else {
	    super.setSupportProgressBarIndeterminateVisibility(visible);
	}
    }

    public final void setActionBarReloadMenuItem(MenuItem menuItem) {
	this.reloadMenuItem = menuItem;
	if (menuItem != null && loadingIndicator == null) {
	    loadingIndicator = LayoutInflater.from(this).inflate(
		    R.layout.view_ab_loading_indicator, null);
	}
	setActionBarLoadingIndicatorVisible(isLoading);
    }

    public void setFragmentVisible(int fragmentId, boolean visible) {
	FragmentManager fm = getSupportFragmentManager();
	Fragment f = fm.findFragmentById(fragmentId);
	if (f != null) {
	    FragmentTransaction ft = fm.beginTransaction();
	    if (visible) {
		ft.show(f);
	    } else {
		ft.hide(f);
	    }
	    ft.commit();
	}
    }

}