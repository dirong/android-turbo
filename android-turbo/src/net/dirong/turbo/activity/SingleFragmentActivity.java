package net.dirong.turbo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import net.dirong.turbo.R;

public abstract class SingleFragmentActivity extends FragmentActivity {

	private Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_fragment);
		fragment = onCreateFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.view_content, fragment);
		ft.commit();
	}

	protected Fragment getFragment() {
		return fragment;
	}

	protected abstract Fragment onCreateFragment();

    protected void replaceFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.view_content, fragment);
        ft.commit();
        this.fragment = fragment;
    }

}