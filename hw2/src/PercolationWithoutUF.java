public class PercolationWithoutUF {
    // TODO: Add any necessary instance variables.
    private boolean[][] grid;
    private int cnt;
    private boolean[][] fullGrid;
    private int N;
    private final static int[][] d ={{1,0},{-1,0},{0,1},{0,-1}};
    public PercolationWithoutUF(int N) {
        // TODO: Fill in this constructor.
        this.grid=new boolean[N][N];
        this.fullGrid=new boolean[N][N];
        this.cnt=0;
        this.N=N;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        grid[row][col]=true;
        UpdateFullGrid();
        cnt++;
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        return fullGrid[row][col];
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return cnt;
    }

    public boolean percolates() {
        // TODO: Fill in this method.
        for(int i=0;i<N;++i){
            if(isFull(N-1,i)){
                return true;
            }
        }
        return false;
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    private void UpdateFullGrid(){
        boolean[][] vis=new boolean[N][N];
        for(int i=0;i<N;++i){
            if(grid[0][i]){
                fullGrid[0][i]=true;
                UpdateFullGrid(vis,0,i);
            }
        }

    }
    private void UpdateFullGrid(boolean[][] vis,int row,int col){
        if(vis[row][col])return;
        vis[row][col]=true;
        for(int[] i:d){
            if(row+i[0]>=N||row+i[0]<0||col+i[1]>=N||col+i[1]<0)continue;
            if(grid[row+i[0]][col+i[1]]){
                fullGrid[row+i[0]][col+i[1]]=true;
                UpdateFullGrid(vis,row+i[0],col+i[1]);
            }
        }
    }
}
