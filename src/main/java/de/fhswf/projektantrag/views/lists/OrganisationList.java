package de.fhswf.projektantrag.views.lists;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import de.fhswf.projektantrag.manager.OrganisationManager;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Erstellt eine Liste von allen Organisationen.
 */
@Route(value = "organisationen", layout = MainView.class)
@PageTitle("Organisationen | ProjektAntrag")
public class OrganisationList extends VerticalLayout {

    @Autowired
    OrganisationManager organisationManager;


    private Grid<OrganisationEntity> grid;

    /**
     * Initalisiert die Seite.
     * @param organisationManager
     */
    OrganisationList(OrganisationManager organisationManager){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

    /**
     * Initialisiert die Daten.
     */
    @PostConstruct
    private void init(){
        grid = new Grid<OrganisationEntity>(OrganisationEntity.class);
        configureGrid();

        add(grid);
    }

    /**
     * Konfiguriert die Tabelle.
     */
    private void configureGrid() {
        grid.addClassName("organisationen-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.removeColumnByKey("id");

        grid.addColumn(c ->{
            organisationManager.select(c.getId());
            return organisationManager.getAnsprechpartner().size();
        }).setHeader("Anzahl der Ansprechpartner");

        grid.asSingleSelect().addValueChangeListener(e->{
          organisationManager.select(e.getValue());
          UI.getCurrent().navigate("ansprechpartner");
        });
        updateList();
    }

    /**
     * Aktualisiert die Tabelleneinträge.
     */
    private void updateList() {
        grid.setItems(organisationManager.getAll());
    }

}
