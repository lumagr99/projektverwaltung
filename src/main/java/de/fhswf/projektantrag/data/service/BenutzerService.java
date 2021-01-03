package de.fhswf.projektantrag.data.service;

import de.fhswf.projektantrag.data.dao.BenutzerDao;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.RollenEntity;
import de.fhswf.projektantrag.data.repository.Benutzer2ProjektRepository;
import de.fhswf.projektantrag.data.repository.BenutzerRepository;
import de.fhswf.projektantrag.manager.RollenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BenutzerService extends BenutzerDao {
    @Autowired
    BenutzerRepository benutzerRepository;

    @Autowired
    Benutzer2ProjektRepository benutzer2ProjektRepository;

    @Autowired
    RollenManager rollenManager;

    @Override
    public Optional<BenutzerEntity> get(int id) {
        return benutzerRepository.findById(id);
    }

    @Override
    public List<BenutzerEntity> getAll() {
        List<BenutzerEntity> list = StreamSupport.stream(benutzerRepository.findAll().spliterator(), false).collect(Collectors.toList());
        return list;
    }

    @Override
    public void save(BenutzerEntity benutzerEntity) {
        benutzerRepository.save(benutzerEntity);
    }

    @Override
    public void update(BenutzerEntity benutzerEntity) {
        this.save(benutzerEntity);
    }

    @Override
    public void delete(BenutzerEntity benutzerEntity) {
        benutzerRepository.delete(benutzerEntity);
    }

    @Override
    public void delete(int id) {
        benutzerRepository.deleteById(id);
    }

    public BenutzerEntity findByBenutzername(String benutzername){
        return benutzerRepository.findBenutzerEntityByBenutzername(benutzername);
    }

    public List<BenutzerEntity> findBenutzerEntitiesByRollenEntity(int rollenId){
        RollenEntity rollenEntity = rollenManager.getRolle(rollenId);
        return benutzerRepository.findBenutzerEntitiesByRollenEntity(rollenEntity);
    }

    public List<BenutzerEntity> findBenutzerByOrganisationID(int id){
        return benutzerRepository.findBenutzerEntitiesByOrganisationId(id);
    }

    public List<BenutzerEntity> findBenutzerEntitiesByRolleIdAndIdNotIn(int rollenId, List<BenutzerEntity> benutzer){
        RollenEntity rollenEntity = rollenManager.getRolle(rollenId);
        List<Integer> benutzerIds = new ArrayList<>();
        for (BenutzerEntity benutzerEntity : benutzer) {
            benutzerIds.add(benutzerEntity.getId());
        }

        if (benutzerIds.size() == 0) benutzerIds.add(0);

        return benutzerRepository.findBenutzerEntitiesByRollenEntityAndIdNotIn(rollenEntity, benutzerIds);
    }


}
