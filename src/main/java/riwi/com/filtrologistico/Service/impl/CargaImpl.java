package riwi.com.filtrologistico.Service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Models.Carga;
import riwi.com.filtrologistico.Repositories.CargaRepository;
import riwi.com.filtrologistico.Service.interfaces.ICargaService;
import riwi.com.filtrologistico.dtos.request.CargaRequest;
import riwi.com.filtrologistico.dtos.response.CargaResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargaImpl implements ICargaService {

    @Autowired
    CargaRepository cargaRepository;

    @Override
    public Carga create(CargaRequest cargaRequest) {

        //DTO -> entity
        Carga carga = new Carga().builder()
                .weight(cargaRequest.getWeight())
                .dimensions(cargaRequest.getDimensions())
                .status(cargaRequest.getStatus())
                .build();

        return cargaRepository.save(carga);
    }

    @Override
    public void delete(String id) {
        Carga carga= cargaRepository.findById(id).orElseThrow(()->new ApiException("La Carga no existe"));
        cargaRepository.delete(carga);
    }

    @Override
    public void path(CargaRequest request, String id) {

        Carga carga=cargaRepository.findById(id).orElseThrow(()->new ApiException("La Carga no existe"));

        if(request.getWeight()!= null){
            carga.setWeight(request.getWeight());
        }

        if(request.getDimensions()!= null){
            carga.setDimensions(request.getDimensions());
        }
        if(request.getStatus()!= null){
            carga.setStatus(request.getStatus());
        }
        cargaRepository.save(carga);
    }

    @Override
    public void update(CargaRequest cargaRequest, String id) {

        Carga carga=cargaRepository.findById(id).orElseThrow(()->new ApiException("La Carga no existe"));

        carga.setWeight(cargaRequest.getWeight());
        carga.setDimensions(cargaRequest.getDimensions());
        carga.setStatus(cargaRequest.getStatus());
        cargaRepository.save(carga);
    }

    @Override
    public List<CargaResponse> readAll() {
        List<CargaResponse> list=cargaRepository.findAll()
                .stream()
                .map(carga->new CargaResponse().builder()
                        .id(carga.getId())
                        .weight(carga.getWeight())
                        .dimensions(carga.getDimensions())
                        .status(carga.getStatus())
                        .build()).collect(Collectors.toList());

        return list;
    }

    @Override
    public CargaResponse readById(String id) {

        Carga carga=cargaRepository.findById(id).orElseThrow(()->new ApiException("La Carga no existe"));

        //Entity-> DTO

        CargaResponse cargaResponse=new CargaResponse().builder()
                .id(carga.getId())
                .weight(carga.getWeight())
                .dimensions(carga.getDimensions())
                .status(carga.getStatus())
                .build();


        return cargaResponse;
    }
}
