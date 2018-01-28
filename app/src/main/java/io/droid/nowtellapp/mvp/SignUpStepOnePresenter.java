package io.droid.nowtellapp.mvp;

import io.droid.nowtellapp.model.UserSignUpData;

/**
 * Created by Zeeshan on 1/27/2018.
 */

public class SignUpStepOnePresenter implements SignUpStepOneMvp.Presenter {

    private final SignUpStepOneMvp.View view;

    public SignUpStepOnePresenter(SignUpStepOneMvp.View view) {
        this.view = view;
    }

    @Override
    public void handleNext(String email, String password, String type, String firstName,
                           String lastName, String dob, String contactNo, String mobileNo, boolean checked) {
        UserSignUpData.getInstance().getParam().put("Email", email);
        UserSignUpData.getInstance().getParam().put("Password", password);
        UserSignUpData.getInstance().getParam().put("Title", type);
        UserSignUpData.getInstance().getParam().put("FirstName", firstName);
        UserSignUpData.getInstance().getParam().put("LastName", lastName);
        UserSignUpData.getInstance().getParam().put("Date-Of-Birth", dob);
        UserSignUpData.getInstance().getParam().put("ContactNo", contactNo);
        UserSignUpData.getInstance().getParam().put("Mobile", mobileNo);
        UserSignUpData.getInstance().getParam().put("IsSubscribedToNewsletter", checked);
        view.showSignUpSecondScreen();
    }
}
