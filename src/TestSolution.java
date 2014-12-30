

import org.junit.Assert;
import org.junit.Test;


public class TestSolution {

	@Test
	public void test(){
		Solution sol = new Solution();
		int[] a = {3,2,1};
		Assert.assertEquals(2, (int)sol.binaryFind(a, 1));
		Assert.assertEquals(1, (int)sol.binaryFind(a, 2));
		Assert.assertEquals(0, (int)sol.binaryFind(a, 3));
		Assert.assertEquals(null, sol.binaryFind(a, 4));
		
	}
	
}
