package riwi.com.filtrologistico.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Models.Carga;
import riwi.com.filtrologistico.Models.Palet;
import riwi.com.filtrologistico.Repositories.CargaRepository;
import riwi.com.filtrologistico.Repositories.PaletRepository;
import riwi.com.filtrologistico.Service.interfaces.IPaletService;
import riwi.com.filtrologistico.dtos.request.PaletRequest;
import riwi.com.filtrologistico.dtos.response.PaletResponse;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaletImpl implements IPaletService {


    @Autowired
    PaletRepository paletRepository;
    @Autowired
    private CargaRepository cargaRepository;

    @Override
    public Palet create(PaletRequest paletRequest) {



        //DTO -> entity
        Palet palet = new Palet().builder()
                .capacityMax(paletRequest.getCapacityMax())
                .location(paletRequest.getLocation())
                .statusPalet(paletRequest.getStatusPalet())
                .build();
        return paletRepository.save(palet);
    }


    @Override
    public void delete(String id) {
        Palet palet= paletRepository.findById(id).orElseThrow(()->new ApiException("El palet no existe"));
        paletRepository.delete(palet);

    }

    @Override
    public void path(PaletRequest paletRequest, String id) {
        Palet palet= paletRepository.findById(id).orElseThrow(()->new ApiException("El palet no existe"));

        if(palet.getCapacityMax()==null){
            palet.setCapacityMax(paletRequest.getCapacityMax());
        }
        if(palet.getLocation()==null){
            palet.setLocation(paletRequest.getLocation());
        }
        if(palet.getStatusPalet()==null){
            palet.setStatusPalet(paletRequest.getStatusPalet());
        }
        paletRepository.save(palet);
    }

    @Override
    public void update(PaletRequest paletRequest, String id) {
        Palet palet= paletRepository.findById(id).orElseThrow(()->new ApiException("El palet no existe"));

        palet.setCapacityMax(paletRequest.getCapacityMax());
        palet.setLocation(paletRequest.getLocation());
        palet.setStatusPalet(paletRequest.getStatusPalet());
        paletRepository.save(palet);
    }

    @Override
    public List<PaletResponse> readAll() {
        List<PaletResponse> palets = paletRepository.findAll().stream().map(palet->new PaletResponse().builder()
                .id(palet.getId())
                .capacityMax(palet.getCapacityMax())
                .location(palet.getLocation())
                .statusPalet(palet.getStatusPalet())
                .build()).collect(Collectors.toList());

        return palets;
    }

    @Override
    public PaletResponse readById(String id) {

        Palet palet= paletRepository.findById(id).orElseThrow(()->new ApiException("El palet no existe"));

        PaletResponse paletResponse= new PaletResponse().builder()
                .id(palet.getId())
                .capacityMax(palet.getCapacityMax())
                .location(palet.getLocation())
                .statusPalet(palet.getStatusPalet())
                .build();


        return paletResponse;
    }

    @Override
    public void AssingCargaPalet(String id, List<String> CargasIds) {

        Palet palet= paletRepository.findById(id).orElseThrow(()-> new ApiException("El palet no existe"));

        List<Carga> cargas=cargaRepository.findAllById(CargasIds);

        if(cargas.size() != CargasIds.size()){
            throw new ApiException("una o mas cargas no existen");
        }

        palet.setCargas(cargas);
        paletRepository.save(palet);

    }
}
