package sudokuchecker;

import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class SudokuChecker extends Application {
        
    boolean correct = true;
    boolean missingInp = false;
    TextField [][] values = new TextField[6][6];
    Label output = new Label(" ");
    //2D array
    int[][] userInput = new int[6][6];

    public Stack<Integer> row=new Stack<>();
    public Stack<Integer> column= new Stack<>();
    
    
    @Override
    public void start(Stage primaryStage) {
        
        StackPane stack = new StackPane();
        output.setFont(Font.font ("Verdana", 15));          
        Button btn = new Button();
        btn.setText("Check Correctness"); 
        btn.setFont(Font.font ("Verdana", 15)); 
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                //application loop
                for( int i = 0 ; i < 6 ; i++ ){
                    for( int j = 0 ; j < 6 ; j++ )
                    {
                        String inp  = values[i][j].getText();
                        //string input error
                        if(!isInt(inp)){
                            missingInp = true;
                            output.setText("Invalid Input");
                            break;
                        }
                        
                        //int input
                        userInput[i][j] = Integer.parseInt(inp);
                        if (column.contains(userInput[i][j])){
                            correct = false;
                            break;
                        }
                        column.add(userInput[i][j]);
                    }
                    column.removeAll(column);
                }
                if(missingInp){
                    output.setText("Incomplete Solution");
                }
                else if(correct){
                    output.setText("Correct Solution");
                }
                else{
                    row.removeAll(row);
                    column.removeAll(column);
                    output.setText("Incorrect Solution");
                }
                missingInp = false;
                correct = true;
            }
            private boolean isInt(String x) {
                try {
                    Integer.parseInt(x);
                    return true;
                } catch(NumberFormatException e){
                    return false;
                }
            }
        });
       
        
        
        GridPane txtfieldLayout = new GridPane();
        for( int i = 0 ; i < 6 ; i++ )
            for( int j = 0 ; j < 6 ; j++ )
            {
                TextField textContainer = new TextField();
                textContainer.setPrefHeight(40);
                textContainer.setPrefWidth(50);
                textContainer.setFont(Font.font("Verdana", 20));
                values[i][j] = textContainer;
                txtfieldLayout.add(values[i][j], i, j);
            }

        GridPane root = new GridPane();
        root.setVgap(5);
        root.setHgap(5);
        root.setPadding(new Insets(0,8,5,7));
        
        stack.getChildren().add(txtfieldLayout);
        root.add(stack,1,1);
        root.add(output,1,2);
        root.add(btn,1,3);

        Scene scene = new Scene(root, 325, 325);
        primaryStage.setTitle("Sudoku 6x6");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
