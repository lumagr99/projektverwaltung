package de.fhswf.projektantrag.views.lists;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Eine Grundlage für Listen von Benutzerentitys.
 * @author Luca Graef
 * @version 1.0 05.01.2020
 */
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
        benutzerEntityGrid.setItems(updateList());

        this.add(benutzerEntityGrid);
    }

    private void configureGrid() {
        benutzerEntityGrid.addClassName("benutzer-grid");
        benutzerEntityGrid.setSizeFull();
        benutzerEntityGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        benutzerEntityGrid.setColumns("vorname", "nachname");
    }

    /**
     * Gibt die zu anzeigenden Entities zurück.
     * @return
     */
    abstract List<BenutzerEntity> updateList();
}
