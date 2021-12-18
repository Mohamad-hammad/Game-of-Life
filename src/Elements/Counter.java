package Elements;

//Class for keeping track of generations
public class Counter {
	int counter;
	Counter(){
		counter=0;
	}
	public void IncCounter() {
		counter++;
	}
	public int GetCounter() {
		return counter;
	}
	public void SetCounter(int s) {
		counter=s;
	}
	public void ResetCounter() {
		counter=0;
	}
}
