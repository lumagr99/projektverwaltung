package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.AnsprechpartnerDao;
import de.fhswf.projektantrag.data.entities.AnsprechpartnerEntity;
import de.fhswf.projektantrag.data.repository.AnsprechpartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnsprechpartnerService extends AnsprechpartnerDao {
    @Autowired
    private AnsprechpartnerRepository ansprechpartnerRepository;

    @Override
    public Optional<AnsprechpartnerEntity> get(int id) {
        return ansprechpartnerRepository.findById(id);
    }

    @Override
    public List<AnsprechpartnerEntity> getAll() {
        List<AnsprechpartnerEntity> list = StreamSupport.stream(ansprechpartnerRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(AnsprechpartnerEntity ansprechpartnerEntity) {
        ansprechpartnerRepository.save(ansprechpartnerEntity);
    }

    @Override
    public void update(AnsprechpartnerEntity ansprechpartnerEntity) {
        this.save(ansprechpartnerEntity);
    }

    @Override
    public void delete(AnsprechpartnerEntity ansprechpartnerEntity) {
        ansprechpartnerRepository.delete(ansprechpartnerEntity);
    }

    @Override
    public void delete(int id) {
        ansprechpartnerRepository.deleteById(id);
    }
}
