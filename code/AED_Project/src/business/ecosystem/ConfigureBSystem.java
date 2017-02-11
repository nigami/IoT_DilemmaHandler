package business.ecosystem;

import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.Organization;
import business.Role.SystemAdminRole;
import business.UserAccount.UserAccount;
import business.person.Person;

/**
 *
 * @author rrheg
 */
public class ConfigureBSystem {

    public static Ecosystem configure() {
        //   System.out.println("inside myconfigureB");
        Ecosystem system = Ecosystem.getInstance();

        //Create a network
        Network nw = system.createAndAddNetwork();

        nw.setName("USA");
        //create an enterprise
        nw.getEnterpriseDirectory().createAndAddEnterprise("USA COMMUNITY", Enterprise.EnterpriseType.COMMUNITY);
        nw.getEnterpriseDirectory().createAndAddEnterprise("USA FINANCIAL", Enterprise.EnterpriseType.FINANCE);
        //initialize some organizations
        for (Enterprise e : nw.getEnterpriseDirectory().getEnterpriseList()) {
            if (e.getName() == "USA COMMUNITY") {
                e.getOrganizationDirectory().createOrganization(Organization.CommunityOrgType.Doctor);
                e.getOrganizationDirectory().createOrganization(Organization.CommunityOrgType.Teacher);
            }
        }
        //have some employees 
        //create user account

        Person person = system.getPersonDirectory().addPerson();
        person.setName("RRH");
        UserAccount ua = system.getUserAccountDirectory().createUserAccount("sysadmin", "sysadmin", person, new SystemAdminRole());

        return system;
    }

}
