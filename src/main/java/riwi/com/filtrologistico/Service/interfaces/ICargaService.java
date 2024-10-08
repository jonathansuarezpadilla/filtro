package riwi.com.filtrologistico.Service.interfaces;


import riwi.com.filtrologistico.Models.Carga;

import riwi.com.filtrologistico.Service.CRUD.*;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;


public interface ICargaService extends
        Create<CargaRequest, Carga>,
        ReadById<String, CargaResponse>,
        Delete<String>,
        ReadAll<CargaResponse>, Put<CargaRequest,String>,
        Path<CargaRequest,String>{
}
