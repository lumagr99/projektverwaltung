package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.Benutzer2ProjektDao;
import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;
import de.fhswf.projektantrag.data.repository.Benutzer2ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Benutzer2ProjektService extends Benutzer2ProjektDao {
    @Autowired
    Benutzer2ProjektRepository benutzer2ProjektRepository;

    @Override
    public Optional<Benutzer2ProjektEntity> get(int id) {
        return benutzer2ProjektRepository.findById(id);
    }

    @Override
    public List<Benutzer2ProjektEntity> getAll() {
        List<Benutzer2ProjektEntity> list = StreamSupport.stream(benutzer2ProjektRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(Benutzer2ProjektEntity benutzer2ProjektEntity) {
        benutzer2ProjektRepository.save(benutzer2ProjektEntity);
    }

    @Override
    public void update(Benutzer2ProjektEntity benutzer2ProjektEntity) {
        this.save(benutzer2ProjektEntity);
    }

    @Override
    public void delete(Benutzer2ProjektEntity benutzer2ProjektEntity) {
        benutzer2ProjektRepository.delete(benutzer2ProjektEntity);
    }

    @Override
    public void delete(int id) {
        benutzer2ProjektRepository.deleteById(id);
    }

    public List<Benutzer2ProjektEntity> findProjekteByBenutzerID(int id){
        return benutzer2ProjektRepository.findBenutzer2ProjektEntitiesByBenutzerid(id);
    }

    public List<Benutzer2ProjektEntity> findBenutzer2ProjektByProjektID(int id){
        return benutzer2ProjektRepository.findBenutzer2ProjektEntitiesByProjektid(id);
    }
}
