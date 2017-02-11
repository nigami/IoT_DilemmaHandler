/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.ecosystem;

/**
 *
 * @author ilanigam17
 */
import business.Network.Network;
import business.Organization.Organization;
import business.Role.Role;
import business.UserAccount.UserAccountDirectory;
import java.util.ArrayList;

public class Ecosystem extends Organization {

    private static Ecosystem business;
    private ArrayList<Network> networkList;

    public static Ecosystem getInstance() {
        if (business == null) {
            business = new Ecosystem();
        }
        return business;
    }

    private Ecosystem() {
        super(null);
        this.networkList = new ArrayList();
    }

    public ArrayList<Network> getNetworkList() {
        return this.networkList;
    }

    public Network createAndAddNetwork() {
        Network network = new Network();
        this.networkList.add(network);
        return network;
    }

    public ArrayList<Role> getSupportedRole() {
        return this.roles;
    }

    public boolean checkIfUsernameIsUnique(String username) {
        if (!getUserAccountDirectory().checkIfUsernameIsUnique(username)) {
            return false;
        }
        return true;
    }
}
