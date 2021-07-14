import java.util.* ;
import java.lang.*;



public class MMBurgers implements MMBurgersInterface {
	
	//class QueueHeap
	public class QueueHeaps{
		
		int Size;
		public class queueIdNode{
			int id;
			int len;
			queueIdNode(int id){
				this.id= id;
				len = 0;
				
			}
		}
		public queueIdNode[] queueIdHeap ;
		public queueIdNode[] Q; 
		public QueueHeaps(int k){
			
			Size =k; 
			Q= new queueIdNode[k];
			queueIdHeap = new queueIdNode[k];
			for (int i =0; i< k; i++) {
				queueIdHeap[i] = new queueIdNode(i+1); 
				Q[i] = queueIdHeap[i];
			}
		}
		
		
		private void swap(int s, int t){
			queueIdNode temp;
			temp = queueIdHeap[s];
			queueIdHeap[s] = queueIdHeap[t];
			queueIdHeap[t] = temp;
		}
		private boolean isLeaf(int p)
		{
			if (p >= ((Size -1)/ 2) &&  p <= Size-1) 
				return true;
        
			return false;
		}
  

		public void Heapify(int p){
	  
		   
			if (!isLeaf(p)) {
				
				if (queueIdHeap[p].len == queueIdHeap[2*p+1].len || queueIdHeap[p].len == queueIdHeap[2*p+2].len) {
					
					if (queueIdHeap[p].id > queueIdHeap[2*p+2].id && queueIdHeap[p].id > queueIdHeap[2*p+1].id){
						
						if (queueIdHeap[2*p+1].id > queueIdHeap[2*p+2].id){
							swap(p, 2*p+2);
							Heapify(2*p+2);
						}
						else{
							swap(p, 2*p+1);
							Heapify(2*p+1);
					}
					}
					
					if (queueIdHeap[p].id > queueIdHeap[2*p+2].id){
						
						swap(p, 2*p+2);
						Heapify(2*p+2);
						
						}
						
					else if (queueIdHeap[p].id > queueIdHeap[2*p+1].id){
						
						swap(p, 2*p+1);
						Heapify(2*p+1);
						
						
					}
					
					
				}
				if (queueIdHeap[p].len > queueIdHeap[2*p+1].len || queueIdHeap[p].len > queueIdHeap[2*p+2].len) {
	  
				   
					if (queueIdHeap[2*p+1].len < queueIdHeap[2*p+2].len) {
						swap(p, 2*p+1);
						Heapify(2*p+1);
					}
	  
					
					else if(queueIdHeap[2*p+1].len > queueIdHeap[2*p+2].len) {
						swap(p, 2*p+2);
						Heapify(2*p+2);
					}
					else{
						if (queueIdHeap[2*p+1].id > queueIdHeap[2*p+2].id){
							swap(p, 2*p+2);
							Heapify(2*p+2);
						}
						else{
							swap(p, 2*p+1);
							Heapify(2*p+1);
						}
					}
				}
			}
			
		}
		private int parent(int i)
		{

        if (i == 0) 
            return 0;
        
 
        return (i - 1) / 2;
		}
		public void Heapify_up(int P){
			if (P > 0 && queueIdHeap[parent(P)].len >= queueIdHeap[P].len)
			{	
				if(queueIdHeap[parent(P)].len > queueIdHeap[P].len)
					swap(P, parent(P));
				if(queueIdHeap[parent(P)].len == queueIdHeap[P].len && queueIdHeap[(parent(P))].id > queueIdHeap[P].id)
					swap(P, parent(P));
				
				Heapify_up(parent(P));
			}
		}
		}
		
		
	//class Node that is stored in AVL tree
	public class Node {
		public int key;
		private int balance;
		private int height;
		private Node left, right, parent;
		public int arrivaltime;
		public int ordercompletion;
		public int departure = -1;
		public int numburgers;
		public int QID; 
		Node(int k, Node p, int t1, int t2, int m, int id) {
		  key = k;
		  parent = p;
		  arrivaltime = t1;
		  ordercompletion = t2;
		  numburgers = m;
		  QID = id;
		  left =null;
		  right= null;
			
		}
	  }
	// class ordernode used for storing griddle info and pending order info
	public class OrderNode{
			
			int ordertime;
			Node customer;
			int numburgersleft;
			//int reachedgriddletime;
			int queID;
			public OrderNode(Node c){
				customer = c;
				ordertime = c.ordercompletion;
				numburgersleft = c.numburgers;
				//reachedgriddletime = -1; 
				queID = c.QID; 
			}
			
		}
	public class GriddleNode{
		Node customer;
		int reachedgriddletime;
		public GriddleNode(Node c , int t){
			customer = c;
			reachedgriddletime =t;
		}
		
	}
	// Class OrderHeaps
	public class OrderHeaps{
		
		
		public Vector<OrderNode> V;
 
    
		public OrderHeaps() {
			V = new Vector<OrderNode>();
		}
		
		void swap(int x, int y)
		{
			
			OrderNode temp = V.get(x);
			V.setElementAt(V.get(y), x);
			V.setElementAt(temp, y);
		}
 
		
		private void heapify_down(int i)
		{

			int left = 2*i+1;
			int right = 2*i+2;
	 
			int smallest = i;
	 
	
	
			if (left < size() && V.get(left).ordertime <= V.get(i).ordertime) {
				if(V.get(left).ordertime  == V.get(i).ordertime && V.get(i).queID < V.get(left).queID )
					smallest = left;
				else if(V.get(left).ordertime < V.get(i).ordertime)
					smallest=left;
			}
			
			if (right < size() && V.get(right).ordertime <= V.get(smallest).ordertime) {
				if(V.get(right).ordertime  == V.get(smallest).ordertime && V.get(smallest).queID < V.get(right).queID )
					smallest = right;
				else if(V.get(right).ordertime < V.get(smallest).ordertime)
					smallest=right;
			}
			
	 
			if (smallest != i)
			{
				
				swap(i, smallest);
	 
				
				heapify_down(smallest);
			}
			
			
		}
		
		private int parent(int i)
		{

        if (i == 0) {
            return 0;
        }
 
        return (i - 1) / 2;
		}
	 
    
		private void heapify_up(int i)
		{
		   
			if (i > 0 && V.get(parent(i)).ordertime >= V.get(i).ordertime)
			{	
				if(V.get(parent(i)).ordertime > V.get(i).ordertime)
					swap(i, parent(i));
				if(V.get(parent(i)).ordertime == V.get(i).ordertime && V.get(parent(i)).queID < V.get(i).queID)
					swap(i, parent(i));
				
				heapify_up(parent(i));
			}
		}
 
    
		public int size() {
			return V.size();
		}
 
    
		public void insert(OrderNode ord)
		{
		   
			V.addElement(ord);
	 

			int index = size() - 1;
			heapify_up(index);
		}
 
		
		public OrderNode pop()
		{
			
			OrderNode topOrder = V.get(0);    
	 
				
				V.setElementAt(V.lastElement(), 0);
				V.remove(size() - 1);
	 
				heapify_down(0);
	 
				return topOrder;
			
		
		}
	 
		
		public OrderNode peek()
		{
			return V.get(0);    
	}
	 
			
		}
	
	//class AVL tree
	public class AVLTree {

		public Node root;

	  

	  public void insert(int key, int t1, int t2, int m, int id) {
		if (root == null) root = new Node(key, null, t1, t2, m, id);
		else {
		  Node n = root;
		  Node parent;
		  while (true) {

			parent = n;

			boolean goLeft = n.key > key;
			n = goLeft ? n.left : n.right;

			if (n == null) {
			  if (goLeft) {
				parent.left = new Node(key, parent, t1, t2, m, id);
			  } else {
				parent.right = new Node(key, parent, t1, t2, m, id);
			  }
			  rebalance(parent);
			  break;
			}
		  }
		}
		
	  }

	  private void delete(Node node) {
		if (node.left == null && node.right == null) {
		  if (node.parent == null) root = null;
		  else {
			Node parent = node.parent;
			if (parent.left == node) {
			  parent.left = null;
			} else parent.right = null;
			rebalance(parent);
		  }
		  return;
		}
		if (node.left != null) {
		  Node child = node.left;
		  while (child.right != null) child = child.right;
		  node.key = child.key;
		  delete(child);
		} else {
		  Node child = node.right;
		  while (child.left != null) child = child.left;
		  node.key = child.key;
		  delete(child);
		}
	  }

	  public void delete(int delKey) {
		if (root == null) return;
		Node node = root;
		Node child = root;

		while (child != null) {
		  node = child;
		  child = delKey >= node.key ? node.right : node.left;
		  if (delKey == node.key) {
			delete(node);
			return;
		  }
		}
	  }

	  private void rebalance(Node n) {
		setBalance(n);

		if (n.balance == -2) {
		  if (height(n.left.left) >= height(n.left.right)) n = rotateRight(n);
		  else n = rotateLeftThenRight(n);

		} else if (n.balance == 2) {
		  if (height(n.right.right) >= height(n.right.left)) n = rotateLeft(n);
		  else n = rotateRightThenLeft(n);
		}

		if (n.parent != null) {
		  rebalance(n.parent);
		} else {
		  root = n;
		}
	  }

	  private Node rotateLeft(Node a) {

		Node b = a.right;
		b.parent = a.parent;

		a.right = b.left;

		if (a.right != null) a.right.parent = a;

		b.left = a;
		a.parent = b;

		if (b.parent != null) {
		  if (b.parent.right == a) {
			b.parent.right = b;
		  } else {
			b.parent.left = b;
		  }
		}

		setBalance(a, b);

		return b;
	  }

	  private Node rotateRight(Node a) {

		Node b = a.left;
		b.parent = a.parent;

		a.left = b.right;

		if (a.left != null) a.left.parent = a;

		b.right = a;
		a.parent = b;

		if (b.parent != null) {
		  if (b.parent.right == a) {
			b.parent.right = b;
		  } else {
			b.parent.left = b;
		  }
		}

		setBalance(a, b);

		return b;
	  }

	  private Node rotateLeftThenRight(Node n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	  }

	  private Node rotateRightThenLeft(Node n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	  }

	  private int height(Node n) {
		if (n == null) return -1;
		return n.height;
	  }

	  private void setBalance(Node... nodes) {
		for (Node n : nodes) {
		  reheight(n);
		  n.balance = height(n.right) - height(n.left);
		}
	  }

	  public void printBalance() {
		printBalance(root);
	  }

	  private void printBalance(Node n) {
		if (n != null) {
		  printBalance(n.left);
		  System.out.printf("%s ", n.balance);
		  printBalance(n.right);
		}
	  }

	  private void reheight(Node node) {
		if (node != null) {
		  node.height = 1 + Math.max(height(node.left), height(node.right));
		}
	  }

	  public Node search(int key) {
		Node result = searchHelper(this.root, key);
		if (result != null) return result;

		return null;
	  }

	  private Node searchHelper(Node root, int key) {
		// root is null or key is present at root
		if (root == null || root.key == key) return root;

		// key is greater than root's key
		if (root.key > key)
		  return searchHelper(root.left, key); // call the function on the node's left child

		// key is less than root's key then
		// call the function on the node's right child as it is greater
		return searchHelper(root.right, key);
	  }

	  
	}
	
	
	//1-order completion
	//2-griddleburgs pop
	//3-griddleburg push
	//4-customerarrival
	//5-depature
	//6-all queries
	int ind;
	
	int K;
	int M;
	int GlobalTime=0;
	Queue<GriddleNode> Griddleburgs = new LinkedList<>();;
	QueueHeaps queues ;
	AVLTree customers = new AVLTree();
	OrderHeaps orders = new OrderHeaps(); 
	//Vector<queueIdNode> Q =new 
	
    public boolean isEmpty(){
        //your implementation
		for (int i=0 ; i< K; i++){
			if(queues.queueIdHeap[i].len !=0){
				//System.out.println(queues.queueIdHeap[i].len);
				
				//System.out.println(false);
				return false;
			}}
		if (Griddleburgs.size() ==0 && orders.size() ==0 ){
			//System.out.println(true);
			//System.out.println(customers.search(4).QID);
			//System.out.println(customers.search(4).ordercompletion);
			return true; 
			
		}
		//System.out.println(false);
		return false;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
	
	
    
    public void setK(int k) throws IllegalNumberException{
        //your implementation
		if (k<=0)
			throw new IllegalNumberException("number of counters cant be non-positive");
		else
			K=k;
			queues = new QueueHeaps(K);
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }   
    
    public void setM(int m) throws IllegalNumberException{
        //your implementation
		if (m<=0)
			throw new IllegalNumberException("max burgers on griddle cant be non-positive");
		else
			M=m;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
	private void traversal(Node root, int t){
		if(root==null)
			return;
		if(root.ordercompletion> GlobalTime && root.ordercompletion <=t){
			queues.Q[root.QID-1].len--;
			//System.out.println("root.QID="+Integer.toString(root.QID)+" queues.Q[root.QID-1].id " +Integer.toString(queues.Q[root.QID-1].id));
				for(int i=0; i<K; i++){
					if(queues.queueIdHeap[i].id == root.QID)
						ind=i;
					//System.out.println("queue "+Integer.toString(queues.queueIdHeap[i].id)+" length "+Integer.toString(queues.queueIdHeap[i].len)+" time "+Integer.toString(t)+" customer "+Integer.toString(root.key));
				}
				queues.Heapify_up(ind);
		}
		//System.out.println("queue "+Integer.toString(customers.search(36).ordercompletion);
		traversal(root.left,t);
		
		traversal(root.right,t);
		//queues.Heapify(0);
		return;
			
	}

    public void advanceTime(int t) throws IllegalNumberException{
        //your implementation
		if(t < GlobalTime)
			throw new IllegalNumberException("cannot advance to past");
		else{
			//if 
			if (t > GlobalTime){
			
		
			traversal(customers.root,t );
			
			int time = GlobalTime;
			GlobalTime = t;
			
			GriddleNode G=null;
			int gt;
			if ( Griddleburgs.size() !=0 && orders.size() !=0 ){
			//while(GlobalTime >= orders.peek().ordertime && (GlobalTime >= (Griddleburgs.peek().reachedgriddletime +10) )){	
			while(orders.size()!=0 && Griddleburgs.size()!=0 && (GlobalTime >= (Griddleburgs.peek().reachedgriddletime +10) || (GlobalTime >= orders.peek().ordertime && Griddleburgs.size()<M)) ){
				
				//change
				while (orders.size()!=0&& Griddleburgs.size()!=0&&(((Griddleburgs.peek().reachedgriddletime+10) >= orders.peek().ordertime && Griddleburgs.size()==M) || (Griddleburgs.peek().reachedgriddletime+10) <=orders.peek().ordertime) && (Griddleburgs.peek().reachedgriddletime+10) <= GlobalTime  ){
					//if (((Griddleburgs.peek().reachedgriddletime+10) >= orders.peek().ordertime && Griddleburgs.size()==M) || (Griddleburgs.peek().reachedgriddletime+10) <=orders.peek().ordertime)
					time = Griddleburgs.peek().reachedgriddletime+10;
					
					//System.out.println(time);
					//System.out.println("griddle size1" + Integer.toString(Griddleburgs.size()));
					G= Griddleburgs.remove();
					//System.out.println("griddle size2" + Integer.toString(Griddleburgs.size()));
					G.customer.departure = time+1;
					
				}if(Griddleburgs.size()!=0){
				if (orders.size()!=0 && orders.peek().ordertime < (Griddleburgs.peek().reachedgriddletime+10) && orders.peek().ordertime <= GlobalTime){
					//CHANGE
					while( orders.size()!=0 && orders.peek().ordertime < (Griddleburgs.peek().reachedgriddletime+10) && Griddleburgs.size() < M && orders.peek().ordertime <= GlobalTime){
						//System.out.println("ordersize" + Integer.toString(orders.size()));
						//if(orders.peek().numburgersleft == orders.peek().customer.numburgers && Griddleburgs.size() < M){
						if(G==null){
							time = orders.peek().ordertime;
							
						}
						else{
							time= G.reachedgriddletime+10;
							//queues.Q[orders.peek().customer.QID-1].len--;
						}
						//if(orders.peek().numburgersleft == orders.peek().customer.numburgers && M> Griddleburgs.size()){
							//queues.Q[orders.peek().customer.QID-1].len--;
							
							//for(int i=0; i<K; i++){
								//if(queues.queueIdHeap[i].id ==orders.peek().customer.QID)
									//ind=i;
							//}
							//System.out.println("ind1 " + Integer.toString(ind));
							//queues.Heapify_up(ind);
						//}
						while( orders.peek().numburgersleft !=0 && M > Griddleburgs.size()){
							
							Griddleburgs.add(new GriddleNode(orders.peek().customer , time));
							orders.peek().numburgersleft -- ;
						}
						if(orders.peek().numburgersleft==0 ) 
							orders.pop();
						//System.out.println("orderpeek " + Integer.toString(orders.peek().customer.ordercompletion));
					}
					 
				}}
			//}
			}
			} if( orders.size() !=0 && Griddleburgs.size()==0 ){
				
				while( orders.size()!=0 && orders.peek().ordertime <= GlobalTime && Griddleburgs.size() < M){
						//System.out.println(3);
						
						//if(orders.peek().numburgersleft == orders.peek().customer.numburgers && Griddleburgs.size() < M)
							time = orders.peek().ordertime;
							//queues.Q[orders.peek().customer.QID-1].len--;
							//for(int i=0; i<K; i++){
								//if(queues.queueIdHeap[i].id ==orders.peek().customer.QID)
									//ind=i;
									
							//}
							//System.out.println("queid "+Integer.toString(queues.queueIdHeap[0].id));
							//System.out.println("ind " + Integer.toString(ind));
							//queues.Heapify_up(ind);
							//System.out.println("qu len for customer " +Integer.toString(orders.peek().customer.key) +" is " + queues.Q[orders.peek().customer.QID-1].len+" - "+Integer.toString(queues.Q[orders.peek().customer.QID-1].id));
						//else
							//time = griddleburgs
						
						while( orders.peek().numburgersleft !=0 && M > Griddleburgs.size()){
							//System.out.println(4);
							Griddleburgs.add(new GriddleNode(orders.peek().customer , time));
							orders.peek().numburgersleft -- ;
							//System.out.println(orders.peek().numburgersleft);
						}
						if(orders.peek().numburgersleft==0 ) 
							orders.pop();
					}
				
			} if(Griddleburgs.size()!=0){
				while(Griddleburgs.size()!=0 && Griddleburgs.peek().reachedgriddletime+10<=GlobalTime){
				time = Griddleburgs.peek().reachedgriddletime+10;
					//System.out.println(Griddleburgs.size());
					G= Griddleburgs.remove();
					G.customer.departure = time+1;
					//System.out.println("customer "+ Integer.toString(G.customer.key)+ " "+ Integer.toString(GlobalTime));
				}
			}
			
		}}
		
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException{
        //your implementation
		if(t < GlobalTime)
			throw new IllegalNumberException("customer cannot arrive in past"); 
		advanceTime(t);
		int qid =  queues.queueIdHeap[0].id;
		int t2 = GlobalTime+qid* (queues.queueIdHeap[0].len +1);
		queues.queueIdHeap[0].len ++;
		queues.Heapify(0);
		
		customers.insert(id, t, t2 , numb, qid);
		Node C= customers.search(id);
		
		orders.insert(new OrderNode(C));
		//System.out.println("queue for customer with id "+ Integer.toString(C.key)+"  " +Integer.toString(C.QID));
		
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 
	
	//Boolean T = false;
	//public int customerState(int id, int t) throws IllegalNumberException{
		//T= true;
		
	//}

    public int customerState(int id, int t) throws IllegalNumberException{
        //your implementation
		if(t < GlobalTime)
			throw new IllegalNumberException("customer State cannot be known for the past");
		
		advanceTime(t);
		Node customerNode = customers.search(id);
		
		if(customerNode == null)
			return 0;
		
		else{
			if(GlobalTime < customerNode.ordercompletion){
				//System.out.println(customerNode.QID);
				return customerNode.QID;
			}
			else if(GlobalTime >= customerNode.ordercompletion && customerNode.departure == -1 ){
				//System.out.println(K+1);
				return K+1;
			}
			else {
				//System.out.println(K+2);
				return K+2;
			}
				
		}
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleState(int t) throws IllegalNumberException{
        //your implementation
		
		
		if( GlobalTime > t)
			throw new IllegalNumberException("griddleState cannot be obtained from past");
		
		advanceTime(t);
		//System.out.println(Griddleburgs.size());
		//System.out.println(Griddleburgs.peek().customer.key);
		return Griddleburgs.size();
		
		
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    public int griddleWait(int t) throws IllegalNumberException{
        //your implementation
		if(t< GlobalTime) 
			throw new IllegalNumberException(" griddle state cannot be known for past");
		
		advanceTime(t);
		int count=0;
		for( int i=0; i< orders.size() ; i++){
			if( orders.V.get(i).ordertime <= GlobalTime )
				count+=orders.V.get(i).numburgersleft;
			
		}
		//System.out.println(order);
		//System.out.println(count);
		return count;
		
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }


	
	//o(logN)
    public int customerWaitTime(int id) throws IllegalNumberException{
        //your implementation
		Node c = customers.search(id);
		if( c == null )
			throw new IllegalNumberException(" id for customerWaitTime hasnt arrived yet");
		if( c.departure == -1 )
			throw new IllegalNumberException(" customerWaitTime cannot be called before end of all simulations");
		//System.out.println(c.departure - c.arrivaltime);
		return c.departure - c.arrivaltime ;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    }
	
	
	
	int sum=0;	
	int count=0;
	private int inorder(Node P){
		
		
		if(P==null)
			return 0;
		
		inorder(P.left);
		
		
		sum+= P.departure-P.arrivaltime;
		
		
		inorder( P.right);
		count++;
		return sum;
		
	}
	
	//O(num of customers)
	public float avgWaitTime(){
        //your implementation
		sum= inorder(customers.root);
		float ans= ((float)sum)/((float)count);
		
		//String s = String.valueOf(ans);
		//for(int i=0 ; i< s.length(); i++){
			//if(s.charAt(i)=='.'){
				//if(s.substring(i).length()==2)
					//s= s+Character.toString('0');
			//}
		//}
		
		//float f= (float)ans;
		//System.out.println(f);
		return (ans) ;
	    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");	
    } 

    
}
