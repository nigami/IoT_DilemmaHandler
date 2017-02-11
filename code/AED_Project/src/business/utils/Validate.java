/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.utils;

import javax.swing.JOptionPane;

/**
 *
 * @author ilanigam17
 */
public class Validate {

    public static Boolean ValidateOnlyAlphaSpace(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (!(nameJTextField.trim()).matches("[a-zA-Z ]*")) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return validity;
    }

    public static String ValidateIsEmpty(String nameJTextField, String labelName) {
        Boolean validity = true;
        if (nameJTextField.isEmpty()) {
            validity = false;
            JOptionPane.showMessageDialog(null, labelName + "is manadatory!", "Error", JOptionPane.ERROR_MESSAGE);
            return labelName + "is manadatory!";
        }

        return null;
    }

    public static Boolean ValidateEmpty(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (nameJTextField.isEmpty()) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return validity;
    }

    public static Boolean ValidateEmail(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (!(nameJTextField.trim()).matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return validity;
    }

    public static Boolean ValidateAlphaNumberic(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (!(nameJTextField.trim()).matches("[a-zA-Z0-9 ]*")) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        return validity;
    }

    public static Boolean ValidateNumberic(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (!(nameJTextField.trim()).matches("[0-9]*")) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return validity;
    }

    public static Boolean ValidateFloat(String nameJTextField, String errMsg) {
        Boolean validity = true;
        if (!(nameJTextField.trim()).matches("[0-9.]*")) {
            validity = false;
            if (errMsg != null && !errMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, errMsg, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return validity;
    }

    public static String passwordStrengthCheck(String username, String pwd) {
        pwd = pwd.trim();
        username = username.trim();
        if ((pwd).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}$") && !username.contains(pwd)) {
            return "100";
        } else if ((pwd).matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$") && !username.contains(pwd)) {
            return "75";
        } else if ((pwd).matches("^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,}$") && !username.contains(pwd)) {
            return "50";
        } else if ((pwd).matches("^(?=.*[a-z])(?=\\S+$).{6,}$") && !username.contains(pwd)) {
            return "25";
        } else if ((pwd).matches("^(?=.*[a-z])(?=\\S+$).{6,}$") && username.contains(pwd) && pwd.length() != username.length()) {
            return "10";
        } else if (username.contains(pwd) && pwd.length() != username.length()) {
            return "0";
        }
        return "5";
    }

}
