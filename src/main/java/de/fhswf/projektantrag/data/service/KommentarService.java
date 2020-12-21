package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.KommentarDao;
import de.fhswf.projektantrag.data.entities.KommentarEntity;
import de.fhswf.projektantrag.data.repository.KommentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class KommentarService extends KommentarDao {
    @Autowired
    private KommentarRepository kommentarRepository;

    @Override
    public Optional<KommentarEntity> get(int id) {
        return kommentarRepository.findById(id);
    }

    @Override
    public List<KommentarEntity> getAll() {
        List<KommentarEntity> list = StreamSupport.stream(kommentarRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(KommentarEntity kommentarEntity) {
        kommentarRepository.save(kommentarEntity);
    }

    @Override
    public void update(KommentarEntity kommentarEntity) {
        this.save(kommentarEntity);
    }

    @Override
    public void delete(KommentarEntity kommentarEntity) {
        kommentarRepository.delete(kommentarEntity);
    }

    @Override
    public void delete(int id) {
        kommentarRepository.deleteById(id);
    }

    public List<KommentarEntity> findKommentareByProjektID(int projektid){
        List<KommentarEntity> list =  StreamSupport.stream(kommentarRepository.findAllByProjektId(projektid).spliterator(), false).collect(Collectors.toList());
        return list;
    }
}
