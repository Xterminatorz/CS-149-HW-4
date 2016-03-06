package swapping;

import java.util.ArrayList;
import java.util.Random;

public class PagingSystem {
	private final Random R = new Random(0);
	private ArrayList<Integer> references = new ArrayList<>();
	
	public ArrayList<Integer> generateReferences()
	{
		references.clear();
		int initial = R.nextInt(10);
		references.add(initial);
		for(int i = 0; i < 99; i++)
		{
			int r = R.nextInt(10);
			if(0 <= r && r < 7)
			{
				initial = (initial + (R.nextInt(2) - 1)) % 10;
			}
			else
			{
				initial = (initial + (R.nextInt(7) + 2)) % 10;
			}
			references.add(initial);
		}
		return references;
	}

}
