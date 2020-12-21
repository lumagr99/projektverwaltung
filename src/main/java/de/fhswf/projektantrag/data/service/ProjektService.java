package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.ProjektDao;
import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.repository.ProjektRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjektService extends ProjektDao {
    @Autowired
    private ProjektRepository projektRepository;

    @Override
    public Optional<ProjektEntity> get(int id) {
        return projektRepository.findById(id);
    }

    @Override
    public List<ProjektEntity> getAll() {
        List<ProjektEntity> list = StreamSupport.stream(projektRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(ProjektEntity projektEntity) {
        projektRepository.save(projektEntity);
    }

    @Override
    public void update(ProjektEntity projektEntity) {
        this.save(projektEntity);
    }

    @Override
    public void delete(ProjektEntity projektEntity) {
        projektRepository.delete(projektEntity);
    }

    @Override
    public void delete(int id) {
        projektRepository.deleteById(id);
    }

    public List<ProjektEntity> getAllByStatusId(int statusid){
        List<ProjektEntity> list = StreamSupport.stream(projektRepository.getByStatusid(statusid).spliterator(), false).collect(Collectors.toList());
        return list;
    }

    public List<ProjektEntity> getAllByStatusIdAndTitle(int statusid, String title){
        List<ProjektEntity> list = StreamSupport.stream(projektRepository.getAllByStatusidAndTitel(statusid, title).spliterator(), false).collect(Collectors.toList());
        return list;
    }

    public List<ProjektEntity> getAllByTitle(String title){
        List<ProjektEntity> list = StreamSupport.stream(projektRepository.getAllByTitel(title).spliterator(), false).collect(Collectors.toList());
        return list;
    }

}
