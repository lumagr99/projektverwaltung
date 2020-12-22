package de.fhswf.projektantrag.views.list;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.*;
import de.fhswf.projektantrag.data.entities.ProjektEntity;
import de.fhswf.projektantrag.data.entities.Student2ProjektEntity;
import de.fhswf.projektantrag.data.service.ProjektService;
import de.fhswf.projektantrag.data.service.Student2ProjektService;
import de.fhswf.projektantrag.security.details.StudentUserDetails;
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
    private Student2ProjektService student2ProjektService;

    private int status = -1;
    private Grid<ProjektEntity> grid;
    private List<ProjektEntity> projekte;

    private String role = "";


    public ProjektList(ProjektService projektService, Student2ProjektService student2ProjektService){
        setId("project-list-view");
        addClassName("project-list-view");
        setSizeFull();
    }

    @PostConstruct
    private void init(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null &&
                auth.getAuthorities().stream().anyMatch(a ->
                        a.getAuthority().equalsIgnoreCase("student"))) {
            role = "Student";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equalsIgnoreCase("dozent"))) {
            role = "Dozent";
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equalsIgnoreCase("ansprechpartner"))){
            role = "Ansprechpartner";
        }
        System.out.println(auth.getDetails());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.getCredentials());
        System.out.println(auth.getPrincipal().getClass());
        StudentUserDetails currStudent = (StudentUserDetails)auth.getPrincipal();
        System.out.println(currStudent.getUsername());
        System.out.println(auth.getName());
        projekte = generateProjektList();
        grid = new Grid<>(ProjektEntity.class);
        configureGrid();
        add(getToolbar(), grid);
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

        //HOW TO GET CURRENT USER ID?
        if(role.equalsIgnoreCase("student")){
            List<Student2ProjektEntity> projektsByStudentID = student2ProjektService.findProjektsByStudentID(1);
            for(Student2ProjektEntity entity : projektsByStudentID){
                list.add(projektService.get(entity.getProjektId()).get());
            }
        }

        return list;
    }

    private void updateList(String filter){
        grid.setItems(projekte);
    }

    private HorizontalLayout getToolbar(){
        TextField filterTitle = new TextField();

        filterTitle.setPlaceholder("Suche nach Titel");
        filterTitle.setClearButtonVisible(true);
        filterTitle.setValueChangeMode(ValueChangeMode.LAZY);
        filterTitle.addValueChangeListener(e->{
            updateList(filterTitle.getValue());
        });

        HorizontalLayout toolbar = new HorizontalLayout(filterTitle);
        return toolbar;
    }
}
