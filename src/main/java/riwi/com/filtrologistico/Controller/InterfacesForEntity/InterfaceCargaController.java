package riwi.com.filtrologistico.Controller.InterfacesForEntity;

import riwi.com.filtrologistico.Controller.GenericMethods.*;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;
import riwi.com.filtrologistico.dtos.response.UserResponse;

public interface InterfaceCargaController extends
        Byid<String, CargaResponse>,
        Create<CargaRequest,String>,
        Delete<String,String>,
        ReadAll<CargaResponse>,
        Put<CargaRequest,String>,
        Path<CargaRequest,String>{
}
