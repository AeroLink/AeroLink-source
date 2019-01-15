/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXMLS.FINANCIAL.CLASSFILES;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Gilbert
 */
public class FINANCIAL_DVR_CLASSFILE {
     private SimpleStringProperty dvrID; 
        public SimpleStringProperty dvrDatereq;  
        public SimpleStringProperty dvrRequestor;
        public SimpleStringProperty dvrAmount;  
        public SimpleStringProperty dvrDepartment;
        public SimpleStringProperty dvrVt;  
        public SimpleStringProperty dvrClaimant;
        public SimpleStringProperty dvrOrganization;  
        public SimpleStringProperty dvrDateRelease;
        
       
    
        
     public FINANCIAL_DVR_CLASSFILE(String dvr_id,
             String dvr_datereq,
             String dvr_req,
             String dvr_amnt,
             String dvr_deprtmnt,
             String dvr_vt,
             String dvr_clmnt,
             String dvr_org,
             String dvr_rel){
         
            this.dvrID = new SimpleStringProperty(dvr_id);
            this.dvrDatereq = new SimpleStringProperty(dvr_datereq);
            this.dvrRequestor = new SimpleStringProperty(dvr_req);
            this.dvrAmount = new SimpleStringProperty(dvr_amnt);
            this.dvrDepartment = new SimpleStringProperty(dvr_deprtmnt);
            this.dvrVt = new SimpleStringProperty(dvr_vt);
            this.dvrClaimant = new SimpleStringProperty(dvr_clmnt);
            this.dvrOrganization = new SimpleStringProperty(dvr_org);
            this.dvrDateRelease = new SimpleStringProperty(dvr_rel);
     }
}
