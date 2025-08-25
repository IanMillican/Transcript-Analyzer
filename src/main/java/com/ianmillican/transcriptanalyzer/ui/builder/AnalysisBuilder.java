package com.ianmillican.transcriptanalyzer.ui.builder;

import java.util.List;

import com.ianmillican.transcriptanalyzer.domain.interfaces.Navigator;
import com.ianmillican.transcriptanalyzer.domain.model.Course;
import com.ianmillican.transcriptanalyzer.ui.viewmodel.AnalysisViewModel;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class AnalysisBuilder implements Builder<Region> {
	
	private final AnalysisViewModel model;
	private final Navigator nav;
	private Insets padding = new Insets(10);
	
	public AnalysisBuilder(AnalysisViewModel model, Navigator nav) {
		this.model = model;
		this.nav = nav;
	}

	@Override
	public Region build() {
		TableView<Course> result = new TableView<>();
		result.setPadding(padding);
		List<Course> courses = model.getAllCourses();
		result.setItems(FXCollections.observableArrayList(courses));
		TableColumn<Course, String> codeAndNameCol = Components.createColumn("Course", model::codeAndName);
		TableColumn<Course, Integer> creditHourCol = Components.createColumn("Credit Hour", model::creditHour);
		result.getColumns().add(codeAndNameCol);
		result.getColumns().add(creditHourCol);
		
		return result;
	}
	
	

}
