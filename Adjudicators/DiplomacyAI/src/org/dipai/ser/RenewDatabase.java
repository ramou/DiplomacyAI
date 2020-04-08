package org.dipai.ser;

import org.dipai.app.DipFC;
import org.dipai.dom.model.game.tdg.GameTDG;
import org.dipai.dom.model.game.tdg.PlaysTDG;
import org.dsrg.soenea.service.tdg.UserTDG;

public class RenewDatabase {

	public static void main(String[] args) {
		DipFC.prepareDbRegistry("");
		try { UserTDG.dropTable();         } catch(Exception e){ e.printStackTrace();}
		try { UserTDG.dropUserRoleTable(); } catch(Exception e){ e.printStackTrace();}
		try { GameTDG.dropTable();         } catch(Exception e){ e.printStackTrace();}
		try { PlaysTDG.dropTable();        } catch(Exception e){ e.printStackTrace();}

		try {
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
			GameTDG.createTable();
			PlaysTDG.createTable();
		} catch(Exception e){
			e.printStackTrace();
		}

	}

}
