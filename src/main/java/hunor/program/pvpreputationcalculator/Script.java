package hunor.program.pvpreputationcalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Script {

    public Label lbLevel;
    public ChoiceBox<String> choiceBox;
    public ImageView ImgProgress;
    public Image servant = new Image(getClass().getResourceAsStream("Images/progression-bg-servant.png"));
    public Image guardian = new Image(getClass().getResourceAsStream("Images/progression-bg-guardian.png"));
    public TextField tfLevelXp;
    public Label lbXpRequired;
    public TextField tfYourLevel;
    public Label lbBattlesWon;
    public Label lbGradeV;
    public Label lbGradeIV;
    public Label lbGradeIII;
    public Label lbGradeII;
    public Label lbGradeI;
    public Label lbXpGain;
    public Label lbLevelGain;
    public TextField tfGoalLevel;
    public CheckBox cbHourglassLowered;
    public Label lbPotentialXpLoss;
    public Label lbPotentialFlagXpLoss;


    public int[] ExperienceRequiredList = {
            0, 2000, 3278, 4033, 4618, 5108, 5535, 5919, 6267, 6590, 6890, 7171, 7437, 7690, 7929, 8160, 8381, 8593, 8797, 8995,
            9187, 9373, 9552, 9728, 9899, 10065, 10227, 10386, 10541, 10693, 10842, 10986, 11130, 11270, 11407, 11542, 11674, 11805, 11934, 12060,
            12184, 12306, 12428, 12546, 12663, 12779, 0, 13005, 13117, 13227, 13335, 13443, 0, 13652, 0, 0, 13960, 0, 14159, 14257,
            14353, 14450, 14545, 14639, 14732, 14825, 14916, 15006, 15097, 15186, 15274, 15361, 0, 15535, 15619, 15705, 15788, 15872, 15954, 16036,
            16118, 16198, 16279, 16359, 16437, 16516, 16593, 16672, 16747, 16825, 16900, 16975, 17050, 17124, 17199, 17271, 17344, 17417, 17489, 17560
    };

    public int sumLevels;

    public int[] StreakWinExperience = { 4200, 4675, 5190, 5688, 6600, 26353 }; // After 5 wins, you don't get increased experience from each successive win.
    public int[] HourglassLoweringExperience = { 1100, 2640, 4680, 7800, 16220 }; // +3000 xp after 4 streak

    //Flag Experience
    public int gradeOne = 600;
    public int gradeTwo = 1200;
    public int gradeThree = 1800;
    public int gradeFour = 2400;
    public int gradeFive = 3000;

    public int yourLevel = 1000;
    public int battlesWon;
    public int gradeV;
    public int gradeIV;
    public int gradeIII;
    public int gradeII;
    public int gradeI;
    public int xpGain;

    public void initialize() {
        choiceBox.getItems().addAll("Servant", "Guardian");
        choiceBox.setValue("Servant");
        choiceBox.setOnAction(this::getChoice);
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
            if (!tfLevelXp.getText().isEmpty() && !tfGoalLevel.getText().isEmpty()) {
                int Intlevel = Integer.parseInt(tfLevelXp.getText());
                int IntGoalLevel = Integer.parseInt(tfGoalLevel.getText());
                if (Intlevel > 0 && Intlevel < 10000 && IntGoalLevel > Intlevel) {
                    lbXpRequired.setText(calculateXpDifference(Intlevel, IntGoalLevel) + " xp");
                }
            }
        } catch (Exception e) { }
    }

    public void onSetGoalLevel(ActionEvent actionEvent) {
        try {
            if (!tfLevelXp.getText().isEmpty() && !tfGoalLevel.getText().isEmpty()) {
                int Intlevel = Integer.parseInt(tfLevelXp.getText());
                int IntGoalLevel = Integer.parseInt(tfGoalLevel.getText());
                if (IntGoalLevel > 0 && IntGoalLevel < 10000 && IntGoalLevel > Intlevel) {
                    lbXpRequired.setText(calculateXpDifference(Intlevel, IntGoalLevel) + " xp");
                }
            }
        } catch (Exception e) { System.out.println("e = " + e); }
    }

    public int calcSumLevels(int index) {
        sumLevels = 0;
        for (int i = 0; i < index; i++) sumLevels += ExperienceRequiredList[i];
        return sumLevels;
    }

    public String calculateXpDifference(int currentIndex, int goalIndex) {
        int sumLevelsCurrent = 0;
        int sumLevelsGoal = 0;
        if (currentIndex < 100) for (int i = 0; i < currentIndex; i++) sumLevelsCurrent += ExperienceRequiredList[i];
        else {
            sumLevelsCurrent = calcSumLevels(100) + (currentIndex-100) * 12600;
        }
        if (goalIndex < 100) for (int i = 0; i < goalIndex; i++) sumLevelsGoal += ExperienceRequiredList[i];
        else {
            sumLevelsGoal = calcSumLevels(100) + (goalIndex-100) * 12600;
        }
        int xpDifference = sumLevelsGoal - sumLevelsCurrent;
        String strXp = String.format("%,d", xpDifference).replace(',', ' ');

        return strXp;
    }

    public String calculateXpRequired() {
        int xpRequired = 0;
        int level = Integer.parseInt(tfLevelXp.getText());
        if (level < 100) xpRequired = calcSumLevels(level);
        else xpRequired = calcSumLevels(100) + (level-100) * 12600;
        String strXp = String.format("%,d", xpRequired).replace(',', ' ');
        return strXp;
    }

    public void onUseYourLevel() {
        try {
            if (!tfYourLevel.getText().isEmpty()) {
                int Intlevel = Integer.parseInt(tfYourLevel.getText());
                if (Intlevel > 0 && Intlevel < 10000) {
                    if (Intlevel > 999) {
                        StringBuilder stringLevel = new StringBuilder(tfYourLevel.getText());
                        stringLevel.insert(1, ".");
                        lbLevel.setText(stringLevel.toString());
                    } else { lbLevel.setText(tfYourLevel.getText()); }
                    yourLevel = Intlevel;

                    battlesWon = 0;  lbBattlesWon.setText("0");
                    gradeV = 0;  lbGradeV.setText("0");
                    gradeIV = 0;  lbGradeIV.setText("0");
                    gradeIII = 0;  lbGradeIII.setText("0");
                    gradeII = 0;  lbGradeII.setText("0");
                    gradeI = 0;  lbGradeI.setText("0");
                    cbHourglassLowered.setSelected(false);
                    lbPotentialFlagXpLoss.setText("");
                    lbXpGain.setText("Xp gained: 0");
                    lbLevelGain.setText("Level gained:" + yourLevel);
                    lbPotentialXpLoss.setText("");
                }
            }
        } catch (Exception e) {}
    }

    public void setImageLevel(int level) {
        if (level > 0 && level < 10000) {
            if (level > 999) {
                StringBuilder stringLevel = new StringBuilder(level+"");
                stringLevel.insert(1, ".");
                lbLevel.setText(stringLevel.toString());
            } else { lbLevel.setText(level+""); }
        }
    }


    public void onClickBattlesWonPlus() {
        if (battlesWon < 99) battlesWon++;
        lbBattlesWon.setText(battlesWon+"");

        calculateXpGained();
    }

    public void onClickBattlesWonMinus() {
        if (battlesWon > 0) battlesWon--;
        lbBattlesWon.setText(battlesWon+"");

        calculateXpGained();
    }


    public void onClickGradeVPlus() {
        if (gradeV < 99) gradeV++;
        lbGradeV.setText(gradeV+"");

        calculateXpGained();
    }

    public void onClickGradeVMinus() {
        if (gradeV > 0) gradeV--;
        lbGradeV.setText(gradeV+"");

        calculateXpGained();
    }


    public void onClickGradeIVPlus() {
        if (gradeIV < 99) gradeIV++;
        lbGradeIV.setText(gradeIV+"");

        calculateXpGained();
    }

    public void onClickGradeIVMinus() {
        if (gradeIV > 0) gradeIV--;
        lbGradeIV.setText(gradeIV+"");

        calculateXpGained();
    }


    public void onClickGradeIIIPlus() {
        if (gradeIII < 99) gradeIII++;
        lbGradeIII.setText(gradeIII+"");

        calculateXpGained();
    }

    public void onClickGradeIIIMinus() {
        if (gradeIII > 0) gradeIII--;
        lbGradeIII.setText(gradeIII+"");

        calculateXpGained();
    }


    public void onClickGradeIIPlus() {
        if (gradeII < 99) gradeII++;
        lbGradeII.setText(gradeII+"");

        calculateXpGained();
    }

    public void onClickGradeIIMinus() {
        if (gradeII > 0) gradeII--;
        lbGradeII.setText(gradeII+"");

        calculateXpGained();
    }


    public void onClickGradeIPlus() {
        if (gradeI < 99) gradeI++;
        lbGradeI.setText(gradeI+"");

        calculateXpGained();
    }

    public void onClickGradeIMinus() {
        if (gradeI > 0) gradeI--;
        lbGradeI.setText(gradeI+"");

        calculateXpGained();
    }


    public void onClickHourglassLowered(ActionEvent actionEvent) { calculateXpGained(); }


    public void calculateXpGained() {
        xpGain = 0;
        int loweringLoss = 0;
        int flagLoss = 0;
        if (battlesWon < StreakWinExperience.length) for (int i = 0; i < battlesWon; i++) xpGain += StreakWinExperience[i];
        else xpGain = StreakWinExperience[5] + (battlesWon-5) * 6600;
        xpGain += gradeV * gradeFive;
        xpGain += gradeIV * gradeFour;
        xpGain += gradeIII * gradeThree;
        xpGain += gradeII * gradeTwo;
        xpGain += gradeI * gradeOne;
        if (cbHourglassLowered.isSelected()) {
            if (battlesWon < 4) for (int i = 0; i < battlesWon; i++) xpGain += HourglassLoweringExperience[i];
            else xpGain += HourglassLoweringExperience[4] + (battlesWon-4) * 3000;
            lbPotentialXpLoss.setText("");
        } else {
            if (battlesWon < 4) for (int i = 0; i < battlesWon; i++) loweringLoss += HourglassLoweringExperience[i];
            else loweringLoss += HourglassLoweringExperience[4] + (battlesWon-4) * 3000;

            if (loweringLoss > 0) lbPotentialXpLoss.setText("Xp loss if sunk: " + loweringLoss);
            else lbPotentialXpLoss.setText("");
        }
        flagLoss += gradeV * gradeFive;
        flagLoss += gradeIV * gradeFour;
        flagLoss += gradeIII * gradeThree;
        flagLoss += gradeII * gradeTwo;
        flagLoss += gradeI * gradeOne;

        if (flagLoss > 0) lbPotentialFlagXpLoss.setText("Flag xp loss if sunk: " + flagLoss);
        else lbPotentialFlagXpLoss.setText("");

        lbXpGain.setText("Xp gained: " + xpGain);
        lbLevelGain.setText("Level gained: " + reverseLvlSearch(xpGain));
    }

    public int reverseLvlSearch(int xp) {
        int i = yourLevel;
        while (xp > 0) {
            if (i < 100) xp -= ExperienceRequiredList[i];
            else xp -= 12600;
            System.out.println("xp = " + xp);
            if (xp >= 0) {
                i++;
            }
        }
        setImageLevel(i);
        return i;
    }

}