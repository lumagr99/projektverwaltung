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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//@PreAuthorize("hasRole('STUDENT')")
@Route(value = "projekt", layout = MainView.class)
@PageTitle("Projekt | ProjektAntrag")
public class ProjektView extends VerticalLayout {

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

    private StudentenHorizontalLayout studenten;
    private AnsprechpartnerHorizontalLayout ansprechpartner;
    private TextFieldsVerticalLayout textFields;
    private ToolbarHorizontalLayout toolbar;
    private KommentareVerticalLayout kommentare;
    private StatusToolbarHorizontalLayout statusToolbar;

    private final String role = "";

    ProjektView(ProjektService projektService, StudentService studentService,
                Student2ProjektService student2ProjektService, AnsprechpartnerService ansprechpartnerService,
                Ansprechpartner2ProjektService ansprechpartner2ProjektService,
                KommentarService kommentarService, StatusService statusService) {
        setSizeFull();
        try {
            projektEntity = UI.getCurrent().getSession().getAttribute(ProjektEntity.class);
        } catch (Exception e) {
            projektEntity = null;
        }
        createProjektEntityIfNotExist();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getAuthorities().toString());
    }

    @PostConstruct
    private void init() {
        createProjektEntityIfNotExist();
        textFields = new TextFieldsVerticalLayout();
        studenten = new StudentenHorizontalLayout();
        ansprechpartner = new AnsprechpartnerHorizontalLayout();
        kommentare = new KommentareVerticalLayout();
        toolbar = new ToolbarHorizontalLayout();
        statusToolbar = new StatusToolbarHorizontalLayout();
        this.add(textFields,
                new H3("Studenten"), studenten,
                new H3("Ansprechpartner"), ansprechpartner,
                kommentare,
                toolbar,
                statusToolbar);
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

    private void speichern() {
        if (textFields.getTitle() != null &&
                textFields.getBeschreibung() != null &&
                textFields.getHintergrund() != null &&
                textFields.getSkizze() != null) {
            projektEntity.setTitel(textFields.getTitle().getValue());
            projektEntity.setSkizze(textFields.getSkizze().getValue());
            projektEntity.setHintergrund(textFields.getHintergrund().getValue());
            projektEntity.setBeschreibung(textFields.getBeschreibung().getValue());
            projektService.update(projektEntity);

        }
    }

    private class StudentenHorizontalLayout extends HorizontalLayout {
        private final List<Student2ProjektEntity> student2ProjektEntities;
        private ComboBox<StudentEntity> comboBox;
        private Button addStudent;


        StudentenHorizontalLayout() {
            student2ProjektEntities = student2ProjektService.findStudentsByProjectID(projektEntity.getId());

            for (int i = 0; i < student2ProjektEntities.size(); i++) {
                StudentEntity studentEntity = studentService.get(student2ProjektEntities.get(i).getStudentId()).get();
                this.add(new StudentVerticalLayout(studentEntity));
            }

            //TODO view only as student
            if (this.getComponentCount() < 3 && projektEntity.getStatusid() == 1) {
                comboBox = new ComboBox<StudentEntity>();
                comboBox.setLabel("Student");
                //TODO Vor und Nachname?
                comboBox.setItemLabelGenerator(StudentEntity::getNachname);
                comboBox.setItems(studentService.getAll());

                manageStudentAddButton();

                VerticalLayout verticalLayout = new VerticalLayout(comboBox, addStudent);
                verticalLayout.setWidth("33%");
                this.add(verticalLayout);
            }

            this.setWidthFull();
            this.setId("student-list");
        }

        private void manageStudentAddButton() {
            addStudent = new Button("Student hinzufügen");
            addStudent.setId("add-student-button");
            addStudent.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

            addStudent.addClickListener(buttonClickEvent -> {
                boolean inList = false;

                for (int i = 0; i < student2ProjektEntities.size(); i++) {
                    if (student2ProjektEntities.get(i).getStudentId() == comboBox.getValue().getId()) {
                        inList = true;
                        break;
                    }
                }

                if (!inList) {
                    Student2ProjektEntity student2ProjektEntity = new Student2ProjektEntity();
                    student2ProjektEntity.setStudentId(comboBox.getValue().getId());
                    student2ProjektEntity.setProjektId(projektEntity.getId());
                    student2ProjektService.save(student2ProjektEntity);
                    this.add(new StudentVerticalLayout(studentService.get(comboBox.getValue().getId()).get()));
                    comboBox.setVisible(false);
                    addStudent.setVisible(false);
                } else {
                    Notification notification = new Notification("Der Student existiert bereits!", 3000);
                    notification.open();
                }
            });
        }
    }

    private class StudentVerticalLayout extends VerticalLayout {
        H5 vorname;
        H5 nachname;

        StudentEntity studentEntity;

        StudentVerticalLayout(StudentEntity studentEntity) {
            if (studentEntity == null) {
                throw new IllegalArgumentException();
            }

            this.studentEntity = studentEntity;

            vorname = new H5(studentEntity.getVorname());
            nachname = new H5(studentEntity.getNachname());

            VerticalLayout layout = new VerticalLayout();
            layout.add(vorname, nachname);
            layout.setId("student-" + studentEntity.getNachname());
            layout.setWidth("33%");

            this.add(vorname, nachname);
        }

        public StudentEntity getStudentEntity() {
            return studentEntity;
        }
    }


    private class AnsprechpartnerHorizontalLayout extends HorizontalLayout {
        private final List<Ansprechpartner2ProjektEntity> ansprechpartnerEntities;
        private ComboBox<AnsprechpartnerEntity> comboBox;
        private Button addAnsprechpartner;

        AnsprechpartnerHorizontalLayout() {
            ansprechpartnerEntities = ansprechpartner2ProjektService.findAnsprechpartnerByProjektID(projektEntity.getId());
            if (ansprechpartnerEntities.size() == 1) {
                AnsprechpartnerEntity ansprechpartnerEntity =
                        ansprechpartnerService.get(ansprechpartnerEntities.get(0).getAnsprechpartnerid()).get();
                this.add(new AnsprechpartnerVerticalLayout(ansprechpartnerEntity));
            } else if (ansprechpartnerEntities.size() == 0 && projektEntity.getStatusid() == 1) {
                //TODO View only as student
                addAnsprechpartner = new Button("Ansprechpartner hinzufügen");
                addAnsprechpartner.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
                addAnsprechpartner.setId("add-ansprechpartner-button");
                comboBox = new ComboBox<AnsprechpartnerEntity>();

                //TODO Vor und Nachname?
                comboBox.setItemLabelGenerator(AnsprechpartnerEntity::getNachname);
                comboBox.setItems(ansprechpartnerService.getAll());
                System.out.println("test");

                addAnsprechpartner.addClickListener(buttonClickEvent -> {
                    manageAnsprechpartnerAddButton();
                });

                this.add(comboBox, addAnsprechpartner);
            }

            this.setWidthFull();
            this.setId("ansprechpartner-layout");
        }

        private void manageAnsprechpartnerAddButton() {
            Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity = new Ansprechpartner2ProjektEntity();
            ansprechpartner2ProjektEntity.setAnsprechpartnerid(comboBox.getValue().getId());
            System.out.println(comboBox.getValue().getNachname());
            ansprechpartner2ProjektEntity.setProjektId(projektEntity.getId());
            ansprechpartner2ProjektService.save(ansprechpartner2ProjektEntity);
            this.add(new AnsprechpartnerVerticalLayout(comboBox.getValue()));
            comboBox.setVisible(false);
            addAnsprechpartner.setVisible(false);
        }
    }

    private class AnsprechpartnerVerticalLayout extends VerticalLayout {
        AnsprechpartnerEntity ansprechpartnerEntity;

        AnsprechpartnerVerticalLayout(AnsprechpartnerEntity ansprechpartnerEntity) {
            if (ansprechpartnerEntity == null) {
                throw new IllegalArgumentException();
            }
            this.ansprechpartnerEntity = ansprechpartnerEntity;
            H5 vorname = new H5(ansprechpartnerEntity.getVorname());
            H5 nachname = new H5(ansprechpartnerEntity.getNachname());
            H6 organisation = new H6(organisationService.get(ansprechpartnerEntity.getOrganisation()).get().getName());

            this.setId("ansprechpartner-" + ansprechpartnerEntity.getNachname());
            this.setWidthFull();
            this.add(vorname, nachname, organisation);
        }
    }

    private class TextFieldsVerticalLayout extends VerticalLayout {
        private final TextField title;
        private final TextArea skizze;
        private final TextArea hintergrund;
        private final TextArea beschreibung;

        TextFieldsVerticalLayout() {
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

            this.setId("textfield-layout");
            this.add(title, skizze, hintergrund, beschreibung);
            this.setSizeFull();
        }

        public TextField getTitle() {
            return title;
        }

        public TextArea getSkizze() {
            return skizze;
        }

        public TextArea getHintergrund() {
            return hintergrund;
        }

        public TextArea getBeschreibung() {
            return beschreibung;
        }
    }

    private class ToolbarHorizontalLayout extends HorizontalLayout {
        private final Button speichern;
        private final Button freigeben;
        private final Button close;

        ToolbarHorizontalLayout() {
            speichern = new Button("Speichern");
            speichern.setId("button-speichern");
            speichern.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            speichern.addClickListener(e -> speichern());

            freigeben = new Button("Freigabe beantragen");
            freigeben.setId("button-freigeben");
            freigeben.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
            freigeben.addClickListener(e -> {
                projektEntity.setStatusid(2);
                speichern();
                getUI().ifPresent(ui -> ui.navigate("projekte"));
            });

            close = new Button("Schließen");
            close.setId("button-close");
            close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
            close.addClickListener(e -> {
                getUI().ifPresent(ui -> ui.navigate("projekte"));
            });

            if (projektEntity.getStatusid() != 1) {
                freigeben.setEnabled(false);
                speichern.setEnabled(false);
            }

            this.add(speichern, freigeben, close);
            this.setId("button-toolbar");
            this.setWidthFull();
            this.setAlignItems(Alignment.END);
        }
    }

    private class KommentareVerticalLayout extends VerticalLayout {
        private final List<KommentarEntity> kommentare;

        KommentareVerticalLayout() {
            kommentare = kommentarService.findKommentareByProjektID(projektEntity.getId());
            if (kommentare.size() > 0) {
                Div div = new Div();
                div.setId("kommentar-div");
                div.setWidthFull();
                for (int i = 0; i < kommentare.size(); i++) {
                    div.add(new H4("Kommentar " + (i + 1)));
                    div.add(kommentare.get(i).getText());
                }
                this.add(div);
            }

            this.setWidthFull();
            this.setId("kommentar-layout");
        }
    }

    private class StatusToolbarHorizontalLayout extends HorizontalLayout {
        private final ArrayList<StatusEntity> stati;
        private final ComboBox<StatusEntity> statusEntityComboBox;
        private final Button save;

        StatusToolbarHorizontalLayout() {
            stati = new ArrayList<StatusEntity>();
            stati.add(statusService.get(1).get());
            stati.add(statusService.get(3).get());
            stati.add(statusService.get(4).get());

            statusEntityComboBox = new ComboBox<>();
            statusEntityComboBox.setItemLabelGenerator(StatusEntity::getBezeichnung);
            statusEntityComboBox.setItems(stati);

            save = new Button("Status Speichern");
            save.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
            save.addClickListener(buttonClickEvent -> {
                projektEntity.setStatusid(statusEntityComboBox.getValue().getId());
                speichern();
                this.setVisible(false);
                if (statusEntityComboBox.getValue().getId() != 3) {
                    new KommentarDialog().open();
                }
            });

            this.add(statusEntityComboBox, save);
            this.setWidthFull();
        }
    }

    private class KommentarDialog extends Dialog {

        private final TextArea kommentar;
        private final Button save;
        private Button exit;

        KommentarDialog() {
            kommentar = new TextArea();
            kommentar.setWidthFull();
            kommentar.setHelperText("Bitte geben sie einen Kommentar ein.");
            kommentar.setMinHeight("90%");

            save = new Button();
            save.setText("Abschicken");
            save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            save.addClickListener(buttonClickEvent -> {
                KommentarEntity kommentarEntity = new KommentarEntity();
                kommentarEntity.setProjektId(projektEntity.getId());
                kommentarEntity.setText(kommentar.getValue());
                kommentarService.save(kommentarEntity);
                this.close();
            });

            Button exit = new Button();
            exit.setText("Schließen");
            exit.addThemeVariants(ButtonVariant.LUMO_ERROR);
            exit.addClickListener(buttonClickEvent -> this.close());

            this.add(kommentar, new HorizontalLayout(save, exit));
        }
    }
}
