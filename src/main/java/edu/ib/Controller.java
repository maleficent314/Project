package edu.ib;


import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;



public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField GivenEccentricity;

    @FXML
    private TextField GivenDistance;

    @FXML
    private TextField GivenPrecision;

    @FXML
    private Button BisectionButton;

    @FXML
    private Button SecantButton;

    @FXML
    private Button FixedButton;

    @FXML
    private Button NewtonButton;

    @FXML
    private Button ClearTrajectoriesButton;

    @FXML
    private Button ClearAllButton;

    @FXML
    private Button SaveButton;

    @FXML
    private ScatterChart<Double, Double> Trajectory;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    void bisectionBtnPressed(ActionEvent event) {
        double e = Double.valueOf(GivenEccentricity.getText());
        double a = Double.valueOf(GivenDistance.getText()) * 150000000;
        double precision = Double.valueOf(GivenPrecision.getText());
        XYChart.Series series = new XYChart.Series();
        double M;

        for (int i = 0; i < 1000; i++) {
            M = i * Math.PI / (180);
            double MNew= M;

            Methods solver1 = new Methods(new Equation(MNew, e));
            double E = solver1.bisection(0, 2 * Math.PI, precision);
            double x = a * Math.cos(E - e);
            double y = a * Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E);
            series.getData().add(new XYChart.Data(x, y));
        }

        Trajectory.getData().add(series);
    }

    @FXML
    void fixedBtnPressed(ActionEvent event) {
        double e = Double.valueOf(GivenEccentricity.getText());
        double a = Double.valueOf(GivenDistance.getText())* 150000000;
        double precision = Double.valueOf(GivenPrecision.getText());
        XYChart.Series series = new XYChart.Series();

        double M;
        for (int i = 0; i < 1000; i++) {
            M = i * Math.PI / (180);
            double MOld = M;

            Methods solver2 = new Methods(new Equation(MOld, e));
            double E = solver2.fixedPoint(0.5, precision);
            double x = a * Math.cos(E - e);
            double y = a * Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E);
            series.getData().add(new XYChart.Data(x, y));
        }

        Trajectory.getData().add(series);
    }

    @FXML
    void newtonBtnPressed(ActionEvent event) {
        double e = Double.valueOf(GivenEccentricity.getText());
        double a = Double.valueOf(GivenDistance.getText())* 150000000;
        double precision = Double.valueOf(GivenPrecision.getText());
        XYChart.Series series = new XYChart.Series();

        double M;

        for (int i = 0; i < 1000; i++) {
            M = i * Math.PI / (180);
            double MOld = M;

            Methods solver3 = new Methods(new Equation(MOld, e));
            double E = solver3.newtonRaphson(2, precision);

            double x = a * Math.cos(E - e);
            double y = a * Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E);
            series.getData().add(new XYChart.Data(x, y));
        }

        Trajectory.getData().add(series);
    }

    @FXML
    void secantBtnPressed(ActionEvent event) {
        double e = Double.valueOf(GivenEccentricity.getText());
        double a = Double.valueOf(GivenDistance.getText())* 150000000;
        double precision = Double.valueOf(GivenPrecision.getText());
        XYChart.Series series = new XYChart.Series();

        double M;

        for (int i = 0; i < 1000; i++) {
            M = i * Math.PI / (180);
            double MOld = M;

            Methods solver4 = new Methods(new Equation(MOld, e));
            double E = solver4.secant(2, 1, precision);

            double x = a * Math.cos(E - e);
            double y = a * Math.sqrt(1 - Math.pow(e, 2)) * Math.sin(E);
            series.getData().add(new XYChart.Data(x, y));
        }

        Trajectory.getData().add(series);
    }


    @FXML
    void clearAllBtnPressed(ActionEvent event) {
        Trajectory.getData().clear();
        GivenEccentricity.clear();
        GivenDistance.clear();
        GivenPrecision.clear();

    }

    @FXML
    void clearTrajectoriesBtnPressed(ActionEvent event) {
        Trajectory.getData().clear();
    }


    @FXML
    void saveBtnPressed(ActionEvent event) {

    }

    @FXML
    void enterDistance(ActionEvent event) {

    }

    @FXML
    void enterEccentricity(ActionEvent event) {

    }

    @FXML
    void enterPrecision(ActionEvent event) {

    }


    @FXML
    void initialize() {
        assert GivenEccentricity != null : "fx:id=\"GivenEccentricity\" was not injected: check your FXML file 'chart.fxml'.";
        assert GivenDistance != null : "fx:id=\"GivenDistance\" was not injected: check your FXML file 'chart.fxml'.";
        assert GivenPrecision != null : "fx:id=\"GivenPrecision\" was not injected: check your FXML file 'chart.fxml'.";
        assert Trajectory != null : "fx:id=\"Trajectory\" was not injected: check your FXML file 'chart.fxml'.";
        assert xAxis != null : "fx:id=\"xAxis\" was not injected: check your FXML file 'chart.fxml'.";
        assert yAxis != null : "fx:id=\"yAxis\" was not injected: check your FXML file 'chart.fxml'.";
        assert BisectionButton != null : "fx:id=\"BisectionButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert SecantButton != null : "fx:id=\"SecantButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert FixedButton != null : "fx:id=\"FixedButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert NewtonButton != null : "fx:id=\"NewtonButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert ClearTrajectoriesButton != null : "fx:id=\"ClearTrajectoriesButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert ClearAllButton != null : "fx:id=\"ClearAllButton\" was not injected: check your FXML file 'chart.fxml'.";
        assert SaveButton != null : "fx:id=\"SaveButton\" was not injected: check your FXML file 'chart.fxml'.";
    }
}
