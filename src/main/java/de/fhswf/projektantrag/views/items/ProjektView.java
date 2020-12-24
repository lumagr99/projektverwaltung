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
import de.fhswf.projektantrag.security.details.BenutzerUserDetails;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Generiert die Detailansicht eines Projekts für die spezifischen Benutzertypen.
 * @author Luca Graef
 * @date 20.12.2020
 */

@Route(value = "projekt", layout = MainView.class)
@PageTitle("Projekt | ProjektAntrag")
public class ProjektView extends VerticalLayout {

    @Autowired
    private ProjektService projektService;
    @Autowired
    private BenutzerService benutzerService;
    @Autowired
    private Benutzer2ProjektService benutzer2ProjektService;
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

    private final BenutzerUserDetails activeBenutzer;

    /**
     * Initialisierung der Daten.
     * @param projektService
     * @param benutzerService
     * @param benutzer2ProjektService
     * @param kommentarService
     * @param statusService
     */

    ProjektView(ProjektService projektService, BenutzerService benutzerService,
                Benutzer2ProjektService benutzer2ProjektService,
                KommentarService kommentarService, StatusService statusService) {
        setSizeFull();
        try {
            projektEntity = UI.getCurrent().getSession().getAttribute(ProjektEntity.class);
        } catch (Exception e) {
            projektEntity = null;
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        activeBenutzer = (BenutzerUserDetails) auth.getPrincipal();
    }

    /**
     * Generierung der Ansichten für die spezifischen Benutzerrollen mit erster Ausführung
     * von Datenbankoperationen.
     */
    @PostConstruct
    private void init() {
        createProjektEntityIfNotExist();
        textFields = new TextFieldsVerticalLayout();
        studenten = new StudentenHorizontalLayout();
        ansprechpartner = new AnsprechpartnerHorizontalLayout();
        kommentare = new KommentareVerticalLayout();
        toolbar = new ToolbarHorizontalLayout();
        statusToolbar = new StatusToolbarHorizontalLayout();

        //Alles
        /*this.add(textFields,
                new H3("Studenten"), studenten,
                new H3("Ansprechpartner"), ansprechpartner,
                kommentare,
                toolbar,
                statusToolbar);*/

        if(activeBenutzer.getRolle() == 1){
            this.add(textFields,
                    new H3("Studenten"), studenten,
                    new H3("Ansprechpartner"), ansprechpartner,
                    kommentare,
                    toolbar);
        }else if(activeBenutzer.getRolle() == 2){
            this.add(textFields,
                    new H3("Studenten"), studenten,
                    new H3("Ansprechpartner"), ansprechpartner,
                    kommentare,
                    statusToolbar);
        }else if(activeBenutzer.getRolle() == 3){
            this.add(textFields,
                    new H3("Studenten"), studenten,
                    new H3("Ansprechpartner"), ansprechpartner,
                    kommentare);
        }
    }

    /**
     * Erstellt ein neues ProjektEntity wenn keines vorhanden ist.
     */
    private void createProjektEntityIfNotExist() {
        if (projektEntity == null) {
            projektEntity = new ProjektEntity();
            projektEntity.setStatusid(1);
            projektEntity.setTitel(" ");
            projektEntity.setSkizze(" ");
            projektEntity.setHintergrund(" ");
            projektEntity.setBeschreibung(" ");
            projektService.save(projektEntity);

            Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
            benutzer2ProjektEntity.setProjektid(projektEntity.getId());
            benutzer2ProjektEntity.setBenutzerid(activeBenutzer.getId());
            benutzer2ProjektService.save(benutzer2ProjektEntity);
        }
    }

    /**
     * Speichert das aktuelle ProjektEnitity.
     */
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

    /**
     * Generiert die Ansicht der zugewiesenen Studenten.
     */
    private class StudentenHorizontalLayout extends HorizontalLayout {
        private List<Benutzer2ProjektEntity> benutzer2ProjektEntities;
        private ComboBox<BenutzerEntity> comboBox;
        private Button addStudent;


        StudentenHorizontalLayout() {

            List<Benutzer2ProjektEntity> helper = new ArrayList<Benutzer2ProjektEntity>();
            benutzer2ProjektEntities = benutzer2ProjektService.findBenutzer2ProjektByProjektID(projektEntity.getId());

            for(Benutzer2ProjektEntity benutzer2ProjektEntity : benutzer2ProjektEntities){
                BenutzerEntity benutzerEntity = benutzerService.get(benutzer2ProjektEntity.getBenutzerid()).get();
                if (benutzerEntity.getRolleId() == 1){
                    helper.add(benutzer2ProjektEntity);
                }
            }

            benutzer2ProjektEntities = helper;

            for (int i = 0; i < benutzer2ProjektEntities.size(); i++) {
                BenutzerEntity studentEntity = benutzerService.get(benutzer2ProjektEntities.get(i).getBenutzerid()).get();
                this.add(new StudentVerticalLayout(studentEntity));
            }

            if (this.getComponentCount() < 3 &&
                    projektEntity.getStatusid() == 1 &&
                        activeBenutzer.getRolle() == 1) {
                comboBox = new ComboBox<BenutzerEntity>();
                comboBox.setLabel("Student");
                //TODO Vor und Nachname?
                comboBox.setItemLabelGenerator(BenutzerEntity::getBenutzername);
                comboBox.setItems(benutzerService.findBenutzerEntitiesByRole(1));

                manageStudentAddButton();

                VerticalLayout verticalLayout = new VerticalLayout(comboBox, addStudent);
                verticalLayout.setWidth("33%");
                this.add(verticalLayout);
            }

            this.setWidthFull();
            this.setId("student-list");
        }

        /**
         * Verwaltet den Button neuen Studenten hinzufügen.
         */

        private void manageStudentAddButton() {
            addStudent = new Button("Student hinzufügen");
            addStudent.setId("add-student-button");
            addStudent.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

            addStudent.addClickListener(buttonClickEvent -> {
                boolean inList = false;

                for (int i = 0; i < benutzer2ProjektEntities.size(); i++) {
                    if (benutzer2ProjektEntities.get(i).getBenutzerid() == comboBox.getValue().getId()) {
                        inList = true;
                        break;
                    }
                }

                if (!inList) {
                    Benutzer2ProjektEntity student2ProjektEntity = new Benutzer2ProjektEntity();
                    student2ProjektEntity.setBenutzerid(comboBox.getValue().getId());
                    student2ProjektEntity.setProjektid(projektEntity.getId());
                    benutzer2ProjektService.save(student2ProjektEntity);
                    this.add(new StudentVerticalLayout(benutzerService.get(comboBox.getValue().getId()).get()));
                    comboBox.setVisible(false);
                    addStudent.setVisible(false);
                } else {
                    Notification notification = new Notification("Der Student existiert bereits!", 3000);
                    notification.open();
                }
            });
        }
    }

    /**
     * Legt einen neuen Studenteneintrag an.
     */
    private class StudentVerticalLayout extends VerticalLayout {
        H5 vorname;
        H5 nachname;

        BenutzerEntity benutzerEntity;

        StudentVerticalLayout(BenutzerEntity benutzerEntity) {
            if (benutzerEntity == null) {
                throw new IllegalArgumentException();
            }

            this.benutzerEntity = benutzerEntity;

            vorname = new H5(benutzerEntity.getVorname());
            nachname = new H5(benutzerEntity.getNachname());

            VerticalLayout layout = new VerticalLayout();
            layout.add(vorname, nachname);
            layout.setId("student-" + benutzerEntity.getNachname());
            layout.setWidth("33%");

            this.add(vorname, nachname);
        }

        public BenutzerEntity getBenutzerEntity() {
            return benutzerEntity;
        }
    }

    /**
     * Generiert die Ansicht des zugewiesenen Ansprechpartners.
     */
    private class AnsprechpartnerHorizontalLayout extends HorizontalLayout {
        private List<Benutzer2ProjektEntity> benutzer2ProjektEntities;
        private ComboBox<BenutzerEntity> comboBox;
        private Button addBenutzer;

        AnsprechpartnerHorizontalLayout() {
            List<Benutzer2ProjektEntity> helper = new ArrayList<Benutzer2ProjektEntity>();
            benutzer2ProjektEntities = benutzer2ProjektService.findBenutzer2ProjektByProjektID(projektEntity.getId());

            for (Benutzer2ProjektEntity benutzer2Projekt : benutzer2ProjektEntities){
                BenutzerEntity benutzerEntity = benutzerService.get(benutzer2Projekt.getBenutzerid()).get();
                if(benutzerEntity.getRolleId() == 3){
                    helper.add(benutzer2Projekt);
                }
            }

            benutzer2ProjektEntities = helper;


            if (benutzer2ProjektEntities.size() == 1) {
                BenutzerEntity benutzerEntity =
                        benutzerService.get(benutzer2ProjektEntities.get(0).getBenutzerid()).get();
                this.add(new AnsprechpartnerVerticalLayout(benutzerEntity));
            } else if (benutzer2ProjektEntities.size() == 0 &&
                            projektEntity.getStatusid() == 1 &&
                                activeBenutzer.getRolle() == 1) {
                addBenutzer = new Button("Ansprechpartner hinzufügen");
                addBenutzer.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
                addBenutzer.setId("add-ansprechpartner-button");
                comboBox = new ComboBox<BenutzerEntity>();

                //TODO Vor und Nachname?
                comboBox.setItemLabelGenerator(BenutzerEntity::getNachname);
                comboBox.setItems(benutzerService.findBenutzerEntitiesByRole(3));

                addBenutzer.addClickListener(buttonClickEvent -> {
                    manageAnsprechpartnerAddButton();
                });

                this.add(new VerticalLayout(comboBox, addBenutzer));
            }

            this.setWidthFull();
            this.setId("ansprechpartner-layout");
        }

        /**
         * Verwaltet den Ansprechpartner hinzufügen Knopf.
         */
        private void manageAnsprechpartnerAddButton() {
            Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
            benutzer2ProjektEntity.setBenutzerid(comboBox.getValue().getId());
            benutzer2ProjektEntity.setProjektid(projektEntity.getId());
            benutzer2ProjektService.save(benutzer2ProjektEntity);
            this.add(new AnsprechpartnerVerticalLayout(comboBox.getValue()));
            comboBox.setVisible(false);
            addBenutzer.setVisible(false);
        }
    }

    /**
     * Generiert die Ansicht eines Ansprechpartners.
     */
    private class AnsprechpartnerVerticalLayout extends VerticalLayout {
        BenutzerEntity ansprechpartnerEntity;

        AnsprechpartnerVerticalLayout(BenutzerEntity benutzerEntity) {
            if (benutzerEntity == null) {
                throw new IllegalArgumentException();
            }
            this.ansprechpartnerEntity = benutzerEntity;
            H5 vorname = new H5(benutzerEntity.getVorname());
            H5 nachname = new H5(benutzerEntity.getNachname());
            H6 organisation = new H6(organisationService.get(benutzerEntity.getOrganisationId()).get().getName());

            this.setId("ansprechpartner-" + benutzerEntity.getNachname());
            this.setWidthFull();
            this.add(vorname, nachname, organisation);
        }
    }

    /**
     * Generiert die Textfelder mit Titel, Skizze, Beschreibung des Projekthintergrunds, Beschreibung.
     */
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

            if (projektEntity.getStatusid() != 1 ||
                    !(activeBenutzer.getRolle() == 1)) {
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

    /**
     * Generiert die Toolbar für die Studentenansicht mit den Funktionen Speichern, Freigeben, Schließen.
     */
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

    /**
     * Generiert die Ansicht der abgegebenen Kommentare.
     */
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

    /**
     * Generiert die Toolbar für den Dozenten.
     * Der Status kann geändert werden und das Projekt geschlossen werden.
     */
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
