package de.unipotsdam.dacha.client.chat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;

import de.unipotsdam.dacha.client.util.StringUtils;
import de.unipotsdam.dacha.shared.chat.AnswerOption;

public class ChatViewImpl extends Composite implements ChatView {

	interface ChatUiBinder extends UiBinder<Widget, ChatViewImpl> {
	}

	private static final ChatUiBinder uiBinder = GWT.create(ChatUiBinder.class);

	private Presenter presenter;

	@UiField
	TextBox messageBox;

	@UiField
	Label lastMessage;

	@UiField(provided = true)
	CellTable<String> historyTable;

	@UiField(provided = true)
	CellTable<AnswerOption> answerOptionsTable;

	@UiField
	ScrollPanel historyScroller;

	@UiField
	ScrollPanel answerTableScroller;

	@UiField
	DockLayoutPanel infoBox;
	
	@UiField
	Button saveButton;

	private ListDataProvider<String> historyDataProvider = new ListDataProvider<String>();
	private ListDataProvider<AnswerOption> answerOptionsProvider = new ListDataProvider<AnswerOption>();
	private MultiSelectionModel<AnswerOption> answerOptionSelectionModel;
	
	private TextColumn<AnswerOption> similarSentenceColumn = null;
	private TextColumn<AnswerOption> overallScoreColumn = null;
	private TextColumn<AnswerOption> wordScoreColumn = null;
	private TextColumn<AnswerOption> parseScoreColumn = null;	
	private TextColumn<AnswerOption> lengthScoreColumn = null;
	private TextColumn<AnswerOption> nerScoreColumn = null;
	private TextColumn<AnswerOption> historyScoreColumn = null;

	public ChatViewImpl() {

		historyTable = new CellTable<String>(300);
		historyTable.setWidth("100%");
		historyTable.addColumn(new TextColumn<String>() {
			public String getValue(String object) {
				return object;
			}
		});

		final ProvidesKey<AnswerOption> answerOptionKeyProvider = new ProvidesKey<AnswerOption>() {
			public Object getKey(AnswerOption item) {
				return item.getId();
			}
		};

		createAnswerOptionTable(answerOptionKeyProvider);

		initWidget(uiBinder.createAndBindUi(this));

		historyDataProvider.addDataDisplay(historyTable);

		answerOptionsProvider.addDataDisplay(answerOptionsTable);
	}


	@UiHandler("messageBox")
	public void onEnter(KeyPressEvent event) {
		if (event.getCharCode() == KeyCodes.KEY_ENTER) {
			addLine();
		}
	}

	@UiHandler("submitButton")
	public void onSubmit(ClickEvent event) {
		addLine();
	}
	
	/**
	 * @param event
	 */
	@UiHandler("saveButton")
	public void onSave(ClickEvent event) {
		final List<Integer> result = new ArrayList<Integer>();
		Set<AnswerOption> selectedSet = answerOptionSelectionModel.getSelectedSet();
		for (AnswerOption option : selectedSet) {
			result.add(option.getId());
		}
		
		presenter.onAnswerOptionsSelection(result);
		saveButton.setEnabled(false);
	}

	private void addLine() {
		if (StringUtils.hasText(messageBox.getText())) {
			presenter.onNewMessage(messageBox.getText());
			messageBox.setText(null);
			messageBox.setFocus(true);
		}
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void clear() {
		historyDataProvider.getList().clear();
		answerOptionsProvider.getList().clear();
	}

	@Override
	public void addChatEntry(String chatEntry) {
		historyDataProvider.getList().add(chatEntry);

		new Timer() {
			public void run() {
				historyScroller.scrollToBottom();
			};
		}.schedule(1);
	}

	@Override
	public void setLastMessage(String message) {
		lastMessage.setText(message);
	}

	@Override
	public void clearAnswerOptions() {
		answerOptionSelectionModel.clear();
		answerOptionsProvider.getList().clear();
	}

	@Override
	public void setAnswerOptions(Collection<AnswerOption> answerOptions) {
		answerOptionsProvider.getList().addAll(answerOptions);
		saveButton.setEnabled(!answerOptions.isEmpty());
	}

	@Override
	public void setInfoBoxVisibility(boolean visible) {
		infoBox.setVisible(visible);
	}
	
	@Override
	public void removeDebugInfo() {
		answerOptionsTable.removeColumn(similarSentenceColumn);
		answerOptionsTable.removeColumn(overallScoreColumn);
		answerOptionsTable.removeColumn(wordScoreColumn);
		answerOptionsTable.removeColumn(parseScoreColumn);	
		answerOptionsTable.removeColumn(lengthScoreColumn);
		answerOptionsTable.removeColumn(nerScoreColumn);
		answerOptionsTable.removeColumn(historyScoreColumn);
	}

	private void createAnswerOptionTable(
			final ProvidesKey<AnswerOption> answerOptionKeyProvider) {
		
		answerOptionsTable = new CellTable<AnswerOption>(100, AnswerOptionsTableResource.INSTANCE,
				answerOptionKeyProvider);
		answerOptionsTable.setWidth("100%");

		answerOptionSelectionModel = new MultiSelectionModel<AnswerOption>(answerOptionKeyProvider);
		answerOptionsTable.setSelectionModel(answerOptionSelectionModel, DefaultSelectionEventManager.<AnswerOption> createCheckboxManager());

		// Create checkbox column
		Column<AnswerOption, Boolean> checkboxColumn = new Column<AnswerOption, Boolean>(new CheckboxCell(true, false)) {
			@Override
			public Boolean getValue(AnswerOption object) {
				return answerOptionSelectionModel.isSelected(object);
			}
		};
		answerOptionsTable.addColumn(checkboxColumn, "Select");

		// Create reponse column.
		TextColumn<AnswerOption> responseColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return answerOption.getAnswer();
			}
		};
		answerOptionsTable.addColumn(responseColumn, "Response");
		
		// Create similar sentence column.
		similarSentenceColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return answerOption.getSimilarSentence();
			}
		};
		answerOptionsTable.addColumn(similarSentenceColumn, "Similar sentence");

		// Create overall score column.
		overallScoreColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getOverallScore());
			}
		};
		answerOptionsTable.addColumn(overallScoreColumn, "Total score");

		// Create word score column.
		wordScoreColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getWordScore());
			}
		};
		answerOptionsTable.addColumn(wordScoreColumn, "Word score");

		// Create word score column.
		parseScoreColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getEdgeScore());
			}
		};
		answerOptionsTable.addColumn(parseScoreColumn, "Parse score");

		// Create length score column.
		lengthScoreColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getLengthScore());
			}
		};
		answerOptionsTable.addColumn(lengthScoreColumn, "Length score");

		// Create named entity score column.
		nerScoreColumn = new TextColumn<AnswerOption>() {	
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getNerScore());
			}
		};
		answerOptionsTable.addColumn(nerScoreColumn, "Ner score");

		// Create history score column.
		historyScoreColumn = new TextColumn<AnswerOption>() {
			@Override
			public String getValue(AnswerOption answerOption) {
				return String.valueOf(answerOption.getHistoryScore());
			}
		};
		answerOptionsTable.addColumn(historyScoreColumn, "History score");
		
		answerOptionsTable.setWidth("99%", true);
		answerOptionsTable.setColumnWidth(checkboxColumn, 40.0, Unit.PX);
		answerOptionsTable.setColumnWidth(responseColumn, 37, Unit.PCT);
		answerOptionsTable.setColumnWidth(similarSentenceColumn, 30.0, Unit.PCT);
		answerOptionsTable.setColumnWidth(overallScoreColumn, 5.5, Unit.PCT);
		answerOptionsTable.setColumnWidth(wordScoreColumn, 5.5, Unit.PCT);
		answerOptionsTable.setColumnWidth(parseScoreColumn, 5.5, Unit.PCT);
		answerOptionsTable.setColumnWidth(lengthScoreColumn, 5.5, Unit.PCT);
		answerOptionsTable.setColumnWidth(nerScoreColumn, 5.5, Unit.PCT);
		answerOptionsTable.setColumnWidth(historyScoreColumn, 5.5, Unit.PCT);
	}
}
