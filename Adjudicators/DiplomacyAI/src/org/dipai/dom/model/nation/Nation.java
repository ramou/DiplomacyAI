package org.dipai.dom.model.nation;

import org.dsrg.soenea.domain.DomainObject;

public class Nation extends DomainObject<Long> {
	private String name;

	public Nation(Long id, Long version, String name) {
		super(id, version);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
