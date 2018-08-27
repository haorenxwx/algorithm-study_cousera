#QuickUnion.py

class QuickUnionUF(object):
	def __init__(self,N,p,q,i,args):
		for i in range(N):
			self.id = {}
			self.id[i] = i
			print(self.id[i])
		self.p = p
		self.q = q
		self.i = i
	def root(self):
		while(self.i != self.id[i]):
			self.i = self.id[i]
			return self.i
	def connected(self):
		return root(self.p)==root(self.q)
	def union(self):
		i = root(self.p);
		j = root(self.q);
		id[i] = j;
	def __getitem__(self, key):
	    return self.id[key]
if __name__ == '__main__':
	union=QuickUnionUF()
	union.__init__(5)

	i=connected()
	i.
	print(" The connection result is %s", i)
	union(1,5)
	j=connected(1,5)
	print(" After union, connection result is %s", j)


Weight union find

public class WeightedQuickUnionFind{
    private int[] id;
    private int[] sz;
    private int[] height; // this is for union-by-height
    private int count; // the number of connected components
    private int[] maximum; // keep track of the maximum object in each connected component
    
    public WeightedQuickUnionFind(int N){
      id = new int[N];
      sz = new int[N];
      height = new int[N];
      maximum = new int[N];
      count = N;
      for (int i = 0; i < N; i++){
        id[i] = i;
        sz[i] = 1;
        height[i] = 1;
        maximum[i] = i;
      }
    }
    
    public boolean isConnected(int p, int q){
      return root(p) == root(q);
    }
    
    private int root(int p){
      while(p != id[p]){
        // id[p] = id[id[p]]; // this line is for path compression
        p = id[p];
      }
      return p;
    }
    
    public boolean isAllConnected(){
      return count == 1;
    }
    
    public int maximumInSameComponent(int p){
        return maximum[root(p)];
    }
    
    public void union(int p, int q){
      int rootP = root(p);
      int rootQ = root(q);
      
      if (rootP == rootQ) return;
      
      if (sz[rootP] >= sz[rootQ]){
          // we link the smaller tree to the bigger tree
          sz[rootP] += sz[rootQ];
          id[rootQ] = rootP;
          // update if the new comers have a bigger maximum
          if (maximum[rootQ] > maximum[rootP]){
              maximum[rootP] = maximum[rootQ];
          }
      }else{
          sz[rootQ] += sz[rootP];
          id[rootP] = rootQ;
          // update if the new comers have a bigger maximum
          if (maximum[rootP] > maximum[rootQ]){
              maximum[rootQ] = maximum[rootP];
          }
      }
      count--; // do not forget decreasing the number of roots
    }
    
    public void unionByHeight(int p, int q){
      int rootP = root(p);
      int rootQ = root(q);
      
      if (rootP == rootQ) return;
      
      if (height[rootP] > height[rootQ]){
          // we link the shorter tree to the bigger tree
          id[rootQ] = rootP;
      }else if(height[rootP] < height[rootQ]){
          // we link the shorter tree to the bigger tree
          id[rootP] = rootQ;
      }else{
          // we link the tree of q to the tree of p
          id[rootQ] = rootP;
          // and do not forget the height increase
          height[rootP]++;
      }
    }
}