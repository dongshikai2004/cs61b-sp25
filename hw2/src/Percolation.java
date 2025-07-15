import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private int n;
    private WeightedQuickUnionUF a;
    private boolean[][] grid;
    private int cnt;
    private final static int[][] d ={{1,0},{-1,0},{0,1},{0,-1}};
    public Percolation(int N) {
        // TODO: Fill in this constructor.
        this.n=N;
        this.a=new WeightedQuickUnionUF(N*N);
        this.grid=new boolean[N][N];
        this.cnt=0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        grid[row][col]=true;
        cnt++;
        for(int[] i:d){
            if(row+i[0]>=n||row+i[0]<0||col+i[1]>=n||col+i[1]<0)continue;
            if(grid[row+i[0]][col+i[1]]){
                a.union(shiftIndex(row,col),shiftIndex(row+i[0],col+i[1]));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        for(int i=0;i<n;++i){
            if(!grid[0][i])continue;
            if(a.connected(shiftIndex(row,col),shiftIndex(0,i))){
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return cnt;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for(int i=0;i<n;++i){
            if(isFull(n-1,i)){
                return true;
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private int shiftIndex(int row,int col){
        return row*n+col;
    }
}
