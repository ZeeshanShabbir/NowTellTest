package io.droid.nowtellapp.ui.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import io.droid.nowtellapp.NowTellApp;
import io.droid.nowtellapp.R;
import io.droid.nowtellapp.dagger.SignUpStepOneMvpModule;
import io.droid.nowtellapp.databinding.FragmentSignUpFragmentStepOneBinding;
import io.droid.nowtellapp.mvp.SignUpStepOneMvp;

public class SignUpStepOneFragment extends Fragment implements SignUpStepOneMvp.View,
        Validator.ValidationListener, View.OnClickListener, DatePickerDialog.OnDateSetListener {

    FragmentSignUpFragmentStepOneBinding binding;

    @Inject
    SignUpStepOneMvp.Presenter presenter;

    private OnNextFragmentListener mListener;

    @NotEmpty
    @Email
    TextInputEditText email;
    @NotEmpty
    @Password(message = "Please enter 6 character at least")
    TextInputEditText password;
    @NotEmpty
    TextInputEditText name;
    @NotEmpty
    TextInputEditText lastname;
    @NotEmpty
    TextInputEditText dob;
    @NotEmpty
    TextInputEditText contactNo;
    @NotEmpty
    TextInputEditText mobileNo;

    private Validator validator;


    public SignUpStepOneFragment() {
        // Required empty public constructor
    }

    public static SignUpStepOneFragment newInstance() {
        SignUpStepOneFragment fragment = new SignUpStepOneFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NowTellApp.get(getActivity()).getComponent()
                .signUpStepOneMvpComponet(new SignUpStepOneMvpModule(this))
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up_fragment_step_one,
                container, false);
        validator = new Validator(this);
        validator.setValidationListener(this);
        binding.btnNext.setOnClickListener(this);
        binding.etDob.setOnClickListener(this);
        bindViews();
        return binding.getRoot();
    }

    private void bindViews() {
        email = binding.etEmail;
        name = binding.etFirstName;
        lastname = binding.etLastName;
        dob = binding.etDob;
        contactNo = binding.etContactNumber;
        password = binding.etPassword;
        mobileNo = binding.etMobileNumber;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNextFragmentListener) {
            mListener = (OnNextFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnNextFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void showSignUpSecondScreen() {
        mListener.onNextFragment();
    }

    @Override
    public void onValidationSucceeded() {
        presenter.handleNext(email.getText().toString(),
                password.getText().toString(),
                binding.spinner.getSelectedItem().toString(),
                name.getText().toString(),
                lastname.getText().toString(),
                dob.getText().toString(),
                contactNo.getText().toString(),
                mobileNo.getText().toString(),
                binding.checkbox.isChecked());
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
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
            case R.id.et_dob:
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(getActivity(), this, now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH), now.get(Calendar.DATE));
                dpd.show();
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String date = i + "-" + (++i1) + "-" + i2;
        dob.setText(date);
    }

    public interface OnNextFragmentListener {
        // TODO: Update argument type and name
        void onNextFragment();
    }
}
