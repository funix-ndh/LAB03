package vn.fpt.fsoft.hcd.g0;

import java.util.Scanner;
import java.util.logging.Logger;

public class Controller {

    private static final Scanner scanner = new Scanner(System.in);

    // get choice from user
    // retry until input is corrected
    public int getChoice() {
        View.showMessage(View.MSG14);
        while (true) {
            try {
                final int choice = Integer.parseInt(getInput());

                // only allow 1 - 5
                if (choice > 0 && choice < 6) {
                    return choice;
                }
                View.showMessage(View.MSG10);
            } catch (final Exception e) {
                ErrorLogger.log(Logger.getGlobal(), e);
                View.showMessage(View.MSG13);
            }
        }
    }

    // get input from user
    // retry until input is corrected
    public String getInput() {
        while (true) {
            try {
                final String input = scanner.nextLine().trim();
                if (!input.isEmpty()) {
                    return input;
                }
            } catch (final Exception e) {
                ErrorLogger.log(Logger.getGlobal(), e);
            }
            View.showMessage(View.MSG1);
        }
    }

    // get confirm from user
    // retry until input is corrected
    public boolean getConfirm() {
        View.showMessage(View.MSG15);
        while (true) {
            final String input = getInput();
            if ("y".equals(input.toLowerCase())) {
                return true;
            }
            if ("n".equals(input.toLowerCase())) {
                return false;
            }
            View.showMessage(View.MSG10);
        }
    }

    // wait and get any input from user
    public void waitKeyPress() {
        try {
            scanner.nextLine();
        } catch (final Exception e) {
            ErrorLogger.log(Logger.getGlobal(), e);
        }
    }

    // get grade from user
    // retry until input is corrected
    public int getGrade() {
        View.showMessage(View.MSG16);
        while (true) {
            try {
                final int grade = Integer.parseInt(getInput());

                // only allow 1 - 10
                if (grade > 0 && grade < 11) {
                    return grade;
                }
                View.showMessage(View.MSG11);
            } catch (final Exception e) {
                ErrorLogger.log(Logger.getGlobal(), e);
                View.showMessage(View.MSG13);
            }
        }
    }
}
