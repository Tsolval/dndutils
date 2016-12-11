/**
 * 
 */
package net.tsolval.dnd.tools;

import static org.junit.Assert.*

import org.junit.Test

/**
 * @author tsolval
 *
 */
class DiceTest {

	/**
	 * Test method for {@link net.tsolval.dnd.tools.Dice#roll(java.lang.String)}.
	 */
	@Test
	public void testRollString() throws Exception {
		Integer[] result = Dice.roll("1d6")
		assert result.size() == 1
		assert result.sum() in 1..6
		result = Dice.roll("20d100")
		assert result.size() == 20
		result.each{ assert it in 1..100 }
		assert result.sum() in 20..2000
	}

	/**
	 * Test method for {@link net.tsolval.dnd.tools.Dice#roll(int, int)}.
	 */
	@Test
	public void testRollIntInt() throws Exception {
		def result = Dice.roll(1,6)
		assert result.size() == 1
		assert result.sum() in 1..6
		result = Dice.roll(2,4)
		assert result.size() == 2
		assert result.sum() in 2..8
		result = Dice.roll(3,8)
		assert result.size() == 3
		assert result.sum() in 3..24
		result = Dice.roll(4,10)
		assert result.size() == 4
		assert result.sum() in 4..40
		result = Dice.roll(5,12)
		assert result.size() == 5
		assert result.sum() in 5..60
		result = Dice.roll(6,20)
		assert result.size() == 6
		assert result.sum() in 6..120
	}
}
