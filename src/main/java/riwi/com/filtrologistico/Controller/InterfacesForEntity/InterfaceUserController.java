package riwi.com.filtrologistico.Controller.InterfacesForEntity;

import riwi.com.filtrologistico.Controller.GenericMethods.*;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.UserResponse;

public interface InterfaceUserController extends
        Byid<String, UserResponse>,
        Create<UserRequest,String>,
        Delete<String,String>,
        ReadAll<UserResponse>,
        Put<UserRequest,String>,
        Path<UserRequest,String>{
}
