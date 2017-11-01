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
假装是个能跑的程序