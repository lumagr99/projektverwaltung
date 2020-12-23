package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.OrganisationEntity;
import de.fhswf.projektantrag.data.service.OrganisationService;
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;

@Route(value = "organisationen", layout = MainView.class)
@PageTitle("Organisationen | ProjektAntrag")
public class OrganisationList extends VerticalLayout {

    @Autowired
    OrganisationService organisationService;

    private Grid<OrganisationEntity> grid;
    private BenutzerUserDetails activeBenutzer;

    OrganisationList(OrganisationService organisationService){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

    @PostConstruct
    private void init(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        activeBenutzer = (BenutzerUserDetails)auth.getPrincipal();

        grid = new Grid<OrganisationEntity>(OrganisationEntity.class);
        configureGrid();

        add(grid);
    }

    private void configureGrid() {
        grid.addClassName("organisationen-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.removeColumnByKey("id");
        grid.asSingleSelect().addValueChangeListener(e->{
          UI.getCurrent().getSession().setAttribute(OrganisationEntity.class, e.getValue());
          UI.getCurrent().navigate("ansprechpartner");
        });
        updateList();
    }

    private void updateList() {
        grid.setItems(organisationService.getAll());
    }

}
