import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class TipCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {

        // ----- Task #2 Controls -----
        Label chargeLabel = new Label("Restaurant Charge:");
        TextField chargeField = new TextField();

        Button calcButton = new Button("Calculate Tip");

        Label amountLabel = new Label("Amount to Tip:");
        Label tipAmountLabel = new Label("");   // starts empty

        // ----- Task #4 Event Handler -----
        calcButton.setOnAction(e -> {
            try {
                double charge = Double.parseDouble(chargeField.getText());
                double tip = charge * 0.20;
                tipAmountLabel.setText("$" + String.format("%.2f", tip));
            } catch (Exception ex) {
                tipAmountLabel.setText("Invalid input");
            }
        });

        // ----- Task #3 VBox Layout -----
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10));

        root.getChildren().addAll(
                chargeLabel,
                chargeField,
                calcButton,
                amountLabel,
                tipAmountLabel
        );

        // ----- Scene -----
        Scene scene = new Scene(root, 250, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tip Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


