package vn.fpt.fsoft.hcd.g0;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class MainProgram {

    private static final View screen = new View();
    private static final Controller control = new Controller();
    private static final Model connector = new Model();

    private static final ClassManager classManager = new ClassManager(screen, control, connector);
    private static final SubjectManager subjectManager = new SubjectManager(screen, control, connector);
    private static final StudentManager studentManager = new StudentManager(screen, control, connector);
    private static final ReportManager reportManager = new ReportManager(screen, control, connector);

    // menu
    private static final int CLASS_MANAGER = 1;
    private static final int SUBJECT_MANAGER = 2;
    private static final int STUDENT_MANAGER = 3;
    private static final int SHOW_REPORT = 4;
    private static final int QUIT = 5;

    public static void main(final String[] args) throws IOException {
        final FileHandler fileHandler = new FileHandler("error_log.txt");
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getGlobal().addHandler(fileHandler);
        Logger.getGlobal().setLevel(Level.SEVERE);
        Logger.getGlobal().setUseParentHandlers(false); // prevent console output

        startUp();
    }

    private static void startUp() {
        while (true) {
            screen.showMainMenu();
            final int choice = control.getChoice();
            switch (choice) {
                case CLASS_MANAGER:
                    classManager.process();
                    break;
                case SUBJECT_MANAGER:
                    subjectManager.process();
                    break;
                case STUDENT_MANAGER:
                    studentManager.process();
                    break;
                case SHOW_REPORT:
                    reportManager.process();
                    break;
                case QUIT:
                    System.exit(0);
            }
        }
    }

    private MainProgram() {}
}
