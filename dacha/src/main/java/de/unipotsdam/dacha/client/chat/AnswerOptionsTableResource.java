package de.unipotsdam.dacha.client.chat;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;

public interface AnswerOptionsTableResource extends CellTable.Resources {

	public static final AnswerOptionsTableResource INSTANCE = GWT.create(AnswerOptionsTableResource.class);
	
	public interface AnswerOptionsTableStyle extends CellTable.Style {
		
	}
	
	@Source("AnswerOptionsTable.css")
	AnswerOptionsTableStyle cellTableStyle();
}
