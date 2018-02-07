package io.droid.nowtellapp.ui.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.R;
import io.droid.nowtellapp.dagger.SignUpStepTwoMvpModule;
import io.droid.nowtellapp.databinding.FragmentSignUpStepTwoBinding;
import io.droid.nowtellapp.mvp.SignUpStepTwoMvp;
import io.droid.nowtellapp.util.Utils;

public class SignUpStepTwoFragment extends Fragment implements SignUpStepTwoMvp.View,
        Validator.ValidationListener, View.OnClickListener {

    private OnFragmentInteractionListener mListener;

    private Validator validator;

    FragmentSignUpStepTwoBinding binding;


    //View for validation
    @NotEmpty
    TextInputEditText address;
    @NotEmpty
    TextInputEditText city;
    @NotEmpty
    TextInputEditText country;
    @NotEmpty
    TextInputEditText postalCode;

    @NotEmpty
    TextInputEditText addressSp;
    @NotEmpty
    TextInputEditText citySp;
    @NotEmpty
    TextInputEditText countrySp;

    @NotEmpty
    TextInputEditText postalCodeSp;

    @NotEmpty
    TextInputEditText addressBl;
    @NotEmpty
    TextInputEditText cityBl;
    @NotEmpty
    TextInputEditText countryBl;

    @NotEmpty
    TextInputEditText postalCodeBl;

    @Inject
    SignUpStepTwoMvp.Presenter presenter;

    public SignUpStepTwoFragment() {
        // Required empty public constructor
    }

    public static SignUpStepTwoFragment newInstance() {
        SignUpStepTwoFragment fragment = new SignUpStepTwoFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NowTellApp.get(getActivity()).getComponent()
                .signUpStepTwoMvpComponet(new SignUpStepTwoMvpModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_step_two, container, false);
        validator = new Validator(this);
        validator.setValidationListener(this);
        binding.btnNext.setOnClickListener(this);
        initCheckboxListeners();
        bindViews();
        return binding.getRoot();
    }

    private void initCheckboxListeners() {
        binding.checkboxBilling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.etAddressLineOneBl.setText(binding.etAddressLineOne.getText().toString());
                    binding.etAddressLineTwoBl.setText(binding.etAddressLineTwo.getText().toString());
                    binding.etCityBl.setText(binding.etCity.getText().toString());
                    binding.etCountyBl.setText(binding.etCounty.getText().toString());
                    binding.etCountryBl.setText(binding.etCountry.getText().toString());
                    binding.etPostalCodeBl.setText(binding.etPostalCode.getText().toString());
                } else {
                    binding.etAddressLineOneBl.setText("");
                    binding.etAddressLineTwoBl.setText("");
                    binding.etCityBl.setText("");
                    binding.etCountyBl.setText("");
                    binding.etCountyBl.setText("");
                    binding.etPostalCodeBl.setText("");
                }
            }
        });
        binding.checkboxShipping.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    binding.etAddressLineOneSp.setText(binding.etAddressLineOne.getText().toString());
                    binding.etAddressLineTwoSp.setText(binding.etAddressLineTwo.getText().toString());
                    binding.etCitySp.setText(binding.etCity.getText().toString());
                    binding.etCountySp.setText(binding.etCounty.getText().toString());
                    binding.etCountrySp.setText(binding.etCountry.getText().toString());
                    binding.etPostalCodeSp.setText(binding.etPostalCode.getText().toString());
                } else {
                    binding.etAddressLineOneSp.setText("");
                    binding.etAddressLineTwoSp.setText("");
                    binding.etCitySp.setText("");
                    binding.etCountySp.setText("");
                    binding.etCountySp.setText("");
                    binding.etPostalCodeSp.setText("");
                }
            }
        });
    }

    private void bindViews() {
        address = binding.etAddressLineOne;
        city = binding.etCity;
        country = binding.etCountry;
        postalCode = binding.etAddressLineOne;
        address = binding.etAddressLineOne;

        addressSp = binding.etAddressLineOneSp;
        citySp = binding.etCitySp;
        countrySp = binding.etCountrySp;
        postalCodeSp = binding.etAddressLineOneSp;
        addressSp = binding.etAddressLineOneSp;

        addressBl = binding.etAddressLineOneBl;
        cityBl = binding.etCityBl;
        countryBl = binding.etCountryBl;
        postalCodeBl = binding.etAddressLineOneBl;
        addressBl = binding.etAddressLineOneBl;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.handleDetach();
        mListener = null;
    }

    @Override
    public void onValidationSucceeded() {
        if (Utils.isNetworkAvailable(getActivity())) {
            presenter.handleSignUp(address.getText().toString(), binding.etAddressLineOne.getText().toString(),
                    country.getText().toString(), city.getText().toString(), binding.etCounty.getText().toString(),
                    postalCode.getText().toString(),
                    addressSp.getText().toString(), binding.etAddressLineOneSp.getText().toString(),
                    countrySp.getText().toString(), citySp.getText().toString(), binding.etCountySp.getText().toString(),
                    postalCodeSp.getText().toString(),
                    addressBl.getText().toString(), binding.etAddressLineOneBl.getText().toString(),
                    countryBl.getText().toString(), cityBl.getText().toString(), binding.etCountyBl.getText().toString(),
                    postalCodeBl.getText().toString());
        } else {
            presenter.handleNoInternet();
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        if (binding.checkboxShipping.isChecked())
            binding.checkboxShipping.setChecked(false);
        if (binding.checkboxBilling.isChecked())
            binding.checkboxBilling.setChecked(false);
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                validator.validate();
                break;
        }
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
    public void showLoginScreen() {
        mListener.onFragmentShowLogin();
    }

    @Override
    public void showProgress() {
        binding.progressLayout.progressRoot.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        binding.progressLayout.progressRoot.setVisibility(View.INVISIBLE);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentShowLogin();
    }

}
