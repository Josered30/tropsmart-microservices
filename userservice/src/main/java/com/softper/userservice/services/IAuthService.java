package com.softper.userservice.services;

import com.softper.userservice.resources.comunications.UserBoundResponse;
import com.softper.userservice.resources.inputs.RefreshInput;
import com.softper.userservice.resources.inputs.SignUp;

public interface IAuthService {
    UserBoundResponse registerComplete(SignUp signUp);
    UserBoundResponse login(String email, String password);
    UserBoundResponse loginFixed(String email, String password);
    //AuthResponse refresh(RefreshInput refreshInput) throws Exception;
}
