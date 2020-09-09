package vn.fpt.fsoft.hcd.g0;

public class SubjectManager {
    private final View screen;
    private final Controller control;
    private final Model connector;

    // operator
    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;
    private static final int LIST = 4;
    private static final int QUIT = 5;

    public SubjectManager(final View screen, final Controller control, final Model connector) {
        this.screen = screen;
        this.control = control;
        this.connector = connector;
    }

    public void process() {
        screen.showSubjectMenu();
        final int subjectChoice = control.getChoice();
        switch (subjectChoice) {
            case ADD:
                addNewSubject();
                break;
            case UPDATE:
                updateSubject();
                break;
            case DELETE:
                deleteSubject();
                break;
            case LIST:
                listAllSubject();
                break;
            case QUIT:
                System.exit(0);
        }
    }

    // NO REQUIREMENT / IMPLEMENT
    private static void addNewSubject() { }

    // NO REQUIREMENT / IMPLEMENT
    private static void updateSubject() { }

    // NO REQUIREMENT / IMPLEMENT
    private static void deleteSubject() { }

    // NO REQUIREMENT / IMPLEMENT
    private static void listAllSubject() { }
}
