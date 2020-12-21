package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.DozentDao;
import de.fhswf.projektantrag.data.entities.DozentEntity;
import de.fhswf.projektantrag.data.repository.DozentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DozentService extends DozentDao {
    @Autowired
    private DozentRepository dozentRepository;

    @Override
    public void save(DozentEntity entity){
        dozentRepository.save(entity);
    }

    @Override
    public Optional<DozentEntity> get(int id) {
        return dozentRepository.findById(id);
    }

    public DozentEntity get(String benutzername){
        return dozentRepository.findDozentEntityByBenutzername(benutzername);
    }

    @Override
    public List<DozentEntity> getAll() {
        List<DozentEntity> list = StreamSupport.stream(dozentRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void update(DozentEntity dozentEntity) {
        this.save(dozentEntity);
    }

    @Override
    public void delete(DozentEntity dozentEntity) {
        dozentRepository.delete(dozentEntity);
    }

    @Override
    public void delete(int id) {
        dozentRepository.deleteById(id);
    }
}
