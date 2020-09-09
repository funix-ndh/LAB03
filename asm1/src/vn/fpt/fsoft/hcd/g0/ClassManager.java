package vn.fpt.fsoft.hcd.g0;

public class ClassManager {
    private final View screen;
    private final Controller control;
    private final Model connector;

    // operator
    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;
    private static final int LIST = 4;
    private static final int QUIT = 5;

    public ClassManager(final View screen, final Controller control, final Model connector) {
        this.screen = screen;
        this.control = control;
        this.connector = connector;
    }

    public void process() {
        screen.showClassMenu();
        final int classChoice = control.getChoice();
        switch (classChoice) {
            case ADD:
                addNewClass();
                break;
            case UPDATE:
                updateClass();
                break;
            case DELETE:
                deleteClass();
                break;
            case LIST:
                listAllClass();
                break;
            case QUIT:
                System.exit(0);
        }
    }

    private void addNewClass() {
        final String classId;
        final String className;

        // validate exist class
        while (true) {
            screen.showAddClass();
            final String id = control.getInput();
            if (connector.isExistedClass(id)) {
                View.showMessage(View.MSG2);
            } else {
                classId = id;
                break;
            }
        }

        View.showMessage(View.MSG17);
        className = control.getInput();
        connector.addClassToFile(classId, className);
        View.showMessage(View.MSG3);
        control.waitKeyPress();
    }

    private void updateClass() {
        final String classId;
        final String className;

        while (true) {
            screen.showUpdateClass();
            final String id = control.getInput();
            if (!connector.isExistedClass(id)) {
                View.showMessage(View.MSG4);
            } else {
                classId = id;
                break;
            }
        }

        View.showMessage(View.MSG18);
        className = control.getInput();
        connector.renameClass(classId, className);
        View.showMessage(View.MSG3);
        control.waitKeyPress();
    }

    private void deleteClass() {
        final String classID;

        while (true) {
            screen.showDeleteClass();
            final String id = control.getInput();

            if (!connector.isExistedClass(id)) {
                View.showMessage(View.MSG4);
            } else if (!connector.isEmptyClass(id)) {
                View.showMessage(View.MSG5);
            } else {
                classID = id;
                break;
            }
        }

        if (control.getConfirm()) {
            connector.deleteClass(classID);
            View.showMessage(View.MSG3);
            control.waitKeyPress();
        }
    }

    private void listAllClass() {
        screen.showListAllClass();
        connector.listAllClass();
        View.showMessage(View.MSG3);
        control.waitKeyPress();
    }
}
