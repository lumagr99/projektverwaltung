package de.fhswf.projektantrag.views.utils;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import de.fhswf.projektantrag.data.entities.*;
import de.fhswf.projektantrag.data.service.*;
import de.fhswf.projektantrag.views.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

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
            List<String> vornamen = Arrays.asList("Amnesix", "Augenblix",
                    "Troubadix", "Berlix", "Asterix", "Obelix", "NullNullSix",
                    "Polemix", "Praktidfix", "Stupidix");

            for(String curr : vornamen){
                student(curr, service);
            }

            button.setEnabled(false);
        });
        return button;
    }

    private BenutzerEntity student(String vorname, BenutzerService service){

        BenutzerEntity benutzerEntity = new BenutzerEntity();
        benutzerEntity.setRolleId(1);
        benutzerEntity.setPasswort("test");
        benutzerEntity.setVorname(vorname);
        benutzerEntity.setNachname("Gallier");
        benutzerEntity.setBenutzername(benutzerEntity.getNachname()+
                benutzerEntity.getVorname());
        service.save(benutzerEntity);
        return benutzerEntity;
    }

    private Button createDozentTestData(BenutzerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            List<String> vornamen = Arrays.asList("Appelmus", "Hohlenus",
                    "Brutus");

            for(String curr : vornamen){
                dozent(curr, service);
            }

            button.setEnabled(false);
        });
        return button;
    }

    private BenutzerEntity dozent(String vorname, BenutzerService service){
        BenutzerEntity benutzerEntity;
        int count = 0;

        benutzerEntity = new BenutzerEntity();
        benutzerEntity.setRolleId(2);
        benutzerEntity.setPasswort("test");
        benutzerEntity.setVorname(vorname);
        benutzerEntity.setNachname("Römer");
        benutzerEntity.setBenutzername(benutzerEntity.getNachname()+
                benutzerEntity.getVorname());
        service.save(benutzerEntity);
        return benutzerEntity;
    }

    private Button createAnsprechpartnerTestData(BenutzerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            List<String> vornamen = Arrays.asList("Baba", "Erix", "Lyrik", "Rhetorik",
                    "Spürnix", "Stenograf", "Telegraf", "Theoretik");

            int count = 1;
            for(String curr : vornamen){
                ansprechpartner(curr, count, service);
                count += 1;
            }

            button.setEnabled(false);
        });
        return button;
    }

    private BenutzerEntity ansprechpartner(String vorname, int organisation, BenutzerService service){
        BenutzerEntity benutzerEntity;

        benutzerEntity = new BenutzerEntity();
        benutzerEntity.setRolleId(3);
        benutzerEntity.setPasswort("test");
        benutzerEntity.setVorname(vorname);
        benutzerEntity.setNachname("Völker");
        benutzerEntity.setOrganisationId(organisation);
        benutzerEntity.setBenutzername(benutzerEntity.getNachname()+
                benutzerEntity.getVorname());
        service.save(benutzerEntity);
        return benutzerEntity;
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
            List<String> titel = Arrays.asList(
                    "Asterix der Gallier",
                    "Der goldene Sichel",
                    "Asterix und die Goten",
                    "Asterix als Gladiator",
                    "Tour de France",
                    "Asterix und Kleopatra",
                    "Asterix bei den Briten",
                    "Asterix bei die Normannen",
                    "Asterix bei den Olympischen Spielen",
                    "Asterix in Spanien"
            );
            for(int i = 0; i<10; i++){
                ProjektEntity projektEntity = new ProjektEntity();
                projektEntity.setBeschreibung("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.\n" +
                        "Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.");
                projektEntity.setTitel(titel.get(i));
                projektEntity.setHintergrund("Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.");
                projektEntity.setSkizze("Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.");
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
            List<String> unternehmen = Arrays.asList(
                    "Obelix GmbH & Co. KG",
                    "FH SWF",
                    "TassenService.de",
                    "Roemer24",
                    "Xiaomi",
                    "IBM",
                    "McDonalds",
                    "BurgerKing",
                    "Casio",
                    "Adesso"
            );

            for(int i = 0; i<10; i++){
                OrganisationEntity organisationEntity = new OrganisationEntity();
                organisationEntity.setName(unternehmen.get(i));
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
            List<String> kommentare = Arrays.asList(
                    "Hier gibts keine zwei Dicken! Höchstens einen und der ist nicht dick!",
                    "Kommt zu den Legionären, hieß es, da erlebt ihr was, hieß es!",
                    "Alte Hiebe rosten nicht.",
                    "Ich kam, sah, siegte!",
                    "Sein oder nicht sein, dass ist hier die Frage!"
            );

            for(int i = 0; i<5; i++){
                KommentarEntity kommentarEntity = new KommentarEntity();
                kommentarEntity.setText(kommentare.get(i));
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
