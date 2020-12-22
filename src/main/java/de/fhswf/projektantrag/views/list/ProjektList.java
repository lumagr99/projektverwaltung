package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import de.fhswf.projektantrag.data.entities.Benutzer2ProjektEntity;
import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.service.Benutzer2ProjektService;
import de.fhswf.projektantrag.data.service.BenutzerService;
import de.fhswf.projektantrag.data.service.ProjektService;
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(value = "projekte", layout = MainView.class)
@PageTitle("Projekte | ProjektAntrag")
public class ProjektList extends VerticalLayout implements HasUrlParameter<String>{

    @Autowired
    private ProjektService projektService;
    @Autowired
    private Benutzer2ProjektService benutzer2ProjektService;
    @Autowired
    private BenutzerService benutzerService;

    private int status = -1;
    private Grid<ProjektEntity> grid;
    private List<ProjektEntity> projekte;

    private BenutzerUserDetails activeBenutzer;


    public ProjektList(ProjektService projektService,
                       Benutzer2ProjektService student2ProjektService,
                       BenutzerService ansprechpartner2ProjektEntity){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

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
    }

    private void configureGrid() {
        grid.addClassName("projekt-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.removeColumnByKey("beschreibung");
        grid.removeColumnByKey("hintergrund");
        grid.removeColumnByKey("skizze");
        grid.setColumns("id", "statusid", "titel");
        grid.asSingleSelect().addValueChangeListener(e->{
            UI.getCurrent().getSession().setAttribute(ProjektEntity.class, e.getValue());
            UI.getCurrent().navigate("projekt");
        });
        updateList("");
    }

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

    private List<ProjektEntity> generateProjektList(){
        List<ProjektEntity> list = new ArrayList<ProjektEntity>();
        if(activeBenutzer.getRolle() == 1){
            List<Benutzer2ProjektEntity> benutzer2ProjektEntities =
                    benutzer2ProjektService.findProjekteByBenutzerID(activeBenutzer.getId());
            for(Benutzer2ProjektEntity entity : benutzer2ProjektEntities){
                list.add(projektService.get(entity.getProjektid()).get());
            }
        }else if (activeBenutzer.getRolle() == 2){
            list = projektService.getAllByStatusId(2);
        }else if(activeBenutzer.getRolle() == 3){
            List<Benutzer2ProjektEntity> projektsByAnsprechpartnerID =
                    benutzer2ProjektService.findProjekteByBenutzerID(activeBenutzer.getId());
            for(Benutzer2ProjektEntity benutzer2ProjektEntity : projektsByAnsprechpartnerID){
                list.add(projektService.get(benutzer2ProjektEntity.getProjektid()).get());
            }
        }
        return list;
    }

    private void updateList(String filter){
        grid.setItems(projekte);
    }

    private HorizontalLayout getToolbar(){
        Button create = new Button("Neues Projekt");
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout toolbar = new HorizontalLayout(create);
        return toolbar;
    }
}
