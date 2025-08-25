package com.ianmillican.transcriptanalyzer.ui.navigation;

import com.ianmillican.transcriptanalyzer.domain.interfaces.Navigator;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class RootSwapNavigator implements Navigator {
	
	private final Pane root;
	
	public RootSwapNavigator(Pane root) {
		this.root = root;
	}

	@Override
	public void show(Region viewRoot, boolean resize) {
		root.getChildren().setAll(viewRoot);
		if(resize) {
			root.getScene().getWindow().setWidth(1450);
			root.getScene().getWindow().setHeight(1050);
			root.getScene().getWindow().centerOnScreen();
		}
	}

}
