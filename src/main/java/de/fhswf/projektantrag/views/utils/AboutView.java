package de.fhswf.projektantrag.views.utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.*;
import de.fhswf.projektantrag.data.service.*;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    @Autowired
    private BenutzerService benutzerService;
    @Autowired
    private ProjektService projektService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private KommentarService kommentarService;
    @Autowired
    private Benutzer2ProjektService benutzer2ProjektService;
    @Autowired
    private OrganisationService organisationService;
    @Autowired
    private RollenService rollenService;

    public AboutView(BenutzerService benutzerService, ProjektService projektService, StatusService statusService,
                     KommentarService kommentarService, Benutzer2ProjektService benutzer2ProjektService,
                     OrganisationService organisationService, RollenService rollenService) {
        setId("about-view");

        Button studentTestData = createStudentTestData(benutzerService, "Student");
        Button statusTestData = createStatusTestData(statusService, "Status");
        Button projektTestData = createProjektTestData(projektService, "Projekt");
        Button kommentarTestData = createKommentarTestData(kommentarService, "Kommentar");
        Button benutzer2ProjektTestData = createBenutzer2ProjektTestData(benutzer2ProjektService, "Benutzer2Projekt");
        Button organsisationTestData = createOrganisationTestData(organisationService, "Organisation");
        Button dozentTestData = createDozentTestData(benutzerService, "Dozent");
        Button ansprechpartnerTestData = createAnsprechpartnerTestData(benutzerService, "Ansprechpartner");
        Button rollenTestData = createRollenTestData(rollenService, "Rollen");

        add(rollenTestData, statusTestData, organsisationTestData,
                studentTestData, dozentTestData, ansprechpartnerTestData,
                projektTestData, kommentarTestData, benutzer2ProjektTestData);
        }

    private Button createStudentTestData(BenutzerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 1; i<11; i++){
                BenutzerEntity benutzer = new BenutzerEntity();
                benutzer.setVorname("Hermann"+i);
                benutzer.setNachname("Meier"+i);
                benutzer.setBenutzername("HermannMeier"+i);
                benutzer.setPasswort("1234"+i);
                benutzer.setRolleId(1);
                service.save(benutzer);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createDozentTestData(BenutzerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 1; i<11; i++){
                BenutzerEntity benutzer = new BenutzerEntity();
                benutzer.setVorname("Herfrau"+i);
                benutzer.setNachname("Mauer"+i);
                benutzer.setBenutzername("HerfrauMauer"+i);
                benutzer.setPasswort("test"+i);
                benutzer.setRolleId(2);
                service.save(benutzer);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createAnsprechpartnerTestData(BenutzerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 1; i<11; i++){
                BenutzerEntity ansprechpartner = new BenutzerEntity();
                ansprechpartner.setVorname("Ferdinand"+i);
                ansprechpartner.setNachname("Klaus"+i);
                ansprechpartner.setBenutzername("Ferdinand"+i);
                ansprechpartner.setPasswort("test"+i);
                ansprechpartner.setOrganisationId(i+1);
                ansprechpartner.setRolleId(3);
                service.save(ansprechpartner);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createStatusTestData(StatusService service, String name){
        Button button = new Button("Import " + name);
        button.setId("import-button");
        button.addClickListener(e -> {
            StatusEntity bearbeitung = new StatusEntity();
            bearbeitung.setBezeichnung("Bearbeitung");
            bearbeitung.setId(1);
            service.save(bearbeitung);

            StatusEntity wartet = new StatusEntity();
            wartet.setBezeichnung("Wartet");
            wartet.setId(2);
            service.save(wartet);

            StatusEntity freigegeben = new StatusEntity();
            freigegeben.setBezeichnung("Freigegeben");
            freigegeben.setId(3);
            service.save(freigegeben);

            StatusEntity abgelehnt = new StatusEntity();
            abgelehnt.setBezeichnung("Abgelehnt");
            abgelehnt.setId(4);
            service.save(abgelehnt);

            button.setEnabled(false);
        });
        return button;
    }

    private Button createProjektTestData(ProjektService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                ProjektEntity projektEntity = new ProjektEntity();
                projektEntity.setBeschreibung("moin"+i);
                projektEntity.setTitel("Titel"+i);
                projektEntity.setHintergrund("hintergrund"+i);
                projektEntity.setSkizze("Skizze"+i);
                projektEntity.setStatusid((i%4)+1);
                service.save(projektEntity);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createOrganisationTestData(OrganisationService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                OrganisationEntity organisationEntity = new OrganisationEntity();
                organisationEntity.setName("Obelix GmbH & co. kg");
                service.save(organisationEntity);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createKommentarTestData(KommentarService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                KommentarEntity kommentarEntity = new KommentarEntity();
                kommentarEntity.setText("Kommentiert " + i);
                kommentarEntity.setProjektId(i+1);
                service.save(kommentarEntity);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createBenutzer2ProjektTestData(Benutzer2ProjektService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 1; i<31; i++){
                if(i < 12|| i > 21){
                    Benutzer2ProjektEntity benutzer2ProjektEntity = new Benutzer2ProjektEntity();
                    benutzer2ProjektEntity.setBenutzerid(i+1);
                    benutzer2ProjektEntity.setProjektid((i%10)+1);
                    service.save(benutzer2ProjektEntity);
                }
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createRollenTestData(RollenService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            RollenEntity rollenEntity = new RollenEntity();
            rollenEntity.setBezeichnung("Student");
            service.save(rollenEntity);

            rollenEntity = new RollenEntity();
            rollenEntity.setBezeichnung("Dozent");
            service.save(rollenEntity);

            rollenEntity = new RollenEntity();
            rollenEntity.setBezeichnung("Ansprechpartner");
            service.save(rollenEntity);
            button.setEnabled(false);
        });
        return button;
    }

}
