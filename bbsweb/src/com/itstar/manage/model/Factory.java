package com.itstar.manage.model;

import com.itstar.manage.model.*;

public abstract class Factory {
	private static Factory factory = null;

	public Factory() {
	}

	public static Factory getInstance() {
		if (factory == null)
			try {
				Class facotryClass = Class.forName("com.itstar.manage.model.sql.SqlFactory");
				factory = (Factory) facotryClass.newInstance();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		return factory;
	}

	public abstract User InitUser();

	public abstract UserFactory InitUserFactory();
}
