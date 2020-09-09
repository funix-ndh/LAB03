package vn.fpt.fsoft.hcd.g0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Model {

    private static final String CLASS_FILE_PATH = "Data/class.txt";
    private static final String STUDENT_FILE_PATH = "Data/student.txt";
    private static final String SUBJECT_FILE_PATH = "Data/subject.txt";
    private static final String GRADE_FILE_PATH = "Data/grade.txt";
    private static final String UNDERSCORE = "_";
    private static final String SPACE = " ";

    // class
    private static final int CLASS_ID_INDEX = 0;

    // student
    private static final int STUDENT_ID_INDEX = 0;
    private static final int STUDENT_CLASS_ID_INDEX = 5;

    // subject
    private static final int SUBJECT_ID_INDEX = 0;

    // grade
    private static final int GRADE_STUDENT_ID_INDEX = 0;
    private static final int GRADE_SUBJECT_ID_INDEX = 1;

    // check if existed class
    public boolean isExistedClass(final String classId) {
        try (final BufferedReader br = new BufferedReader(new FileReader(CLASS_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                final String[] dataSplit = line.split(UNDERSCORE);
                if (classId.toLowerCase().equals(dataSplit[CLASS_ID_INDEX].toLowerCase())) {
                    return true;
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
        return false;
    }

    // check is class is empty student
    public boolean isEmptyClass(final String classId) {
        try (final BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                final String[] dataSplit = line.split(UNDERSCORE);
                if (classId.toLowerCase().equals(dataSplit[STUDENT_CLASS_ID_INDEX].toLowerCase())) {
                    return false;
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
        return true;
    }

    // check if existed student
    public boolean isExistedStudent(final String studentId) {
        try (final BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                final String[] dataSplit = line.split(UNDERSCORE);
                if (studentId.toLowerCase().equals(dataSplit[STUDENT_ID_INDEX].toLowerCase())) {
                    return true;
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
        return false;
    }

    // check if existed subject
    public boolean isExistedSubject(final String subjectId) {
        try (final BufferedReader br = new BufferedReader(new FileReader(SUBJECT_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                final String[] dataSplit = line.split(UNDERSCORE);
                if (subjectId.toLowerCase().equals(dataSplit[SUBJECT_ID_INDEX].toLowerCase())) {
                    return true;
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
        return false;
    }

    // check if exist grade
    public boolean isExistedGrade(final String studentID, final String subjectID) {
        try (final BufferedReader br = new BufferedReader(new FileReader(GRADE_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                final String[] dataSplit = line.split(UNDERSCORE);
                if (studentID.toLowerCase().equals(dataSplit[GRADE_STUDENT_ID_INDEX].toLowerCase())
                    && subjectID.toLowerCase().equals(dataSplit[GRADE_SUBJECT_ID_INDEX].toLowerCase())) {
                    return true;
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
        return false;
    }

    // append new class to end of file
    public void addClassToFile(final String id, final String name) {
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(CLASS_FILE_PATH, true))) {
            bw.append(id.toUpperCase() + UNDERSCORE + name.toUpperCase() + '\n');
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }

    // rename class and update to file
    public void renameClass(final String id, final String name) {
        try (final BufferedReader br = new BufferedReader(new FileReader(CLASS_FILE_PATH))) {
            final List<String> lineList = new ArrayList<>();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                lineList.add(line);
            }
            try (final PrintWriter pr = new PrintWriter(new FileWriter(CLASS_FILE_PATH))) {
                for (int i = 0; i < lineList.size(); i++) {
                    final String line = lineList.get(i);
                    final String[] dataSplit = line.split(UNDERSCORE);
                    if (dataSplit[CLASS_ID_INDEX].toLowerCase().equals(id.toLowerCase())) {
                        pr.println(id.toUpperCase() + UNDERSCORE + name.toUpperCase());
                    } else {
                        pr.println(line);
                    }
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }

    // delete class from file
    public void deleteClass(final String id) {
        try (final BufferedReader br = new BufferedReader(new FileReader(CLASS_FILE_PATH))) {
            final List<String> lineList = new ArrayList<>();
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                lineList.add(line);
            }
            try (final PrintWriter pr = new PrintWriter(CLASS_FILE_PATH)) {
                for (int i = 0; i < lineList.size(); i++) {
                    final String line = lineList.get(i);
                    final String[] dataSplit = line.split(UNDERSCORE);
                    if (dataSplit[CLASS_ID_INDEX].toLowerCase().equals(id.toLowerCase())) {
                        continue;
                    }
                    pr.println(line);
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }

    // list all class
    public void listAllClass() {
        try (final BufferedReader br = new BufferedReader(new FileReader(CLASS_FILE_PATH))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                View.showMessage(String.join(SPACE, line.split(UNDERSCORE)) + '\n');
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }

    // search student by keyword
    public void searchStudent(final String keyword) {
        try (final BufferedReader br = new BufferedReader(new FileReader(STUDENT_FILE_PATH))) {
            View.showMessage("Result: \n");
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                if (line.toLowerCase().contains(keyword.toLowerCase())) {
                    View.showMessage(String.join(SPACE, line.split(UNDERSCORE)) + '\n');
                }
            }
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }

    // add grade to file
    public void addGradeToFile(final String studentID, final String subjectID, final int grade) {
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter(GRADE_FILE_PATH, true))) {
            bw.append(
                    studentID.toUpperCase() + UNDERSCORE + subjectID.toUpperCase() + UNDERSCORE + grade + '\n'
            );
        } catch (final Exception e) {
            ErrorLogger.fatal(Logger.getGlobal(), e);
        }
    }
}
