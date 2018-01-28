package io.droid.nowtellapp.webservices;

import android.support.annotation.NonNull;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.droid.nowtellapp.model.WSAuthDTO;
import io.droid.nowtellapp.model.WSResponseDTO;
import io.droid.nowtellapp.model.WSSignUpDTO;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Zeeshan on 1/26/2018.
 */

@Singleton
public class RetrofitNowTellApi implements NowTellApi {


    private final RetrofitService retrofitService;

    @Inject
    public RetrofitNowTellApi(@NonNull Retrofit retrofit) {
        this.retrofitService = retrofit.create(RetrofitService.class);
    }


    @Override
    public Observable<WSResponseDTO<WSSignUpDTO>> signupUser(HashMap<String, Object> body,
                                                             HashMap<String, String> header) {
        return retrofitService.signupUser(body, header).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<WSResponseDTO<WSSignUpDTO>, WSResponseDTO<WSSignUpDTO>>() {
                    @Override
                    public WSResponseDTO<WSSignUpDTO> apply(WSResponseDTO<WSSignUpDTO> wsSignUpDTOWSResponseDTO) throws Exception {
                        return wsSignUpDTOWSResponseDTO;
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public Observable<WSResponseDTO<WSAuthDTO>> loginUser(HashMap<String, Object> body,
                                                          HashMap<String, String> header) {

        return retrofitService.loginUser(body, header).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<WSResponseDTO<WSAuthDTO>,
                        WSResponseDTO<WSAuthDTO>>() {
                    @Override
                    public WSResponseDTO<WSAuthDTO> apply(WSResponseDTO<WSAuthDTO> wsAuthDTOWSResponseDTO) throws Exception {
                        return wsAuthDTOWSResponseDTO;
                    }
                }).doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private interface RetrofitService {
        @POST("new/test/signup")
        Observable<WSResponseDTO<WSSignUpDTO>> signupUser(@Body HashMap<String, Object> body,
                                                          @HeaderMap HashMap<String, String> header);

        @POST("auth/test/login")
        Observable<WSResponseDTO<WSAuthDTO>> loginUser(@Body HashMap<String, Object> body,
                                                       @HeaderMap HashMap<String, String> header);
    }
}
