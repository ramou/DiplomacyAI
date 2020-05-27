package org.dipai.app.dispatcher.game;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public class Create extends Dispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		Map m = new HashMap<String, Object>();
		m.put("turn", 0);
		
		Map phase = new HashMap<String, Object>();
		phase.put("id", 0);
		m.put("phase", phase);
		
		Map italy = new HashMap<String, Object>();
		italy.put("player", 1);
		italy.put("armies", new Integer[] {53, 76});
		italy.put("fleets", new Integer[] {45});
		italy.put("bases", new Integer[] {45, 53, 76})  ;
		m.put("italy", italy);

		Map france = new HashMap<String, Object>();
		france.put("player", 5);
		france.put("armies", new Integer[] {39, 48});
		france.put("fleets", new Integer[] {13});
		france.put("bases", new Integer[] {13, 39, 48})  ;
		m.put("france", france);

		Map austria = new HashMap<String, Object>();
		austria.put("player", 2);
		austria.put("armies", new Integer[] {14, 77});
		austria.put("fleets", new Integer[] {70});
		austria.put("bases", new Integer[] {14, 70, 77})  ;
		m.put("austria", austria);

		Map germany = new HashMap<String, Object>();
		germany.put("player", 3);
		germany.put("armies", new Integer[] {10, 41});
		germany.put("fleets", new Integer[] {33});
		germany.put("bases", new Integer[] {10, 33, 41})  ;
		m.put("germany", germany);

		Map england = new HashMap<String, Object>();
		england.put("player", 7);
		england.put("armies", new Integer[] {36});
		england.put("fleets", new Integer[] {23, 34});
		england.put("bases", new Integer[] {23, 34, 36})  ;
		m.put("england", england);
		
		Map turkey = new HashMap<String, Object>();
		turkey.put("player", 18);
		turkey.put("armies", new Integer[] {20, 60});
		turkey.put("fleets", new Integer[] {3});
		turkey.put("bases", new Integer[] {3, 20, 60})  ;
		m.put("turkey", turkey);

		Map russia = new HashMap<String, Object>();
		russia.put("player", 6);
		russia.put("armies", new Integer[] {40, 79});
		russia.put("fleets", new Integer[] {57, 66});
		russia.put("bases", new Integer[] {40, 57, 66, 79})  ;
		m.put("russia", russia);
		myHelper.setRequestAttribute("game", m);
		forward("/WEB-INF/jsp/game.jsp");

	}

}
