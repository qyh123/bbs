package com.itstar.manage.model.sql;

import com.itstar.manage.model.*;
import com.itstar.manage.model.sql.*;

public class SqlFactory extends Factory {
	private User user = null;
	private UserFactory userFactory = null;

	public User InitUser() {
		return new SqlUser();
	}

	public UserFactory InitUserFactory() {
		return new SqlUserFactory();
	}
}
