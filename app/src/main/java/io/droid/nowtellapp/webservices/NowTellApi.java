package io.droid.nowtellapp.webservices;

import java.util.HashMap;

import io.droid.nowtellapp.model.WSAuthDTO;
import io.droid.nowtellapp.model.WSResponseDTO;
import io.droid.nowtellapp.model.WSSignUpDTO;
import io.reactivex.Observable;
import retrofit2.http.HeaderMap;

/**
 * Created by Zeeshan on 1/26/2018.
 */

public interface NowTellApi {
    Observable<WSResponseDTO<WSSignUpDTO>> signupUser(HashMap<String, Object> body,
                                                      HashMap<String, String> header);

    Observable<WSResponseDTO<WSAuthDTO>> loginUser(HashMap<String, Object> body,
                                                   HashMap<String, String> header);
}
