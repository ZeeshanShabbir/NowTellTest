package io.droid.nowtellapp.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.R;
import io.droid.nowtellapp.dagger.SignInMvpModule;
import io.droid.nowtellapp.databinding.FragmentSignInBinding;
import io.droid.nowtellapp.mvp.SignInMvp;
import io.droid.nowtellapp.ui.activities.HomeActivity;
import io.droid.nowtellapp.util.Constants;
import io.droid.nowtellapp.util.Utils;

public class SignInFragment extends Fragment implements SignInMvp.View, View.OnClickListener {

    private FragmentSignInBinding binding;

    private OnSignUpListener mListener;

    @Inject
    SignInMvp.Presenter presenter;

    public SignInFragment() {
        // Required empty public constructor
    }

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NowTellApp.get(getActivity()).getComponent()
                .signInMvpComponet(new SignInMvpModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false);
        binding.btnSignIn.setOnClickListener(this);
        binding.tvRegister.setOnClickListener(this);
        return binding.getRoot();
    }

    public void onSignInClicked() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignUpListener) {
            mListener = (OnSignUpListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSignUpListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.handleOnPause();
        mListener = null;
    }

    @Override
    public void hideProgress() {
        binding.progressLayout.progressRoot.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        binding.progressLayout.progressRoot.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(int error) {
        Toast.makeText(getActivity(), getString(error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSignUp() {
        if (mListener != null) mListener.onSignUpClickListener();
    }

    @Override
    public void showHomeScreen() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign_in:
                if (Utils.isNetworkAvailable(getActivity())) {
                    presenter.handleLogin(binding.etUsername.getText().toString(),
                            binding.etPassword.getText().toString());
                } else {
                    presenter.handleNoInternet();
                }
                break;
            case R.id.tv_register:
                presenter.handleSignUp();
                break;
        }
    }

    public interface OnSignUpListener {
        void onSignUpClickListener();
    }
}
