package de.fhswf.projektantrag.views.lists;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route(value = "dozenten", layout = MainView.class)
@PageTitle("Dozenten | ProjektAntrag")
public class DozentList extends BenutzerList{
    @Autowired
    BenutzerService benutzerService;

    @Override
    List<BenutzerEntity> updateList() {
        return benutzerService.findBenutzerEntitiesByRollenEntity(2);
    }
}
