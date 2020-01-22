package org.dipai.ser;

import org.dipai.app.DipFC;
import org.dsrg.soenea.service.tdg.UserTDG;

public class RenewDatabase {

	public static void main(String[] args) {
		DipFC.prepareDbRegistry("");
		try {
		UserTDG.dropTable();
		UserTDG.dropUserRoleTable();
		
		} catch(Exception e){
			e.printStackTrace();
		}

		try {
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
