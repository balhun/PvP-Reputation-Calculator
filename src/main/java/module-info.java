module hunor.program.pvpreputationcalculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens hunor.program.pvpreputationcalculator to javafx.fxml;
    exports hunor.program.pvpreputationcalculator;
}