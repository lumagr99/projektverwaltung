package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import de.fhswf.projektantrag.manager.OrganisationManager;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Erstellt eine ggf. Organisationsspezifische Ansicht von Ansprechpartnern.
 */
@Route(value = "ansprechpartner", layout = MainView.class)
@PageTitle("Ansprechpartner | ProjektAntrag")
public class AnsprechpartnerList extends VerticalLayout {

    @Autowired
    OrganisationManager organisationManager;

    private Grid<BenutzerEntity> grid;

    AnsprechpartnerList(OrganisationManager organisationManager){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();

        OrganisationEntity organisationEntity;
        try {
            organisationEntity = UI.getCurrent().getSession().getAttribute(OrganisationEntity.class);
            organisationManager.select(organisationEntity.getId());
        } catch (Exception e) {
            organisationEntity = null;
        }
    }

    @PostConstruct
    private void init(){

        grid = new Grid<BenutzerEntity>(BenutzerEntity.class);
        configureGrid();

        add(grid);
    }

    private void configureGrid(){
        grid.addClassName("ansprechpartner-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.setColumns("vorname", "nachname");
        updateList();
    }

    private void updateList() {
        grid.setItems(organisationManager.getAnsprechpartner());
    }


}
