package hunor.program.pvpreputationcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Script {

    public Label lbLevel;
    public ChoiceBox<String> choiceBox;
    public ImageView ImgProgress;
    public Image servant = new Image(getClass().getResourceAsStream("Images/progression-bg-servant.png"));
    public Image guardian = new Image(getClass().getResourceAsStream("Images/progression-bg-guardian.png"));
    public TextField tfLevel;




    public int[] ExperienceRequiredList = {
            0, 2000, 3278, 4033, 4618, 5108, 5535, 5919, 6267, 6590, 6890, 7171, 7437, 7690, 7929, 8160, 8381, 8593, 8797, 8995,
            9187, 9373, 9552, 9728, 9899, 10065, 10227, 10386, 10541, 10693, 10842, 10986, 11130, 11270, 11407, 11542, 11674, 11805, 11934, 12060,
            12184, 12306, 12428, 12546, 12663, 12779, 0, 13005, 13117, 13227, 13335, 13443, 0, 13652, 0, 0, 13960, 0, 14159, 14257,
            14353, 14450, 14545, 14639, 14732, 14825, 14916, 15006, 15097, 15186, 15274, 15361, 0, 15535, 15619, 15705, 15788, 15872, 15954, 16036,
            16118, 16198, 16279, 16359, 16437, 16516, 16593, 16672, 16747, 16825, 16900, 16975, 17050, 17124, 17199, 17271, 17344, 17417, 17489, 17560
    };

    public int sumLevels;

    public int lossXp = 700;
    public int[] SreakWinExperience = { 4200, 4675, 5190, 5688, 6600 }; // After 5 wins, you don't get increased experience from each successive win.
    public int[] HourglassLoweringExperience = { 0, 1100, 2640, 4680, 7800 }; // +3000 xp after 4 streak

    //Flag Experience
    public int gradeOne = 600;
    public int gradeTwo = 1200;
    public int gradeThree = 1800;
    public int gradeFour = 2400;
    public int gradeFive = 3000;
    public Label lbXpRequired;


    public void initialize() {
        choiceBox.getItems().addAll("Servant", "Guardian");
        choiceBox.setValue("Servant");
        choiceBox.setOnAction(this::getChoice);
        lbLevel.setText("1.000");
        lbLevel.setFont(Font.loadFont(getClass().getResourceAsStream("Font/Windlass.ttf"), 40));
        lbXpRequired.setFont(Font.loadFont(getClass().getResourceAsStream("Font/Windlass.ttf"), 15));
        lbXpRequired.setText(calculateXpRequired() + " xp");
    }

    public void getChoice(ActionEvent event) {
        if (choiceBox.getValue().equals("Servant")) ImgProgress.setImage(servant);
        else ImgProgress.setImage(guardian);
    }

    public void onLevelSet(ActionEvent actionEvent) {
        try {
            if (!tfLevel.getText().isEmpty()) {
                int Intlevel = Integer.parseInt(tfLevel.getText());
                if (Intlevel > 0 && Intlevel < 10000) {
                    if (Intlevel > 999) {
                        StringBuilder stringLevel = new StringBuilder(tfLevel.getText());
                        stringLevel.insert(1, ".");
                        lbLevel.setText(stringLevel.toString());
                    } else { lbLevel.setText(tfLevel.getText()); }
                    lbXpRequired.setText(calculateXpRequired() + " xp");
                }
            }
        } catch (Exception e) { System.out.println("e = " + e); }
    }

    public int calcSumLevels(int index) {
        sumLevels = 0;
        for (int i = 0; i < index; i++) sumLevels += ExperienceRequiredList[i];
        return sumLevels;
    }

    public String calculateXpRequired() {
        int xpRequired = 0;
        int level = Integer.parseInt(tfLevel.getText());
        if (level < 100) xpRequired = calcSumLevels(level);
        else {
            xpRequired = calcSumLevels(100) + (level-100) * 12600;
        }
        String strXp = String.format("%,d", xpRequired).replace(',', ' ');
        return strXp;
    }
}