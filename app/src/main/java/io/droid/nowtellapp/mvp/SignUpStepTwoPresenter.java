package io.droid.nowtellapp.mvp;

import org.json.JSONObject;

import java.util.HashMap;

import io.droid.nowtellapp.R;
import io.droid.nowtellapp.model.UserSignUpData;
import io.droid.nowtellapp.model.WSResponseDTO;
import io.droid.nowtellapp.model.WSSignUpDTO;
import io.droid.nowtellapp.util.Utils;
import io.droid.nowtellapp.webservices.NowTellApi;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Zeeshan on 1/28/2018.
 */

public class SignUpStepTwoPresenter implements SignUpStepTwoMvp.Presenter {

    private final SignUpStepTwoMvp.View view;
    private final NowTellApi api;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public SignUpStepTwoPresenter(SignUpStepTwoMvp.View view, NowTellApi api) {
        this.view = view;
        this.api = api;
    }

    // TODO: 1/28/2018 use builder pattern
    @Override
    public void handleSignUp(String address, String addressLineOne, String country,
                             String city, String county, String postalCode, String addressSp,
                             String addressLineOneSp, String countrySp, String citySp,
                             String countySp, String postalCodeSp, String addressBl,
                             String addressLineOneBl, String countryBl, String cityBI,
                             String countyBl, String postalCodeBl) {
        view.showProgress();
        HashMap<String, String> homeAddress = new HashMap<>();
        homeAddress.put("AddressL1", address);
        homeAddress.put("AddressL2", addressLineOne);
        homeAddress.put("Country", country);
        homeAddress.put("City", city);
        homeAddress.put("PostCode", postalCode);
        homeAddress.put("County", county);

        HashMap<String, String> shippingAddress = new HashMap<>();
        shippingAddress.put("AddressL1", addressSp);
        shippingAddress.put("AddressL2", addressLineOneSp);
        shippingAddress.put("Country", countrySp);
        shippingAddress.put("City", citySp);
        shippingAddress.put("PostCode", postalCodeSp);
        shippingAddress.put("County", countySp);

        HashMap<String, String> billingAddress = new HashMap<>();
        billingAddress.put("AddressL1", addressBl);
        billingAddress.put("AddressL2", addressLineOneBl);
        billingAddress.put("Country", countryBl);
        billingAddress.put("City", cityBI);
        billingAddress.put("PostCode", postalCodeBl);
        billingAddress.put("County", countyBl);

        UserSignUpData.getInstance().getParam().put("HomeAddress", new JSONObject(homeAddress));
        UserSignUpData.getInstance().getParam().put("ShippingAddress", new JSONObject(shippingAddress));
        UserSignUpData.getInstance().getParam().put("BillingAddress", new JSONObject(billingAddress));

        HashMap<String, String> header = new HashMap<>();
        header.put("User-Agent", "Android");
        header.put("Authorization", Utils.getAuthorizationToken().replace("\n", ""));
        header.put("Content-Type", "Application/json; charset=utf-8");


        compositeDisposable.add(api.signupUser(UserSignUpData.getInstance().getParam(), header)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<WSResponseDTO<WSSignUpDTO>>() {
                    @Override
                    public void accept(WSResponseDTO<WSSignUpDTO> wsSignUpDTOWSResponseDTO) throws Exception {
                        view.hideProgress();
                        if (wsSignUpDTOWSResponseDTO.getPayload() != null
                                && wsSignUpDTOWSResponseDTO.getPayload().getSignup() != null) {
                            if (wsSignUpDTOWSResponseDTO.getPayload().getSignup().isRegistered()) {
                                view.showLoginScreen();
                            } else {
                                view.showToast(wsSignUpDTOWSResponseDTO.getMessage());
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideProgress();
                    }
                }));

    }

    @Override
    public void handleNoInternet() {
        view.showError(R.string.no_internet_connection);
    }

    @Override
    public void handleDetach() {
        if (compositeDisposable.size() > 0)
            compositeDisposable.dispose();
    }
}
