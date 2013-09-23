/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author ajmiro
 */
public class Company {
    private String logoPath,companyName;
    
    public void InitCompany(String companyID){        
        
        if (companyID != null) {
                    
            if ("oracle".equals(companyID.toLowerCase())) {
                logoPath = "Images/Oracle_Logo.png";
                companyName = "Oracle";
                return;
            }

            if ("linkedin".equals(companyID.toLowerCase())) {
                logoPath = "Images/linkedin 2.png";
                companyName = "LinkedIn";
                return;
            }
        }
        //logoPath = "Images/sugarcrm_logo.png";
        logoPath = "Images/InfoRelianceLogo.jpg";
        companyName = "InfoReliance";
    }

    /**
     * @return the logoPath
     */
    public String getLogoPath() {
        return logoPath;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
}
