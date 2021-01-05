package de.fhswf.projektantrag.views.lists;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.manager.OrganisationManager;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Erstellt eine ggf. Organisationsspezifische Ansicht von Ansprechpartnern.
 */
@Route(value = "ansprechpartner", layout = MainView.class)
@PageTitle("Ansprechpartner | ProjektAntrag")
public class AnsprechpartnerList extends BenutzerList {

    @Autowired
    OrganisationManager organisationManager;


    List<BenutzerEntity> updateList() {
        return organisationManager.getAnsprechpartner();
    }


}
