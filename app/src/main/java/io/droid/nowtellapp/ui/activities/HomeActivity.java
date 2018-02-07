package io.droid.nowtellapp.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.R;
import io.droid.nowtellapp.databinding.ActivityHomeBinding;
import io.droid.nowtellapp.util.Constants;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHomeBinding binding;

    @Inject
    SharedPreferences sharedPreferences;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        NowTellApp.get(this).getComponent().inject(this);
        binding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                binding.progressLayout.progressRoot.setVisibility(View.VISIBLE);
                compositeDisposable.add(Observable.just(true).delay(5000, TimeUnit.MILLISECONDS)
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
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compositeDisposable.size() > 0)
            compositeDisposable.dispose();
    }
}
