package de.fhswf.projektantrag.views.about;

import com.vaadin.flow.component.Text;
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
    private StudentService studentService;
    @Autowired
    private ProjektService projektService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private DozentService dozentService;
    @Autowired
    private AnsprechpartnerService ansprechpartnerService;
    @Autowired
    private KommentarService kommentarService;
    @Autowired
    private Ansprechpartner2ProjektService ansprechpartner2ProjektService;
    @Autowired
    private Student2ProjektService student2ProjektService;
    @Autowired
    private OrganisationService organisationService;

    public AboutView(StudentService studentService, ProjektService projektService, StatusService statusService,
                     DozentService dozentService, AnsprechpartnerService ansprechpartnerService,
                     KommentarService kommentarService, Ansprechpartner2ProjektService ansprechpartner2ProjektService,
                     Student2ProjektService student2ProjektService, OrganisationService organisationService) {
        setId("about-view");

        Button studentTestData = createStudentTestData(studentService, "Student");
        Button statusTestData = createStatusTestData(statusService, "Status");
        Button projektTestData = createProjektTestData(projektService, "Projekt");
        Button dozentTestData = createDozentTestData(dozentService, "Dozent");
        Button ansprechpartnerTestData = createAnsprechpartnerTestData(ansprechpartnerService, "Ansprechpartner");
        Button kommentarTestData = createKommentarTestData(kommentarService, "Kommentar");
        Button ansprechpartner2ProjektTestData = createAnsprechpartner2ProjektTestData(ansprechpartner2ProjektService, "Ansprechpartner2Projekt");
        Button student2ProjektTestData = createStudent2ProjektTestData(student2ProjektService, "Student2Projekt");
        Button organsisationTestData = createOrganisationTestData(organisationService, "Organisation");



        add(new Text("Content placeholder"), studentTestData, statusTestData, projektTestData,
                dozentTestData, ansprechpartnerTestData, kommentarTestData,
                ansprechpartner2ProjektTestData, student2ProjektTestData,
                organsisationTestData);
        }

    private Button createStudentTestData(StudentService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                StudentEntity student = new StudentEntity();
                student.setVorname("Hermann"+i);
                student.setNachname("Meier"+i);
                student.setBenutzername("HermannMeier"+i);
                student.setPasswort("1234"+i);
                service.save(student);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createDozentTestData(DozentService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                DozentEntity dozent = new DozentEntity();
                dozent.setVorname("Herfrau"+i);
                dozent.setNachname("Mauer"+i);
                dozent.setBenutzername("HerfrauMauer"+i);
                dozent.setPassword("test"+i);
                service.save(dozent);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createAnsprechpartnerTestData(AnsprechpartnerService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                AnsprechpartnerEntity ansprechpartner = new AnsprechpartnerEntity();
                ansprechpartner.setVorname("Herfrau"+i);
                ansprechpartner.setNachname("Mauer"+i);
                ansprechpartner.setBenutzername("HerfrauMauer"+i);
                ansprechpartner.setPasswort("test"+i);
                ansprechpartner.setOrganisation(i+1);
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

    private Button createAnsprechpartner2ProjektTestData(Ansprechpartner2ProjektService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<5; i++){
                Ansprechpartner2ProjektEntity ansprechpartner2ProjektEntity = new Ansprechpartner2ProjektEntity();
                ansprechpartner2ProjektEntity.setAnsprechpartnerid((i%10)+1);
                ansprechpartner2ProjektEntity.setProjektId((i%10)+1);
                service.save(ansprechpartner2ProjektEntity);
            }
            button.setEnabled(false);
        });
        return button;
    }

    private Button createStudent2ProjektTestData(Student2ProjektService service, String name){
        Button button = new Button("Import "+name);
        button.setId("import-button-"+name);
        button.addClickListener(e -> {
            for(int i = 0; i<10; i++){
                Student2ProjektEntity student2ProjektEntity = new Student2ProjektEntity();
                student2ProjektEntity.setStudentId((i%4)+1);
                student2ProjektEntity.setProjektId((i%5)+1);
                service.save(student2ProjektEntity);
            }
            button.setEnabled(false);
        });
        return button;
    }

}
