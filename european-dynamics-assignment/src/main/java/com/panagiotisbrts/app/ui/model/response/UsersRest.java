package com.panagiotisbrts.app.ui.model.response;

import java.util.List;

/**
 * The response of the API when requesting the Users of a specific page and a
 * specific limit with total the total number of users in the Database.
 * 
 */

public class UsersRest {

	private List<SingleUserRest> items;
	private long total;

	public List<SingleUserRest> getItems() {
		return items;
	}

	public void setItems(List<SingleUserRest> items) {
		this.items = items;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}
