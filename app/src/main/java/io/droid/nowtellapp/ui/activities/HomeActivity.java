package io.droid.nowtellapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.R;
import io.droid.nowtellapp.databinding.ActivityHomeBinding;
import io.droid.nowtellapp.ui.adapter.TopupPagerAdapter;
import io.droid.nowtellapp.ui.fragments.TopupFragment;
import io.droid.nowtellapp.ui.fragments.TopupHistoryFragment;
import io.droid.nowtellapp.util.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Inject
    SharedPreferences sharedPreferences;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        NowTellApp.get(this).getComponent().inject(this);
        initUiContent();
    }

    private void initUiContent() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new TopupFragment());
        fragments.add(new TopupHistoryFragment());

        TopupPagerAdapter topupPagerAdapter = new TopupPagerAdapter(getSupportFragmentManager(), fragments);

        binding.viewpager.setAdapter(topupPagerAdapter);
        binding.tabs.setupWithViewPager(binding.viewpager);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeDisposable.size() > 0)
            compositeDisposable.dispose();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.edit().remove(Constants.SESSION_TOKEN).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_logout) {
            compositeDisposable.add(Observable.just(true).delay(2000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean aBoolean) throws Exception {
                            sharedPreferences.edit().remove(Constants.SESSION_TOKEN).apply();
                            binding.progressLayout.progressRoot.setVisibility(View.GONE);
                            startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            finish();
                        }
                    }));
        }
        return super.onOptionsItemSelected(item);
    }
}
