package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;

import javax.annotation.PostConstruct;

public abstract class BenutzerList extends VerticalLayout {

    private Grid<BenutzerEntity> benutzerEntityGrid;

    BenutzerList(){
        this.setId("benutzerentity-view");
        this.setSizeFull();
    }

    @PostConstruct
    private void init(){
        benutzerEntityGrid = new Grid<BenutzerEntity>(BenutzerEntity.class);

        configureGrid();
        updateList();

        this.add(benutzerEntityGrid);
    }

    private void configureGrid() {
        benutzerEntityGrid.addClassName("benutzer-grid");
        benutzerEntityGrid.setSizeFull();
        benutzerEntityGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        benutzerEntityGrid.setColumns("vorname", "nachname");
    }

    abstract void updateList();
}
