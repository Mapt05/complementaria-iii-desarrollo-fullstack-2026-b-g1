package com.api.vehiculos.service;

import com.api.vehiculos.entity.Vehiculo;
import com.api.vehiculos.repository.VehiculoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehiculoService {

    private final VehiculoRepository repo;

    public VehiculoService(VehiculoRepository repo) {
        this.repo = repo;
    }

    public List<Vehiculo> listar() {
        return repo.findAll();
    }

    public Vehiculo obtener(Long id) {
        return repo.findById(id).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado: " + id));
    }

    public Vehiculo crear(Vehiculo v) {
        return repo.save(v);
    }

    public Vehiculo actualizar(Long id, Vehiculo datos) {
        Vehiculo v = obtener(id);
        v.setPlaca(datos.getPlaca());
        v.setMarca(datos.getMarca());
        v.setModelo(datos.getModelo());
        v.setAnio(datos.getAnio());
        v.setColor(datos.getColor());
        return repo.save(v);
    }

    public void eliminar(Long id) {
        repo.delete(obtener(id));
    }
}