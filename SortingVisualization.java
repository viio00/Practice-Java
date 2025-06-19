package application;

//first time using javaFx so I have GPT as my guide

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.stage.Stage; //main window
import javafx.scene.Scene; //holds all visual content
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/*	HBox - horizontal box (items in row)
	VBox - vertical box (items in column)
	Pane - no inherent layout behavior, manually set X & Y positions
*/
import javafx.scene.shape.Rectangle; //for bar lines 
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


public class SortingVisualization extends Application {

	Stage window; //stores the stage to switch pages/scenes
	Scene scene1, scene2; 
	
	static final int Num_bars = 60; //60 rectangle bars
	static final int Max_height = 300; //random heights no more than 300px
	Rectangle[] bars = new Rectangle[Num_bars]; //array that stores 60 bars
	
	@Override //main GUI
	public void start(Stage primaryStage){
		window = primaryStage;
		
		Button bubbleSort = new Button("Bubble Sort");
		Button mergeSort = new Button("Merge Sort");
		Button quickSort = new Button("Quick Sort");
		
		bubbleSort.setOnAction(e -> showBubbleSort("Bubble Sort"));
        //mergeSort.setOnAction(e -> showMergeSort("Merge Sort"));
        //quickSort.setOnAction(e -> showQuickSort("Quick Sort"));
		
        VBox buttons = new VBox(4); //spacing 4px (gap between buttons)
        buttons.getChildren().addAll(bubbleSort, mergeSort, quickSort);
        buttons.setAlignment(Pos.CENTER);
        scene1 = new Scene(buttons, 300, 400);  //size of window
		window.setTitle("Sorting Visualization");
		//display the window
		window.setScene(scene1);
		window.show(); 
	}
	
	public void showBubbleSort(String type){
		Text sortType = new Text(type);
		
		VBox func = new VBox(4);
		func.setAlignment(Pos.CENTER);
		Button back = new Button("back");
		back.setOnAction(e -> window.setScene(scene1));
		func.getChildren().addAll(sortType, back);
		
		HBox box = new HBox(2); //spacing 2px (gap between bars)
		//fill the array with random height bars
		box.setAlignment(Pos.BASELINE_CENTER);

		
		for (int i = 0; i < Num_bars; i++) {
			int height = (int)(Math.random() * Max_height);
			bars[i] = new Rectangle(10, height, Color.BLACK);
			//(width, height, color)
			box.getChildren().add(bars[i]);
			//getChildren() -- gives access to the HBox
		}
		
		VBox layout2 = new VBox(20);
		layout2.getChildren().addAll(func, box);
		layout2.setAlignment(Pos.CENTER);
		scene2 = new Scene(layout2, 750, 400);
		window.setScene(scene2);
		
		
		/*	first time using threads although I am familiar with it.
		 	basically, thread allows a program to do multiple things
		 	at once */
		new Thread(() -> bubbleSort()).start();
	}
	
	public void bubbleSort() {
		try {
			for (int i = 0; i < bars.length-1; i++) {
				for (int j = 0; j < bars.length-1; j++) {
					if(bars[j].getHeight() > bars[j+1].getHeight()) {
						swap(j, j+1);
						Thread.sleep(20);
						}
					}
		
				}
		} catch (InterruptedException e) {
			e.printStackTrace();
			}
	}
	public void swap(int i, int j) {
		double h1 = bars[i].getHeight();
		double h2 = bars[j].getHeight();
	
		javafx.application.Platform.runLater(() -> {
			bars[i].setHeight(h2);
			bars[j].setHeight(h1);
		});
	
	/*	Platform.runLater(() -> {}); -- since we are ruuning a code background,
	 	it safely updates GUI. Basically waiting for thread to be ready
	 	in order for us to see the bars swap */
}

	public void mergeSort(){
	
	}
	
	
public static void main(String[] args) {
	launch(args);
		
	}
}
