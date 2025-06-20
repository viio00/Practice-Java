package application;

//first time using javaFx so I have GPT as my guide

import javafx.application.Application;
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
	Scene scene1, scene2, scene3, scene4;
	
	public static final int Num_bars = 60; //60 rectangle bars
	public static final int Max_height = 300; //random heights no more than 300px
	public static Rectangle[] bars = new Rectangle[Num_bars]; //array that stores 60 bars
	
	@Override //main GUI
	public void start(Stage primaryStage){
		window = primaryStage;
		
		Button bubbleSort = new Button("Bubble Sort");
		Button mergeSort = new Button("Merge Sort");
		Button quickSort = new Button("Quick Sort");
		
		bubbleSort.setOnAction(e -> showBubbleSort("Bubble Sort"));
        mergeSort.setOnAction(e -> showMergeSort("Merge Sort"));
        quickSort.setOnAction(e -> showQuickSort("Quick Sort"));
		
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
		
		VBox layout = new VBox(50);
		layout.getChildren().addAll(func, box);
		layout.setAlignment(Pos.CENTER);
		scene2 = new Scene(layout, 750, 420);
		window.setScene(scene2);
		
		
		/*	first time using threads although I am familiar with it.
		 	basically, thread allows a program to do multiple things
		 	at once */
		bubbleSortClass bubble = new bubbleSortClass(bars);
		new Thread(() -> {
			try {
				bubble.bubbleSort();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).start();
	}
	
	public void showMergeSort(String type) {
		int[] array = new int[Num_bars];
		Text sortType = new Text(type);
		
		VBox func = new VBox(4);
		func.setAlignment(Pos.CENTER);
		Button back = new Button("back");
		back.setOnAction(e -> window.setScene(scene1));
		func.getChildren().addAll(sortType, back);
		
		HBox box = new HBox(2);
		box.setAlignment(Pos.BASELINE_CENTER);

		
		for (int i = 0; i < Num_bars; i++) {
			int height = (int)(Math.random() * Max_height);
			array[i] = height;
			
			bars[i] = new Rectangle(10, height, Color.BLACK);
			box.getChildren().add(bars[i]);
		}
		
		VBox layout = new VBox(50);
		layout.getChildren().addAll(func, box);
		layout.setAlignment(Pos.CENTER);
		scene3 = new Scene(layout, 750, 420);
		window.setScene(scene3);
		
		mergeSortClass merge = new mergeSortClass(bars);
		new Thread(() -> {
			try {
				merge.mergeSort(array);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).start();
		
	}
	
	public void showQuickSort(String type) {
		int[] array = new int[Num_bars];
		Text sortType = new Text(type);
		
		VBox func = new VBox(4);
		func.setAlignment(Pos.CENTER);
		Button back = new Button("back");
		back.setOnAction(e -> window.setScene(scene1));
		func.getChildren().addAll(sortType, back);
		
		HBox box = new HBox(2);
		box.setAlignment(Pos.BASELINE_CENTER);

		
		for (int i = 0; i < Num_bars; i++) {
			int height = (int)(Math.random() * Max_height);
			array[i] = height;
			
			bars[i] = new Rectangle(10, height, Color.BLACK);
			box.getChildren().add(bars[i]);
		}
		
		VBox layout = new VBox(50);
		layout.getChildren().addAll(func, box);
		layout.setAlignment(Pos.CENTER);
		scene4 = new Scene(layout, 750, 420);
		window.setScene(scene4);
		
		quickSortClass quick = new quickSortClass(bars);
		new Thread(() -> {
			try {
				quick.quickSort(array, 0, array.length - 1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).start();
		
	}
	
public static void main(String[] args) {
	launch(args);
		
	}
}

//******************** Sorting Algorithms ********************

class bubbleSortClass {
	private Rectangle[] bars;
	
	public bubbleSortClass(Rectangle[] bars) {
		this.bars = bars;
	}
	
	public void bubbleSort() throws InterruptedException{
		for (int i = 0; i < bars.length-1; i++) {
			for (int j = 0; j < bars.length-1; j++) {
				if(bars[j].getHeight() > bars[j+1].getHeight()) {
					swap(j, j+1);
					Thread.sleep(20);
				}
			}
		}
	}
	private void swap(int i, int j) {
		double h1 = bars[i].getHeight();
		double h2 = bars[j].getHeight();
	
		javafx.application.Platform.runLater(() -> {
			bars[i].setHeight(h2);
			bars[j].setHeight(h1);
		});

		/*	Platform.runLater(() -> {}); -- since we are running a code background,
		 	it safely updates GUI. Basically waiting for thread to be ready
		 	in order for us to see the bars swap */
	}
}
class mergeSortClass {
	private Rectangle[] bars;
	
	 public mergeSortClass(Rectangle[] bars) {
	        this.bars = bars;
	  }
	
	 private void merge(int arr[], int l, int m, int r) throws InterruptedException {
		int n1 = m - l + 1, n2 = r - m;
		int L[] = new int [n1], R[] = new int[n2];
		
		for (int i = 0; i < n1; i++) L[i] = arr[l+i];
		for (int j = 0; j < n2; j++) R[j] = arr [m + 1 + j];
		
		int i = 0, j = 0, k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i++];
				updateBar(k, arr[k]);
				Thread.sleep(20);
			} else {
				arr[k] = R[j++];
				updateBar(k, arr[k]);
				Thread.sleep(20);
			}
			updateBar(k, arr[k]);
			k++;
		}
		
		while (i < n1) {
			arr[k] = L[i++];
			updateBar(k, arr[k]);
			Thread.sleep(20);
			k++;
		}
		while (j < n2) {
			arr[k] = R[j++];
			updateBar(k, arr[k]);
			Thread.sleep(20);
			k++;
		}	
	}
	public void mergeSort(int arr[]) throws InterruptedException {
		for (int size = 1; size < arr.length; size *= 2) {
			for (int left = 0; left < arr.length - size; left += 2*size) {
				int mid = left + size - 1;
				int right = Math.min(left + 2*size-1, arr.length - 1);
				merge(arr, left, mid, right);
			}
		}
	
	}
	 private void updateBar(int i, int h) {
		javafx.application.Platform.runLater(() -> {
			bars[i].setHeight(h);
		});
	}
}

class quickSortClass{
	private Rectangle[] bars;
	
	public quickSortClass(Rectangle[] bars) {
		this.bars = bars;
	}
	
	 private int partition(int[] arr, int low, int high)throws InterruptedException {
		int pivot = arr[high];
		int i = low - 1;
		
		for (int j = low; j <= high - 1; j++) {
			if(arr[j] < pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, high);
		return i + 1;
	}
	
	private void swap(int[] arr, int i, int j) throws InterruptedException {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		
		updateBar(i, arr[i]);
		updateBar(j, arr[j]);
        Thread.sleep(20);
	}
	
	public void quickSort(int[] arr, int low, int high) throws InterruptedException {
		if(low < high) {
			//partition, index of pivot
			int pi = partition(arr, low, high);
			
			quickSort(arr, low, pi - 1);
			quickSort(arr, pi + 1, high);
		}
	}
	
	private void updateBar(int i, int h) {
		javafx.application.Platform.runLater(() -> {
			bars[i].setHeight(h);
		});
	}
	
}
