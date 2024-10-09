package riwi.com.filtrologistico.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import riwi.com.filtrologistico.Exception.ApiException;
import riwi.com.filtrologistico.Models.Carga;
import riwi.com.filtrologistico.Models.UserEntity;
import riwi.com.filtrologistico.Repositories.CargaRepository;
import riwi.com.filtrologistico.Repositories.UserRepository;
import riwi.com.filtrologistico.Service.interfaces.IUserService;
import riwi.com.filtrologistico.dtos.request.UserRequest;
import riwi.com.filtrologistico.dtos.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsuarioImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CargaRepository cargaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserEntity create(UserRequest request) {
        // dto --> entidad
        UserEntity usuario = new UserEntity().builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .rol(request.getRol())
                .tel(request.getTel())
                .password(passwordEncoder.encode(request.getPassword())) // agregar encriptacion de contraseña
                .build();

        return userRepository.save(usuario);
    }

    @Override
    public void delete(String id) {
        UserEntity usuario= userRepository.findById(id).orElseThrow(
                ()-> new ApiException("El usuario no existe"));
        userRepository.deleteById(id);
    }

    @Override
    public void path(UserRequest userRequest, String id) {
        UserEntity usuario=userRepository.findById(id).orElseThrow(()-> new ApiException("El usuario no existe"));

        if(userRequest.getNombre()!= null){
            usuario.setNombre(userRequest.getNombre());
        }
        if(userRequest.getApellido()!= null){
            usuario.setApellido(userRequest.getApellido());
        }
        if(userRequest.getEmail()!= null){
            usuario.setEmail(userRequest.getEmail());
        }
        if(userRequest.getRol()!= null){
            usuario.setRol(userRequest.getRol());

        }

        if(userRequest.getTel()!= null){
            usuario.setTel(userRequest.getTel());
        }
        if(userRequest.getPassword()!=null){
            usuario.setPassword(passwordEncoder.encode(userRequest.getPassword())); //passwordENCODER
        }
        userRepository.save(usuario);
    }

    @Override
    public void update(UserRequest request, String id) {
        UserEntity usuario=userRepository.findById(id).orElseThrow(()-> new ApiException("El usuario no existe"));

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setRol(request.getRol());
        usuario.setTel(request.getTel());
        usuario.setPassword(passwordEncoder.encode(request.getPassword())); // hasear
        userRepository.save(usuario);
    }

    @Override
    public List<UserResponse> readAll() {
        List<UserResponse> list= userRepository.findAll().stream()
                .map(usuario-> new UserResponse().builder()
                        .id(usuario.getId())
                        .nombre(usuario.getNombre())
                        .apellido(usuario.getApellido())
                        .email(usuario.getEmail())
                        .rol(usuario.getRol())
                        .tel(usuario.getTel())
                        .build()).collect(Collectors.toList());
        return list;
    }

    @Override
    public UserResponse readById(String id) {
        //entidad --> userResponse
        UserEntity usuario= userRepository.findById(id).orElseThrow(
                ()-> new ApiException("El usuario no existe"));


        UserResponse userResponse = new UserResponse().builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .email(usuario.getEmail())
                .tel(usuario.getTel())
                .rol(usuario.getRol())
                .build();

        return userResponse ;
    }

    @Override
    public  void assign(String id, List<String> cargasIds) {

       UserEntity userEntity= userRepository.findById(id).orElseThrow(()->new ApiException("El usuario no existe"));

       List<Carga> cargas=cargaRepository.findAllById(cargasIds);


       if(cargas.size()!= cargasIds.size()){
           throw new ApiException("Una o más cargas no existen");
       }

       userEntity.setCargas(cargas);
       userRepository.save(userEntity);

    }
}



