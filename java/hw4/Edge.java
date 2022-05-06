package hw4;



public class Edge<T,V> {
		/*
		 * abstraction function:
		 *                      Edge e represents the source and destination of an edge with weight label.
		 * representation invariant: 
		 * 						edge source and destination have to be in the adjacency list.
		 */
		private V weight;
		private T source;
		private int dupe;
		private T destination;
		/**
		 *	@param: source, destination, edgeLabel to be added to edge list
		 *	@effects: creates an edge object with the respective member variables.
		 *  
		 */
		public Edge(T src, T dest, V wei){
			this.weight = wei;
			this.source = src;
			this.destination = dest;
			this.dupe = 1;
			
		}
		/*
		 * @returns: source member variable
		 */
		public T getSource() {
			return source;
		}
		/*
		 * @returns: weight member variable
		 */
		public V getWeight() {
			return weight;
		}
		/*
		 * @returns: destination member variable
		 */
		public T getDestination() {
			return destination;
		}
		/* @param: wei -> weight
		 * @modifies: this
		 * @effects: changes weight to specified value
		 */
		public void setWeight(V wei) {
			this.weight = wei;
		}
		public void addDupe() {
			this.dupe+=1;
		}
		public int getDupe(){
			return dupe;
		}
		/*
		 * @returns: true if two edges are equal
		 */
		@Override 
		public boolean equals(Object o) {
			
			Edge e = (Edge) o;
			if(e.getSource().equals( this.getSource()) && e.getDestination().equals( this.getDestination())) {
				return true;
			}
			return false;
	}
		@Override
	    public int hashCode() {
			return (int) (this.source).hashCode() * (this.destination).hashCode();
		}
}
