package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "studenten", layout = MainView.class)
@PageTitle("Studenten | ProjektAntrag")
public class StudentList extends BenutzerList{
    @Autowired
    BenutzerService benutzerService;

    @Override
    List<BenutzerEntity> updateList() {
        return benutzerService.findBenutzerEntitiesByRollenEntity(1);
    }
}
