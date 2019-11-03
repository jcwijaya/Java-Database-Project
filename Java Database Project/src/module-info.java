module Test {
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;
	requires transitive javafx.media;
	requires transitive javafx.swing;
	requires transitive javafx.swt;
	requires transitive javafx.web;
	requires transitive java.sql;
	opens WebMart;
	exports WebMart;
}