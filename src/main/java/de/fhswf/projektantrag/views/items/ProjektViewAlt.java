package de.fhswf.projektantrag.views.items;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.*;
import de.fhswf.projektantrag.data.service.*;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Route(value = "projektAlt", layout = MainView.class)
@PageTitle("Projekt | ProjektAntrag")
public class ProjektViewAlt extends VerticalLayout {
    @Autowired
    private ProjektService projektService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private Student2ProjektService student2ProjektService;
    @Autowired
    private Ansprechpartner2ProjektService ansprechpartner2ProjektService;
    @Autowired
    private AnsprechpartnerService ansprechpartnerService;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private KommentarService kommentarService;
    @Autowired
    private StatusService statusService;

    private ProjektEntity projektEntity;

    private TextField title;
    private TextArea skizze;
    private TextArea hintergrund;
    private TextArea beschreibung;

    private HorizontalLayout studentLayout;


    ProjektViewAlt(ProjektService projektService, StudentService studentService,
                   Student2ProjektService student2ProjektService, AnsprechpartnerService ansprechpartnerService,
                   Ansprechpartner2ProjektService ansprechpartner2ProjektService,
                   KommentarService kommentarService, StatusService statusService) {
        setSizeFull();
        try {
            projektEntity = UI.getCurrent().getSession().getAttribute(ProjektEntity.class);
        } catch (Exception e) {
            projektEntity = null;
        }

    }

    @PostConstruct
    public void init() {
        createProjektEntityIfNotExist();
        add(addTextFields(), new H3("Studenten"), addStudents(), new H3("Ansprechpartner"),
                addAnsprechpartner(), new H3("Kommentare"), addKommentare(), addButtonToolbar(),
                addModifyStatusBar());
    }

    private void createProjektEntityIfNotExist() {
        if (projektEntity == null) {
            projektEntity = new ProjektEntity();
            projektEntity.setStatusid(1);
            projektService.save(projektEntity);

            //TODO Get StudentID by current user
            Student2ProjektEntity student2ProjektEntity = new Student2ProjektEntity();
            student2ProjektEntity.setProjektId(projektEntity.getId());
            student2ProjektService.save(student2ProjektEntity);
        }
    }

    private VerticalLayout addTextFields() {
        title = new TextField();
        title.setId("projekt-title");
        title.setHelperText("Titel des Projekts");
        title.setValue(projektEntity.getTitel());
        title.setWidthFull();

        skizze = new TextArea();
        skizze.setId("projekt-skizze");
        skizze.setHelperText("Kurze Skizze des Projekts");
        skizze.setValue(projektEntity.getSkizze());
        skizze.setWidthFull();
        skizze.setHeight("100px");

        hintergrund = new TextArea();
        hintergrund.setId("projekt-hintergrund");
        hintergrund.setHelperText("Beschreibung des Projekthintergrunds");
        hintergrund.setValue(projektEntity.getHintergrund());
        hintergrund.setWidthFull();
        hintergrund.setHeight("200px");

        beschreibung = new TextArea();
        beschreibung.setId("projekt-beschreibung");
        beschreibung.setHelperText("Beschreibung des Projekts");
        beschreibung.setValue(projektEntity.getBeschreibung());
        beschreibung.setWidthFull();
        beschreibung.setMinHeight("400px");
        beschreibung.setHeight("80%");

        //TODO status != 1 oder kein student!!
        if (projektEntity.getStatusid() != 1) {
            beschreibung.setReadOnly(true);
            hintergrund.setReadOnly(true);
            skizze.setReadOnly(true);
            title.setReadOnly(true);
        }

        VerticalLayout layout = new VerticalLayout();
        layout.setId("textfield-layout");
        layout.add(title, skizze, hintergrund, beschreibung);
        layout.setSizeFull();
        return layout;
    }

    //TODO view only as student
    private HorizontalLayout addButtonToolbar() {
        Button speichern = new Button("Speichern");
        speichern.setId("button-speichern");
        speichern.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        speichern.addClickListener(e -> save());

        Button freigeben = new Button("Freigabe beantragen");
        freigeben.setId("button-freigeben");
        freigeben.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        freigeben.addClickListener(e -> {
            projektEntity.setStatusid(2);
            save();
            getUI().ifPresent(ui -> ui.navigate("projekte"));
        });

        Button close = new Button("Schließen");
        close.setId("button-close");
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        close.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate("projekte"));
        });

        if (projektEntity.getStatusid() != 1) {
            freigeben.setEnabled(false);
            speichern.setEnabled(false);
        }

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.add(speichern, freigeben, close);
        toolbar.setId("button-toolbar");
        toolbar.setWidthFull();
        toolbar.setAlignItems(Alignment.END);

        return toolbar;
    }

    private HorizontalLayout addStudents() {
        studentLayout = new HorizontalLayout();
        List<Student2ProjektEntity> student2ProjektEntities = student2ProjektService.findStudentsByProjectID(projektEntity.getId());

        for(int i = 0; i<student2ProjektEntities.size(); i++){
            StudentEntity studentEntity = studentService.get(student2ProjektEntities.get(i).getStudentId()).get();
            studentLayout.add(addStudent(studentEntity));
        }

        //TODO view only as student
        if (studentLayout.getComponentCount() < 3 && projektEntity.getStatusid() == 1) {
            ComboBox<StudentEntity> comboBox = new ComboBox<StudentEntity>();
            comboBox.setLabel("Student");
            //TODO Vor und Nachname?
            comboBox.setItemLabelGenerator(StudentEntity::getNachname);
            comboBox.setItems(studentService.getAll());

            Button addStudent = new Button("Student hinzufügen");
            addStudent.setId("add-student-button");
            addStudent.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

            addStudent.addClickListener(buttonClickEvent -> {
                boolean inList = false;

                for(int i = 0; i< student2ProjektEntities.size(); i++){
                    if(student2ProjektEntities.get(i).getStudentId() == comboBox.getValue().getId()){
                        inList = true;
                        break;
                    }
                }

               if(!inList){
                   Student2ProjektEntity student2ProjektEntity = new Student2ProjektEntity();
                   student2ProjektEntity.setStudentId(comboBox.getValue().getId());
                   student2ProjektEntity.setProjektId(projektEntity.getId());
                   student2ProjektService.save(student2ProjektEntity);
                   studentLayout.add(addStudent(studentService.get(comboBox.getValue().getId()).get()));
                   comboBox.setVisible(false);
                   addStudent.setVisible(false);
               }else{
                   Notification notification = new Notification("Der Student existiert bereits!", 3000);
                   notification.open();
               }
            });
            VerticalLayout verticalLayout = new VerticalLayout(comboBox, addStudent);
            verticalLayout.setWidth("33%");
            studentLayout.add(verticalLayout);

        }

        studentLayout.setWidthFull();
        studentLayout.setId("student-list");
        return studentLayout;
    }

    private VerticalLayout addStudent(StudentEntity studentEntity) {
        H5 vorname = new H5(studentEntity.getVorname());
        H5 nachname = new H5(studentEntity.getNachname());

        VerticalLayout layout = new VerticalLayout();
        layout.add(vorname, nachname);
        layout.setId("student-" + studentEntity.getNachname());
        layout.setWidth("33%");
        return layout;
    }

    private void save() {
        if (title != null && beschreibung != null && skizze != null && hintergrund != null) {
            projektEntity.setTitel(title.getValue());
            projektEntity.setSkizze(skizze.getValue());
            projektEntity.setHintergrund(hintergrund.getValue());
            projektEntity.setBeschreibung(beschreibung.getValue());
            projektService.update(projektEntity);

        }
    }

    private HorizontalLayout addAnsprechpartner() {
        HorizontalLayout layout = new HorizontalLayout();
        List<Ansprechpartner2ProjektEntity> ansprechpartnerEntities = ansprechpartner2ProjektService.findAnsprechpartnerByProjektID(projektEntity.getId());
        if (ansprechpartnerEntities.size() == 1) {
            VerticalLayout vert = new VerticalLayout();
            AnsprechpartnerEntity ansprechpartnerEntity =
                    ansprechpartnerService.get(ansprechpartnerEntities.get(0).getAnsprechpartnerid()).get();
            H5 vorname = new H5(ansprechpartnerEntity.getVorname());
            H5 nachname = new H5(ansprechpartnerEntity.getNachname());
            H6 organisation = new H6(organisationService.get(ansprechpartnerEntity.getOrganisation()).get().getName());

            vert.setId("ansprechpartner-" + ansprechpartnerEntity.getNachname());
            vert.setWidthFull();
            vert.add(vorname, nachname, organisation);
            layout.add(vert);
        } else if (ansprechpartnerEntities.size() == 0 && projektEntity.getStatusid() == 1) {
            //TODO View only as student
            Button button = new Button("Ansprechpartner hinzufügen");
            button.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            button.setId("add-ansprechpartner-button");

            button.addClickListener(buttonClickEvent -> {

            });

            layout.add(button);
        }

        layout.setWidthFull();
        layout.setId("ansprechpartner-layout");
        return layout;
    }

    private VerticalLayout addKommentare() {
        VerticalLayout verticalLayout = new VerticalLayout();

        List<KommentarEntity> kommentare = kommentarService.findKommentareByProjektID(projektEntity.getId());
        if (kommentare.size() > 0) {
            Div div = new Div();
            div.setId("kommentar-div");
            div.setWidthFull();
            for (int i = 0; i < kommentare.size(); i++) {
                div.add(new H4("Kommentar " + (i + 1)));
                div.add(kommentare.get(i).getText());
            }
            verticalLayout.add(div);
        }

        verticalLayout.setWidthFull();
        verticalLayout.setId("kommentar-layout");
        return verticalLayout;
    }

    //TODO reload comments after save
    private Dialog kommentarPopUp() {
        Dialog dialog = new Dialog();

        TextArea kommentar = new TextArea();
        kommentar.setWidthFull();
        kommentar.setHelperText("Bitten geben sie einen Kommentar ein.");
        kommentar.setMinHeight("90%");

        Button save = new Button();
        save.setText("Abschicken");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.addClickListener(buttonClickEvent -> {
            KommentarEntity kommentarEntity = new KommentarEntity();
            kommentarEntity.setProjektId(projektEntity.getId());
            kommentarEntity.setText(kommentar.getValue());
            kommentarService.save(kommentarEntity);
            dialog.close();
        });

        Button exit = new Button();
        exit.setText("Schließen");
        exit.addThemeVariants(ButtonVariant.LUMO_ERROR);
        exit.addClickListener(buttonClickEvent -> dialog.close());

        dialog.setWidthFull();
        dialog.setHeight("33%");
        dialog.add(kommentar, new HorizontalLayout(save, exit));
        return dialog;
    }

    //TODO nur für dozenten
    private HorizontalLayout addModifyStatusBar() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        ArrayList<StatusEntity> stati = new ArrayList<StatusEntity>();
        stati.add(statusService.get(1).get());
        stati.add(statusService.get(3).get());
        stati.add(statusService.get(4).get());

        ComboBox<StatusEntity> statusEntityComboBox = new ComboBox<>();
        statusEntityComboBox.setItemLabelGenerator(StatusEntity::getBezeichnung);
        statusEntityComboBox.setItems(stati);

        Button save = new Button("Status Speichern");
        save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        save.addClickListener(buttonClickEvent -> {
            projektEntity.setStatusid(statusEntityComboBox.getValue().getId());
            save();
            horizontalLayout.setVisible(false);
            if(statusEntityComboBox.getValue().getId() != 3) {
                kommentarPopUp().open();
            }
        });


        horizontalLayout.add(statusEntityComboBox,save);
        horizontalLayout.setWidthFull();

        return horizontalLayout;
    }

}