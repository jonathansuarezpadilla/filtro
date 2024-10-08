package riwi.com.filtrologistico.Controller.InterfacesForEntity;

import riwi.com.filtrologistico.Controller.GenericMethods.*;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.request.PaletRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;
import riwi.com.filtrologistico.dtos.response.PaletResponse;

public interface InterfacePaletController extends
        Byid<String, PaletResponse>,
        Create<PaletRequest,String>,
        Delete<String,String>,
        ReadAll<PaletResponse>,
        Put<PaletRequest,String>,
        Path<PaletRequest,String>{
}
