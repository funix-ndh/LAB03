package vn.fpt.fsoft.hcd.g0;

public class ReportManager {
    private final View screen;
    private final Controller control;
    private final Model connector;

    // operator
    private static final int SEARCH_STUDENT = 1;
    private static final int LIST_STUDENT_BY_CLASS = 2;
    private static final int LIST_EXCELLENT_STUDENT = 3;
    private static final int LIST_LOW_GPA_STUDENT = 4;
    private static final int QUIT = 5;

    public ReportManager(final View screen, final Controller control, final Model connector) {
        this.screen = screen;
        this.control = control;
        this.connector = connector;
    }

    public void process() {
        screen.showReportMenu();
        final int studentChoice = control.getChoice();
        switch (studentChoice) {
            case SEARCH_STUDENT:
                searchStudent();
                break;
            case LIST_STUDENT_BY_CLASS:
                listStudentByClass();
                break;
            case LIST_EXCELLENT_STUDENT:
                listExcellentStudent();
                break;
            case LIST_LOW_GPA_STUDENT:
                listLowGPAStudent();
                break;
            case QUIT:
                System.exit(0);
        }
    }

    private void searchStudent() {
        screen.showSearchStudent();
        connector.searchStudent(control.getInput());
        View.showMessage(View.MSG3);
        control.waitKeyPress();
    }

    // NO REQUIREMENT / IMPLEMENT
    private void listStudentByClass() { }

    // NO REQUIREMENT / IMPLEMENT
    private void listExcellentStudent() { }

    // NO REQUIREMENT / IMPLEMENT
    private void listLowGPAStudent() { }
}
