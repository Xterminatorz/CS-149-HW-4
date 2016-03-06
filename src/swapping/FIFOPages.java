package swapping;

import java.util.ArrayList;

public class FIFOPages extends PagingSystem {

	private ArrayList<Integer> references = super.generateReferences();
	private ArrayList<Integer> pages = new ArrayList<>();
	private boolean evicted = false;
	private boolean pagedIn = false;
	private int misses = 0;
	private int hits = 0;
	private int removed;
	
	public void managePages()
	{
		while(!references.isEmpty()) 
		{
			evicted = false;
			pagedIn = false;
			int toAdd = references.get(0);
			if(!pages.contains(toAdd))
			{
				if(pages.size() < 4)
				{
					pages.add(toAdd);
					pagedIn = true;
					misses++;
				}
				else
				{
					removed = pages.remove(0);
					pages.add(toAdd);
					evicted = true;
					pagedIn = true;
					misses++;
				}
			}
			else
			{
				hits++;
			}
			System.out.println("Pages: "+ ((pages.size() > 1) ? (pages.get(0) + " ") : "") +
										  ((pages.size() > 2) ? (pages.get(1) + " ") : "") +
										  ((pages.size() > 3) ? (pages.get(2) + " ") : "") +
										  ((pages.size() == 4) ? (pages.get(3) + " ") : "") +
										  ((evicted) ? ("Removed: " + removed + " ") : "") +
										  ((pagedIn) ? ("Loaded: " + toAdd + " ") : ""));
 			
		}
	}
	
	public void calcStats()
	{
		System.out.printf("Had %d hits and %d misses.\n", hits, misses);
	}
	
}
