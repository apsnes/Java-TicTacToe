package ticTacToe;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

	String turn = "X";
        int count = 0;
	ArrayList<Button> buttons = new ArrayList<>();

	public static void main(String[] args) {
		launch(TicTacToeApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {

		BorderPane layout = new BorderPane();
		Label title = new Label("Turn: " + turn);
		title.setFont(Font.font("Monospaced", 25));

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setHgap(10);
		grid.setVgap(10);

		for (int i = 0; i < 9; i++) {
                    Button button = new Button();
                    button.setFont(Font.font("Monospaced", 25));
                    button.setMinSize(70, 70);
                    button.setMaxSize(70, 70);

                    button.setOnMouseClicked((event) -> {
			if (title.getText().contains("Winner: ") || title.getText().equals("It's a draw!")) {
                            count = 0;
                            button.disarm();
                            } else if (button.getText().isEmpty()) {
                                button.setText(turn);
                                count++;
                                if (checkIfWinner()) {
                                    title.setText("The end!");
                                } else if(count == 9) {
                                    title.setText("It's a draw!");
                                } else {
                                    takeTurn();
                                    title.setText("Turn: " + turn);
                                }
                            }
			});
                    buttons.add(button);
		}

		grid.add(buttons.get(0), 0, 0);
		grid.add(buttons.get(1), 0, 1);
		grid.add(buttons.get(2), 0, 2);
		grid.add(buttons.get(3), 1, 0);
		grid.add(buttons.get(4), 1, 1);
		grid.add(buttons.get(5), 1, 2);
		grid.add(buttons.get(6), 2, 0);
		grid.add(buttons.get(7), 2, 1);
		grid.add(buttons.get(8), 2, 2);

		layout.setTop(title);
		layout.setCenter(grid);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();

	}
        
	public void takeTurn() {
		if (turn.equals("X")) {
			turn = "O";
		} else if (turn.equals("O")) {
			turn = "X";
		}
	}     
        
	public boolean checkIfWinner() {
		return checkVerticalColumns() || checkHorizontalRows() || checkDiagonals();
	}
	public boolean checkVerticalColumns() {
		if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(1).getText()))
				&& (buttons.get(0).getText().equals(buttons.get(2).getText())))) {
			return true;
		}
		if (!buttons.get(3).getText().isEmpty() && ((buttons.get(3).getText().equals(buttons.get(4).getText()))
				&& (buttons.get(3).getText().equals(buttons.get(5).getText())))) {
			return true;
		}
		if (!buttons.get(6).getText().isEmpty() && ((buttons.get(6).getText().equals(buttons.get(7).getText()))
				&& (buttons.get(6).getText().equals(buttons.get(8).getText())))) {
			return true;
		}

		return false;
	}

	public boolean checkHorizontalRows() {
		if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(3).getText()))
				&& (buttons.get(0).getText().equals(buttons.get(6).getText())))) {
			return true;
		}

		if (!buttons.get(1).getText().isEmpty() && ((buttons.get(1).getText().equals(buttons.get(4).getText()))
				&& (buttons.get(1).getText().equals(buttons.get(7).getText())))) {
			return true;
		}
		if (!buttons.get(2).getText().isEmpty() && ((buttons.get(2).getText().equals(buttons.get(5).getText()))
				&& (buttons.get(2).getText().equals(buttons.get(8).getText())))) {
			return true;
		}
		return false;
	}

	public boolean checkDiagonals() {
		if (!buttons.get(0).getText().isEmpty() && ((buttons.get(0).getText().equals(buttons.get(4).getText()))
				&& (buttons.get(0).getText().equals(buttons.get(8).getText())))) {
			return true;
		}
		if (!buttons.get(2).getText().isEmpty() && ((buttons.get(2).getText().equals(buttons.get(4).getText()))
				&& (buttons.get(2).getText().equals(buttons.get(6).getText())))) {
			return true;
		}
		return false;
	}

}