package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // fill this in
        if (side == Side.NORTH) {
            // Don't you dare try to write all of your
            // code in this method. You will want to write
            // helper methods. And those helper methods should
            // have helper methods.
            tiltUp(board);
            return;
        } else if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
            return;
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            return;
        } else { // SOUTH
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateRight(board);
            rotateRight(board);
            return;
        }
    }
    public static void tiltUp(int[][] board){
        for(int i=0;i<board[0].length;++i){
            int m=0;
            for(int j=1;j<board.length;++j){
                int k=j-1;
                while(k>=m && board[k][i]==0){
                    board[k][i]=board[k+1][i];
                    board[k+1][i]=0;
                    k--;
                }
                if(k>=m&&board[k][i]==board[k+1][i]){
                    board[k][i]*=2;
                    board[k+1][i]=0;
                    m=k+1;
                }
            }
        }
    }
}
