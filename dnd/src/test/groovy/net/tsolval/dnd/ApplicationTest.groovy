/**
 * 
 */
package net.tsolval.dnd;

import static org.junit.Assert.*

import org.junit.Test

/**
 * @author tsolval
 *
 */
class ApplicationTest {

	/**
	 * Test method for {@link net.tsolval.dnd.Application#treasure(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testTreasure() throws Exception {
		Application app = new Application()
		def treasure =app.treasure(1,20)
		println treasure
	}

	/**
	 * Test method for {@link net.tsolval.dnd.Application#horde(java.lang.Object)}.
	 */
	@Test
	public void testHorde() throws Exception {
		Application app = new Application()
		def treasure =app.treasure(1,20)
		println treasure
	}
}
