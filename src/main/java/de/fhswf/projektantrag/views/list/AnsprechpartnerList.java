package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

@Route(value = "ansprechpartner", layout = MainView.class)
@PageTitle("Ansprechpartner | ProjektAntrag")
public class AnsprechpartnerList extends VerticalLayout {

    @Autowired
    BenutzerService benutzerService;

    private Grid<BenutzerEntity> grid;
    private BenutzerUserDetails activeBenutzer;

    AnsprechpartnerList(BenutzerService benutzerService){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

    @PostConstruct
    private void init(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        activeBenutzer = (BenutzerUserDetails)auth.getPrincipal();

        grid = new Grid<BenutzerEntity>(BenutzerEntity.class);
        configureGrid();

        add(grid);
    }

    private void configureGrid(){
        grid.addClassName("ansprechpartner-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.removeColumnByKey("id");
        grid.removeColumnByKey("organisationId");
        grid.removeColumnByKey("rolleId");
        grid.removeColumnByKey("passwort");
        grid.removeColumnByKey("benutzername");
        grid.setColumns("vorname", "nachname");
        //grid.asSingleSelect().addValueChangeListener(e->{
          //  UI.getCurrent().getSession().setAttribute(BenutzerEntity.class, e.getValue());
            //UI.getCurrent().navigate("projekt");
        //});
        updateList();
    }

    private void updateList() {
        grid.setItems(benutzerService.findBenutzerEntitiesByRole(3));
    }


}
