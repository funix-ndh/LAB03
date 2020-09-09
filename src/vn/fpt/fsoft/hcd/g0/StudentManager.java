package vn.fpt.fsoft.hcd.g0;

public class StudentManager {
    private final View screen;
    private final Controller control;
    private final Model connector;

    // operator
    private static final int ADD = 1;
    private static final int UPDATE = 2;
    private static final int DELETE = 3;
    private static final int INPUT_GRADE = 4;
    private static final int QUIT = 5;

    public StudentManager(final View screen, final Controller control, final Model connector) {
        this.screen = screen;
        this.control = control;
        this.connector = connector;
    }

    public void process() {
        screen.showStudentMenu();
        final int studentChoice = control.getChoice();
        switch (studentChoice) {
            case ADD:
                addNewStudent();
                break;
            case UPDATE:
                updateStudent();
                break;
            case DELETE:
                deleteStudent();
                break;
            case INPUT_GRADE:
                inputStudentGrade();
                break;
            case QUIT:
                System.exit(0);
        }
    }

    // NO REQUIREMENT / IMPLEMENT
    private static void addNewStudent() { }

    // NO REQUIREMENT / IMPLEMENT
    private static void updateStudent() { }

    // NO REQUIREMENT / IMPLEMENT
    private static void deleteStudent() { }

    private void inputStudentGrade() {
        screen.showInputStudentGrade();

        while (true) {
            final String studentId;
            final String subjectId;
            final int grade;

            while (true) {
                View.showMessage("Select Student: ");
                final String studentIdTemp = control.getInput();
                if (!connector.isExistedStudent(studentIdTemp)) {
                    View.showMessage(View.MSG9);
                } else {
                    studentId = studentIdTemp;
                    break;
                }
            }

            while (true) {
                View.showMessage("Select subject: ");
                final String subjectIdTemp = control.getInput();
                if (!connector.isExistedSubject(subjectIdTemp)) {
                    View.showMessage(View.MSG7);
                } else {
                    subjectId = subjectIdTemp;
                    break;
                }
            }

            if (connector.isExistedGrade(studentId, subjectId)) {
                View.showMessage(View.MSG12);
            } else {
                grade = control.getGrade();
                connector.addGradeToFile(studentId, subjectId, grade);
                View.showMessage(View.MSG3);
                control.waitKeyPress();
                break;
            }
        }
    }
}
