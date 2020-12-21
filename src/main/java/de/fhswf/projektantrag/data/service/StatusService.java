package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.StatusDao;
import de.fhswf.projektantrag.data.entities.StatusEntity;
import de.fhswf.projektantrag.data.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StatusService extends StatusDao {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Optional<StatusEntity> get(int id) {
        return statusRepository.findById(id);
    }

    @Override
    public List<StatusEntity> getAll() {
        List<StatusEntity> list = StreamSupport.stream(statusRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(StatusEntity statusEntity) {
        statusRepository.save(statusEntity);
    }

    @Override
    public void update(StatusEntity statusEntity) {
        this.save(statusEntity);
    }

    @Override
    public void delete(StatusEntity statusEntity) {
        statusRepository.delete(statusEntity);
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);
    }
}
