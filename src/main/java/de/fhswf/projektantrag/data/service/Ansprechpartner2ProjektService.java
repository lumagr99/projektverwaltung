package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.Ansprechpartner2ProjektDao;
import de.fhswf.projektantrag.data.entities.Ansprechpartner2ProjektEntity;
import de.fhswf.projektantrag.data.repository.Ansprechpartner2ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class Ansprechpartner2ProjektService extends Ansprechpartner2ProjektDao {
    @Autowired
    private Ansprechpartner2ProjektRepository ansprechpartner2ProjektRepository;

    @Override
    public Optional<Ansprechpartner2ProjektEntity> get(int id) {
        return ansprechpartner2ProjektRepository.findById(id);
    }

    @Override
    public List<Ansprechpartner2ProjektEntity> getAll() {
        List<Ansprechpartner2ProjektEntity> list = StreamSupport.stream(ansprechpartner2ProjektRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {
        ansprechpartner2ProjektRepository.save(ansprechpartner2ProjektEntity);
    }

    @Override
    public void update(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {
        this.save(ansprechpartner2ProjektEntity);
    }

    @Override
    public void delete(Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity) {
        ansprechpartner2ProjektRepository.delete(ansprechpartner2ProjektEntity);
    }

    @Override
    public void delete(int id) {
        ansprechpartner2ProjektRepository.deleteById(id);
    }

    public List<Ansprechpartner2ProjektEntity>  findProjektsByAnsprechpartnerID(int ansprechpartnerid){
        List<Ansprechpartner2ProjektEntity> list = StreamSupport.stream(ansprechpartner2ProjektRepository.findAnsprechpartner2ProjektEntitiesByAnsprechpartnerid(ansprechpartnerid).spliterator(), false).collect(Collectors.toList());
        return list;
    }

    public List<Ansprechpartner2ProjektEntity>  findAnsprechpartnerByProjektID(int projektid){
        List<Ansprechpartner2ProjektEntity> list = StreamSupport.stream(ansprechpartner2ProjektRepository.findAnsprechpartner2ProjektEntitiesByProjektId(projektid).spliterator(), false).collect(Collectors.toList());
        return list;
    }

}
