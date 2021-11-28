package Elements;

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
	public void ResetCounter() {
		counter=0;
	}
}
