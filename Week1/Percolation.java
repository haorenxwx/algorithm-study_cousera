//Percolation.java

public class Percolation{
	private boolean[][] grid;
	private int N;

	private WeightedQuickUnionUF qu;
	//if connect to the bottom, it will back wash, so it dosen't connect to the bottom 
	private WeightedQuickUnionUF qu2;

	private int VIRTUAL_TOP_KEY;
	private int VIRTUAL_BOTTOM_KEY;

	public Percolation(int N) {
		this.N = N;
		this.grid = new boolean[N][N];
		int maxIndex = 0;
		for (i = 0; i<N; i++){
			for (int j = 0; j<N; j++){
				grid[i][j] = false;
				maxIndex = xytoD(i+1,j+1);
			}
		}//initialize
		VIRTUAL_TOP_KEY = maxIndex+1;
		VIRTUAL_BOTTOM_KEY = maxIndex+2;// those 2 keys a re additional keys
		qu = new WeightedQuickUnionUF(VIRTUAL_BOTTOM_KEY+1);
		qu2 = new WeightedQuickUnionUF(VIRTUAL_BOTTOM_KEY+1);

	}
	private int xytoD(int x,int y){
		return x+(y*N);
	}

	public boolean isOpen(int i,int j){
		validateIndex(i,j);
		return grid[j-1][i-1];
	}

	public boolean isFull(int i, int j){
		validateIndex(i,j);
		return qu2.connected(VIRTUAL_TOP_KEY,xytoD(i,j))

	}

	public void open(int i, int j){
		grid[j-1][i-1] == true;
		if (!isLeftEdge(j)){
			union(i,j,0,-1);//connect to left square
		}
		if (!isRightEdge(j)){
			union(i,j,0,+1);//connecct to right square
		}
		if (!isTopEdge(i)){
			union(i,j,-1,0);//connect to the top cell
		}else{
			unionVirtual(i,j,VIRTUAL_TOP_KEY,false);//conenct to virtual
		}
		if (!isBottomEdge(i)){
			union(i,j,1,0);
		}else{
			unionVirtual(i,j,VIRTUAL_BOTTOM_KEY,true);
		}
	}
	public boolean percolates(){
		return qu.connected(VIRTUAL_BOTTOM_KEY,VIRTUAL_TOP_KEY);
		//test if the bottom and top are connected
	}
	/**
	*connect to adjacent cell given coordinates and offset
	* @param i
	* @param j
	* @param rowOffset
	* @param columnOffset
	*/
	private void union(int i, int j, int rowOffset, int columnOffset){
		final int currentKey = xytoD(i,j);
		final int column2 = j+columnOffset;
		final int row2 = i+rowOffset;
		if (isOpen(row2,column2)){
			qu.union(currentKey,xytoD(row2,column2))// call the union inside WeightUnion API
			qu2.union(currentKey,xytoD(row2,column2))
		}
	}

	/**
	* Connect to virtual top and bottom in qu
	* qu2 has no virtual bottom to avoid backwash
	* @param i
	* @param j
	* @param virtualkey
	* @param bottom
	*/

	private void unionVirtual(int i, int j, int virtualKey, boolean bottom){
		final int currentKey = xytoD(i,j);
		qu.union(currentKey,virtualKey);
		if (!bottom){
			qu2.union(currentKey,virtualKey);
		}
	}

	/**
	* Valid index are only between 1 and N
	*
	* @param x
	* @param y
	* @return
	*/
	private boolean isValid(int x, int y){
		return x >=1 && y>=1 && x <=N && y <= N;
	}

	private void validateIndex(int x, int y){
		if(!isValid(x,y)){
			throw new IndexOutOfBoundException(
				String.format("N:%d x:%d y:%d", N, x, y));
		}
	}

	private boolean isBottomEdge(int i){
		return i==N;
	}

	private boolean isTopEdge(int i){
		return i==1;
	}

	private boolean isRightEdge(int j){
		return j == N;
	}

	private boolean isLeftEdge(int j){
		return j==1;
	}

	public static void main(String[] args){
		test2();
	}

	private static void test2(){
		final Percolation p = new Percolation(3);
		System.out.println("p.isOpen(1,2) = "+p.isOpen(1,2));
		p.open(1,2);
		System.out.println("p.isOpen(1,2) = "+p.isOpen(1,2));

		System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
        p.open(2, 2);
        System.out.println("p.isOpen(2,2) = " + p.isOpen(2, 2));
        System.out.println("p.isFull(2, 2) = " + p.isFull(2, 2));

		System.out.println("p.isOpen(3,1) = " + p.isOpen(3, 2));
        p.open(3, 2);
        System.out.println("p.isOpen(3,1) = " + p.isOpen(3, 2));
        p.isFull(3, 2));        

		System.out.println("p.percolates() = "+ p.percolates());
	}


}

