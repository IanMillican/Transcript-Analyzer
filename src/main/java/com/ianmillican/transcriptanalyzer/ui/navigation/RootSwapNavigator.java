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
	public void show(Region viewRoot) {
		root.getChildren().setAll(viewRoot);
	}

}
