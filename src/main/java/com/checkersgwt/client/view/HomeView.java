package com.checkersgwt.client.view;

import com.checkersgwt.client.place.HomePlace;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;

import java.util.ArrayList;
import java.util.List;

public class HomeView extends Composite implements IHomeView {
	
	private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);
	
	interface HomeViewUiBinder extends UiBinder<Widget, HomeView>{}
	
	
	@UiField
	SpanElement nameSpan;
	
	@UiField
	Anchor goodbyeLink;
	
	@UiField
	Button button82;
	
	@UiField
	Button button84;
	
	@UiField
	Button button86;
	
	@UiField
	Button button88;
	
	@UiField
	Button button71;
	
	@UiField
	Button button73;
	
	@UiField
	Button button75;
	
	@UiField
	Button button77;
	
	@UiField
	Button button62;
	
	@UiField
	Button button64;
	
	@UiField
	Button button66;
	
	@UiField
	Button button68;
	
	@UiField
	Button button51;
	
	@UiField
	Button button53;
	
	@UiField
	Button button55;
	
	@UiField
	Button button57;
	
	@UiField
	Button button42;
	
	@UiField
	Button button44;
	
	@UiField
	Button button46;
	
	@UiField
	Button button48;
	
	@UiField
	Button button31;
	
	@UiField
	Button button33;
	
	@UiField
	Button button35;
	
	@UiField
	Button button37;
	
	@UiField
	Button button22;
	
	@UiField
	Button button24;
	
	@UiField
	Button button26;
	
	@UiField
	Button button28;
	
	@UiField
	Button button11;
	
	@UiField
	Button button13;
	
	@UiField
	Button button15;
	
	@UiField
	Button button17;
	
	
	private String classSideForRegulars = "whiteRegular";
	private String oppositeClassSideForRegulars = "blackRegular";
	
	List<Button> list = new ArrayList<>(32);
	List<DivElement> listWhiteNecessary = new ArrayList<>(4);
	List<DivElement> listBlackNecessary = new ArrayList<>(4);
	
	private DivElement previousDiv;
	private Element attackedDiv;
	
	private Presenter presenter;
	private String name;
	
	public HomeView() {
		initWidget(uiBinder.createAndBindUi(this));
		fillList();
		setClickHandlers();
	}
	
	private  void fillList() {
		list.add(button82);
		list.add(button84);
		list.add(button86);
		list.add(button88);
		list.add(button71);
		list.add(button73);
		list.add(button75);
		list.add(button77);
		list.add(button62);
		list.add(button64);
		list.add(button66);
		list.add(button68);
		list.add(button51);
		list.add(button53);
		list.add(button55);
		list.add(button57);
		list.add(button42);
		list.add(button44);
		list.add(button46);
		list.add(button48);
		list.add(button31);
		list.add(button33);
		list.add(button35);
		list.add(button37);
		list.add(button22);
		list.add(button24);
		list.add(button26);
		list.add(button28);
		list.add(button11);
		list.add(button13);
		list.add(button15);
		list.add(button17);
	}
	
	private void setClickHandlers() {
		list.forEach(button -> setClickHandler(button));
	}
	
	private void setClickHandler(Button button) {
		button.addClickHandler((event -> {
			DivElement currentDiv = (DivElement) button.getElement().getParentElement();
			if (!previousDiv.equals(currentDiv)) {
				if (isAvailableChecker(currentDiv)) {
					if (previousDiv != null) {
						previousDiv.removeClassName("red");
					}
					currentDiv.addClassName("red");
					previousDiv = currentDiv;
				} else if (isAvailableMove(currentDiv)) {
					previousDiv.removeClassName(classSideForRegulars);
					previousDiv.removeClassName("red");
					currentDiv.addClassName(classSideForRegulars);
					checkNecessaryMoves(currentDiv);
					previousDiv = null;
					setTurn();
				} else if (isAvailableAttack(currentDiv)) {
					attackedDiv.removeClassName(oppositeClassSideForRegulars);
					previousDiv.removeClassName(classSideForRegulars);
					previousDiv.removeClassName("red");
					currentDiv.addClassName(classSideForRegulars);
					if(!isNecessaryAttack(currentDiv)) {
						setTurn();
					}
					previousDiv = null;
				}
			}
		}));
	}
	
	private boolean isAvailableChecker(DivElement curruntDiv) {
		if (curruntDiv.hasClassName(classSideForRegulars)) {
			return true;
		}
		return false;
	}
	
	
	private boolean isAvailableMove(DivElement currentDiv) {
		if (previousDiv == null || isDivChecker(currentDiv)) {
			return false;
		}
		
		int idCurrent = Integer.parseInt(currentDiv.getId());
		int idPrevious = Integer.parseInt(previousDiv.getId());
		
		List<DivElement> lestNecessary;
		boolean white = isWhite();
		if (white || isBlack()) {
			lestNecessary = white ? listWhiteNecessary : listBlackNecessary;
			if (!lestNecessary.isEmpty() ) {
				if (lestNecessary.contains(currentDiv)) {
					lestNecessary.remove(currentDiv);
					return true;
				}
				return false;
			}
			return white ? isAvailableMoveForWhite(idCurrent, idPrevious) : isAvailableMoveForBlack(idCurrent, idPrevious);
		}
		return false;
	}
	
	private void checkNecessaryMoves(DivElement currentDiv) {
		if(isNecessaryAttack(currentDiv)) {
			if (isWhite()) {
				listWhiteNecessary.add(currentDiv);
			} else if (isBlack()) {
				listBlackNecessary.add(currentDiv);
			}
		}
		
		setTurn();
		
		int idCurrent = Integer.parseInt(currentDiv.getId());
		int idLeftDown = idCurrent - 11;
		int idRightDown = idCurrent - 9;
		int idLeftUp = idCurrent + 9;
		int idRightUp = idCurrent + 11;
		int[] possibleAttackIds = {idLeftDown, idRightDown, idLeftUp, idRightUp};
		DivElement possibleAttackedDiv;
		for (int possibleAttackId : possibleAttackIds) {
			possibleAttackedDiv = DOM.getElementById(String.valueOf(possibleAttackId)).cast();
			if (isNecessaryAttack(possibleAttackedDiv)) {
				if (isWhite()) {
					listWhiteNecessary.add(currentDiv);
				} else if (isBlack()) {
					listBlackNecessary.add(currentDiv);
				}
			}
		}
		
		setTurn();
	}
	
	private boolean isBlack() {
		return classSideForRegulars.equals("blackRegular");
	}
	
	private boolean isAvailableMoveForBlack(int idCurrent, int idPrevious) {
		return (idPrevious / 10) - (idCurrent / 10) == 1   &&   Math.abs((idPrevious % 10) - (idCurrent % 10)) == 1;
	}
	
	private boolean isWhite() {
		return classSideForRegulars.equals("whiteRegular");
	}
	
	private boolean isAvailableMoveForWhite(int idCurrent, int idPrevious) {
		return (idCurrent / 10) - (idPrevious / 10) == 1   &&   Math.abs((idPrevious % 10) - (idCurrent % 10)) == 1;
	}
	
	
	private boolean isAvailableAttack(DivElement currentDiv) {
		if (previousDiv == null) {
			return false;
		}
		if (isDivChecker(currentDiv)) {
			return false;
		}
		
		int idCurrent = Integer.parseInt(currentDiv.getId());
		int idPrevious = Integer.parseInt(previousDiv.getId());
		int idCurrentTen = idCurrent / 10;
		int idPreviousTen = idPrevious / 10;
		int idCurrentOne = idCurrent % 10;
		int idPreviousOne = idPrevious % 10;
		
		if (Math.abs(idCurrentTen - idPreviousTen) != 2 || Math.abs(idCurrentOne - idPreviousOne) != 2) {
			return false;
		}
		
		int attackedId = (idCurrentTen + idPreviousTen) / 2 * 10 + (idCurrentOne + idPreviousOne) / 2;
		Element attackedDiv = DOM.getElementById(String.valueOf(attackedId));
		
		if (attackedDiv.hasClassName(oppositeClassSideForRegulars)) {
			this.attackedDiv = attackedDiv;
			return true;
		}
		
		return false;
	}
	
	private boolean isDivChecker(DivElement currentDiv) {
		return currentDiv.hasClassName("whiteRegular") || currentDiv.hasClassName("blackRegular");
	}
	
	private boolean isNecessaryAttack(DivElement currentDiv) {
		int idCurrent = Integer.parseInt(currentDiv.getId());
		int idLeftDown = idCurrent - 11;
		int idRightDown = idCurrent - 9;
		int idLeftUp = idCurrent + 9;
		int idRightUp = idCurrent + 11;
		
		int[] possibleAttackIds = {idLeftDown, idRightDown, idLeftUp, idRightUp};
		DivElement divWhereAttackingCheckerWillBe = null;
		for (int possibleAttackId : possibleAttackIds) {
			Element possibleAttackedDiv = DOM.getElementById(String.valueOf(possibleAttackId));
			if (possibleAttackedDiv.hasClassName(oppositeClassSideForRegulars)) {
				if (possibleAttackId == idLeftDown) {
					divWhereAttackingCheckerWillBe = DOM.getElementById(String.valueOf(idCurrent - 22)).cast();
				} else if (possibleAttackId == idRightDown) {
					divWhereAttackingCheckerWillBe = DOM.getElementById(String.valueOf(idCurrent - 18)).cast();
				} else if (possibleAttackId == idLeftUp) {
					divWhereAttackingCheckerWillBe = DOM.getElementById(String.valueOf(idCurrent + 18)).cast();
				} else if (possibleAttackId == idRightUp) {
					divWhereAttackingCheckerWillBe = DOM.getElementById(String.valueOf(idCurrent + 22)).cast();
				}
				
				if (!isDivChecker(divWhereAttackingCheckerWillBe)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private void setTurn() {
		if (classSideForRegulars.equals("whiteRegular")) {
			classSideForRegulars = "blackRegular";
			oppositeClassSideForRegulars = "whiteRegular";
		} else if (classSideForRegulars.equals("blackRegular")) {
			classSideForRegulars = "whiteRegular";
			oppositeClassSideForRegulars = "blackRegular";
		}
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
		nameSpan.setInnerText(name);
	}
	
	@UiHandler("goodbyeLink")
	void onClickGoodbye(ClickEvent e) {
		presenter.goTo(new HomePlace(name));
	}
	
	@Override
	public void setPresenter(Presenter presenter)
	{
		this.presenter = presenter;
	}
	
}