package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.RollenDao;
import de.fhswf.projektantrag.data.entities.RollenEntity;
import de.fhswf.projektantrag.data.repository.RollenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RollenService extends RollenDao {

    @Autowired
    RollenRepository rollenRepository;

    @Override
    public Optional<RollenEntity> get(int id) {
        return rollenRepository.findById(id);
    }

    @Override
    public List<RollenEntity> getAll() {
        List<RollenEntity> list = StreamSupport.stream(rollenRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(RollenEntity rollenEntity) {
        rollenRepository.save(rollenEntity);
    }

    @Override
    public void update(RollenEntity rollenEntity) {
        this.save(rollenEntity);
    }

    @Override
    public void delete(RollenEntity rollenEntity) {
        rollenRepository.delete(rollenEntity);
    }

    @Override
    public void delete(int id) {
        rollenRepository.deleteById(id);
    }
}
