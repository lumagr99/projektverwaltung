package de.fhswf.projektantrag.views.main;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import de.fhswf.projektantrag.views.items.ProjektView;
import de.fhswf.projektantrag.views.lists.DozentList;
import de.fhswf.projektantrag.views.lists.OrganisationList;
import de.fhswf.projektantrag.views.lists.ProjektList;
import de.fhswf.projektantrag.views.lists.StudentList;

import java.util.Optional;

/**
 * The main view is a top-level placeholder for other views.
 */
@JsModule("./styles/shared-styles.js")
@CssImport("./styles/views/main/main-view.css")
@PWA(name = "ProjektAntrag", shortName = "ProjektAntrag", enableInstallPrompt = false)
@Route("")
public class MainView extends AppLayout {

    private final Tabs menu;
    private H1 viewTitle = new H1("ProjektAntrag");

    /**
     * Initiale einstellungen
     */
    public MainView() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        menu = createMenu();
        addToDrawer(createDrawerContent(menu));
    }

    /**
     * Erstellt den Header mit Logout, ...
     * @return
     */
    private Component createHeaderContent() {
        Anchor logout = new Anchor("logout", "Log out");
        logout.setId("logout");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.getThemeList().set("dark", true);
        layout.setWidthFull();
        layout.setSpacing(false);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.add(new DrawerToggle());
        viewTitle = new H1();
        layout.add(viewTitle,logout);
        layout.add(new Image("images/user.svg", "Avatar"));
        return layout;
    }

    /**
     * Erstellt das Linke Menu
     * @param menu
     * @return
     */
    private Component createDrawerContent(Tabs menu) {
        VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.getThemeList().set("spacing-s", true);
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        HorizontalLayout logoLayout = new HorizontalLayout();
        logoLayout.setId("logo");
        logoLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        logoLayout.add(new Image("images/logo.png", "ProjektAntrag logo"));
        logoLayout.add(new H1("ProjektAntrag"));
        layout.add(logoLayout, menu);
        return layout;
    }

    /**
     * Fügt das Menu hinzu
     * @return
     */
    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.addThemeVariants(TabsVariant.LUMO_MINIMAL);
        tabs.setId("tabs");
        tabs.add(createMenuItems());
        return tabs;
    }

    /**
     * Erstellt die Menu Items.
     * @return
     */
    private Component[] createMenuItems() {
        Tab tab1 = createTab("Projekte", ProjektList.class);
        Tab tab2 = createTab("Organisationen", OrganisationList.class);
        Tab tab3 = createTab("Aktuelles Projekt", ProjektView.class);
        Tab tab4 = createTab("Studenten", StudentList.class);
        Tab tab5 = createTab("Dozenten", DozentList.class);
        return new Tab[]{tab1, tab2, tab3, tab4, tab5};
    }

    /**
     * Erstelltl einen neuen Menu Tab.
     * @param text
     * @param navigationTarget
     * @return
     */
    private static Tab createTab(String text, Class<? extends Component> navigationTarget) {
        final Tab tab = new Tab();
        tab.add(new RouterLink(text, navigationTarget));


        ComponentUtil.setData(tab, Class.class, navigationTarget);


        return tab;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        getTabForComponent(getContent()).ifPresent(menu::setSelectedTab);
        viewTitle.setText(getCurrentPageTitle());
    }

    private Optional<Tab> getTabForComponent(Component component) {
        return menu.getChildren().filter(tab -> ComponentUtil.getData(tab, Class.class).equals(component.getClass()))
                .findFirst().map(Tab.class::cast);
    }

    private String getCurrentPageTitle() {
        return getContent().getClass().getAnnotation(PageTitle.class).value();
    }
}
