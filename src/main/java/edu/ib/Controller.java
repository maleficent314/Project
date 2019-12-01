package edu.ib;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.SnapshotParameters;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;


public class Controller {

    private ObservableList<String> planets = FXCollections.observableArrayList("Mercury", "Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto");
    Planets p1 = new Planets("Mercury",0.2056,0.387);
    Planets p2 = new Planets("Venus",0.0068,0.723);
    Planets p3 = new Planets("Earth",0.0167,1);
    Planets p4 = new Planets("Mars",0.0934,1.524);
    Planets p5 = new Planets("Jupiter",0.0484,5.203);
    Planets p6 = new Planets("Saturn",0.0542,9.537);
    Planets p7 = new Planets("Uranus",0.0472,19.191);
    Planets p8 = new Planets("Neptune",0.0086,30.069);
    Planets p9 = new Planets("Pluto",0.2488,39.482);


    @FXML
    private ChoiceBox<String> ChoiceOfPlanet;


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
    private Button PlanetsLoadingButton;

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
            double MNew = M;

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
        double a = Double.valueOf(GivenDistance.getText()) * 150000000;
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
        double a = Double.valueOf(GivenDistance.getText()) * 150000000;
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
        double a = Double.valueOf(GivenDistance.getText()) * 150000000;
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
    void planetsLoadingBtnPressed(ActionEvent event) {
        //ChoiceOfPlanet.setValue("Mercury");
        ChoiceOfPlanet.setItems(planets);
        if(ChoiceOfPlanet.getValue() == "Mercury"){
            GivenEccentricity.setText(Double.toString(p1.getEccentricity()));
            GivenDistance.setText(Double.toString(p1.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Venus"){
            GivenEccentricity.setText(Double.toString(p2.getEccentricity()));
            GivenDistance.setText(Double.toString(p2.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Earth"){
            GivenEccentricity.setText(Double.toString(p3.getEccentricity()));
            GivenDistance.setText(Double.toString(p3.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Mars"){
            GivenEccentricity.setText(Double.toString(p4.getEccentricity()));
            GivenDistance.setText(Double.toString(p4.getDistance()));
            System.out.println();
        }
        if(ChoiceOfPlanet.getValue() == "Jupiter"){
            GivenEccentricity.setText(Double.toString(p5.getEccentricity()));
            GivenDistance.setText(Double.toString(p5.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Saturn"){
            GivenEccentricity.setText(Double.toString(p6.getEccentricity()));
            GivenDistance.setText(Double.toString(p6.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Uranus"){
            GivenEccentricity.setText(Double.toString(p7.getEccentricity()));
            GivenDistance.setText(Double.toString(p7.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Neptune"){
            GivenEccentricity.setText(Double.toString(p8.getEccentricity()));
            GivenDistance.setText(Double.toString(p8.getDistance()));
        }
        if(ChoiceOfPlanet.getValue() == "Pluto"){
            GivenEccentricity.setText(Double.toString(p9.getEccentricity()));
            GivenDistance.setText(Double.toString(p9.getDistance()));
        }
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
        WritableImage image = Trajectory.snapshot(new SnapshotParameters(), null);
        File file = new File("C:\\Users\\Dell Latitude\\Desktop\\savedChart.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
        }
        System.out.println(Trajectory.getData().addAll());
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
        assert ChoiceOfPlanet != null : "fx:id=\"ChoiceOfPlanet\" was not injected: check your FXML file 'chart.fxml'.";
        assert PlanetsLoadingButton != null : "fx:id=\"PlanetsLoadingButton\" was not injected: check your FXML file 'chart.fxml'.";
    }
}
