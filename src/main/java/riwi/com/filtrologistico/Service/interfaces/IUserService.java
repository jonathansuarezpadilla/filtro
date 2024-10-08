package riwi.com.filtrologistico.Service.interfaces;

import riwi.com.filtrologistico.Models.UserEntity;
import riwi.com.filtrologistico.Service.CRUD.*;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.UserResponse;

public interface IUserService extends
        Create<UserRequest, UserEntity>,
        ReadById<String, UserResponse>,
        Delete<String>,
        ReadAll<UserResponse>, Put<UserRequest,String>,
        Path<UserRequest,String>, AssingCargaUsuario<String>{
}
