package UI;

public class board {
    public int height, width;
    public String[][] box;

    public board() {

    }

    /*public board(int h, int w, int current[][]) {
        height = h;
        width = w;
        box = new String[height][width];
        for (int i = 0; i < box.length; i++)
            for (int j = 0; j < box[i].length; j++) {
                box[i][j] = " - ";
            }
    }*/
    public void Draw(int w,int h,int arr[][]) {
        height = h;
        width = w;
        box = new String[height][width];
        for (int i = 0; i < box.length; i++)
            for (int j = 0; j < box[i].length; j++) {
                if (arr[i][j] == 0) {
                    box[i][j] = " - ";
                }
                if (arr[i][j] == 1) {
                    box[i][j] = " * ";
                }
            }
        printBoard();
    }
    public void printBoard() {
        System.out.print("    |");
        for (int i = 0; i < box[0].length; i++) {
            System.out.print("    ");
            System.out.print(i + 1);
            System.out.print("    |");
        }
        System.out.println();
        for (int i = 0; i < box.length; i++) {
            System.out.print("----+");
            for (int j = 0; j < box[0].length; j++) {
                System.out.print("---------+");
            }
            System.out.println();
            System.out.print("  " + (i + 1) + " |");
            for (int j = 0; j < box[0].length; j++) {
                if (box[i][j].length() < 10) {
                    int spaces = (9 - box[i][j].length()) / 2;
                    for (int k = 0; k < spaces; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(box[i][j]);
                    for (int k = 0; k < (9 - box[i][j].length()) - spaces; k++) {
                        System.out.print(" ");
                    }
                } else {
                    System.out.print(box[i][j].substring(0, 9));
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
    public void setHeight(int h)
    {
        this.height=h;
    }
    public void setWidth(int w)
    {
        this.width=w;
    }
    public void setAlive(int x, int y, int current[][]) {
        box[x - 1][y - 1] = " * ";
    }

    public void viewState(int current[][]) {

    }
}
    /*private int decide(int i, int j,int current[][]){
        int neighbors = 0;
        if(j > 0){
            if(current[i][j-1]==1) neighbors++;
            if(i>0) if(current[i-1][j-1]==1) neighbors++;
            if(i<height-1) if(current[i+1][j-1]==1) neighbors++;
        }
        if(j < width-1){
            if(current[i][j+1]==1) neighbors++;
            if(i>0) if(current[i-1][j+1]==1) neighbors++;
            if(i<height-1) if(current[i+1][j+1]==1) neighbors++;
        }
        if(i>0) if(current[i-1][j]==1) neighbors++;
        if(i<height-1) if(current[i+1][j]==1) neighbors++;
        if(neighbors == 3) return 1;
        if(current[i][j]==1 && neighbors == 2) return 1;
        return 0;
    }
    public void nextState(int current[][])
    {
        nextstate=new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                nextstate[i][j] = decide(i,j,current);
            }
        }
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                current[i][j] = nextstate[i][j];
                if(current[i][j]==1)
                {
                    box[i][j]="*";
                }
            }
        }
    }*/
