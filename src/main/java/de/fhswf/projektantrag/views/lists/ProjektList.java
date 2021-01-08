package de.fhswf.projektantrag.views.lists;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;
import de.fhswf.projektantrag.data.entities.BenutzerEntity;
import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.service.Benutzer2ProjektService;
import de.fhswf.projektantrag.data.service.ProjektService;
import de.fhswf.projektantrag.data.service.RollenService;
import de.fhswf.projektantrag.manager.StatusManager;
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Erstellt eine Tabelle von Projekten, an die Nutzerrechte angepasst.
 */
@Route(value = "projekte", layout = MainView.class)
@PageTitle("Projekte | ProjektAntrag")
public class ProjektList extends VerticalLayout implements HasUrlParameter<String>{

    @Autowired
    private ProjektService projektService;
    @Autowired
    private Benutzer2ProjektService benutzer2ProjektService;

    @Autowired
    private StatusManager statusManager;

    @Autowired
    private RollenService rollenService;

    private int status = -1;
    private Grid<ProjektEntity> grid;
    private List<ProjektEntity> projekte;

    private BenutzerUserDetails activeBenutzer;


    public ProjektList(ProjektService projektService,
                       Benutzer2ProjektService student2ProjektService,
                       StatusManager statusManager){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

    /**
     * Initialisiert die benötigten Daten.
     */
    @PostConstruct
    private void init(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        activeBenutzer = (BenutzerUserDetails)auth.getPrincipal();

        projekte = generateProjektList();
        grid = new Grid<>(ProjektEntity.class);
        configureGrid();

        if(activeBenutzer.getRolle() == 1){
            add(getToolbar(), grid);
        }else{
            add(grid);
        }

        List<BenutzerEntity> benutzer = rollenService.get(1).get().getBenutzer();
    }

    /**
     * Konfiguriert das Grid.
     */
    private void configureGrid() {
        grid.addClassName("projekt-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.removeColumnByKey("beschreibung");
        grid.removeColumnByKey("hintergrund");
        grid.removeColumnByKey("skizze");
        grid.removeColumnByKey("status");
        grid.setColumns("id","titel");
        grid.addColumn(curr -> {
            return curr.getStatus().getBezeichnung();
        }).setHeader("Status");

        grid.asSingleSelect().addValueChangeListener(e->{
            UI.getCurrent().getSession().setAttribute(ProjektEntity.class, e.getValue());
            UI.getCurrent().navigate("projekt");
        });
        updateList("");
    }

    /**
     * Macht es möglich nach dem Status per queryParameter zu filtern.
     * @param event
     * @param parameter
     */
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter){
        Location location = event.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();
        if(parametersMap.get("status") == null){
            status = -1;
        }else{
            status = Integer.parseInt(parametersMap.get("status").get(0));
            updateList("");
        }
    }

    /**
     * Generiert die zum Benutzer passende Projektliste.
     * @return
     */
    private List<ProjektEntity> generateProjektList(){
        List<ProjektEntity> list = new ArrayList<ProjektEntity>();
        if(activeBenutzer.getRolle() == 1){
            List<Benutzer2ProjektEntity> benutzer2ProjektEntities =
                    benutzer2ProjektService.findProjekteByBenutzerID(activeBenutzer.getId());
            for(Benutzer2ProjektEntity entity : benutzer2ProjektEntities){
                list.add(projektService.get(entity.getProjektid()).get());
            }
        }else if (activeBenutzer.getRolle() == 2){
            try{
                list = projektService.getAllByStatus(statusManager.getStatus(2));
            }catch (Exception e){

            }
        }else if(activeBenutzer.getRolle() == 3){
            List<Benutzer2ProjektEntity> projektsByAnsprechpartnerID =
                    benutzer2ProjektService.findProjekteByBenutzerID(activeBenutzer.getId());
            for(Benutzer2ProjektEntity benutzer2ProjektEntity : projektsByAnsprechpartnerID){
                list.add(projektService.get(benutzer2ProjektEntity.getProjektid()).get());
            }
        }
        return list;
    }

    /**
     * Aktualisiert die grid items.
     * @param filter
     */

    private void updateList(String filter){
        grid.setItems(projekte);
    }


    /**
     * Erstellt ein neues Projekt und öffnet es.
     * @return
     */
    private HorizontalLayout getToolbar(){
        Button create = new Button("Neues Projekt");
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        create.addClickListener( buttonClickEvent -> {
            UI.getCurrent().getSession().setAttribute(ProjektEntity.class, null);
            UI.getCurrent().navigate("projekt");
        });

        HorizontalLayout toolbar = new HorizontalLayout(create);
        return toolbar;
    }
}
