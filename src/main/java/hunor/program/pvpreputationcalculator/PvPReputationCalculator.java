package hunor.program.pvpreputationcalculator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class PvPReputationCalculator extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PvPReputationCalculator.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("PvP Reputation Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("Images/Servants_of_the_Flame_icon.png")));
        Font.loadFont(getClass().getResourceAsStream("font/Windlass.ttf"), 18);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}