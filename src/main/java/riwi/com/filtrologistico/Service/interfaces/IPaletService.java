package riwi.com.filtrologistico.Service.interfaces;

import riwi.com.filtrologistico.Models.Carga;
import riwi.com.filtrologistico.Models.Palet;
import riwi.com.filtrologistico.Service.CRUD.*;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.request.PaletRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;
import riwi.com.filtrologistico.dtos.response.PaletResponse;

public interface IPaletService extends
        Create<PaletRequest, Palet>,
        ReadById<String, PaletResponse>,
        Delete<String>,
        ReadAll<PaletResponse>, Put<PaletRequest,String>,
        Path<PaletRequest,String>, AssingCargaPalet<String>{
}
