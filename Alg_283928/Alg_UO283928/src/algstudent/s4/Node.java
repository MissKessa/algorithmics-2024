package algstudent.s4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
	private String name;
	private List<Node> neighbors = new ArrayList<Node>();
	private String color;
	
	public Node( String name ) {
		
		this.name = name;
		
	}
	
	public void addNeighbor( Node n ) {
		if( !neighbors.contains(n)) neighbors.add(n);
		
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Node))
			return false;
		Node other = (Node) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return name + "(" + color + ")";
	}

	public Object getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}
	
	
	
}
