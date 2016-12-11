/**
 * 
 */
package net.tsolval.dnd.treasure;

import static org.junit.Assert.*

import org.junit.Test

/**
 * @author tsolval
 *
 */
class TreasureGeneratorTest {

	/**
	 * Test method for {@link net.tsolval.dnd.treasure.TreasureGenerator#build(java.lang.Object, java.lang.Object)}.
	 */
	@Test
	public void testBuild() throws Exception {
		def treasure = TreasureGenerator.build(4, 20);
		assert treasure.cp in 0..(5*6*1*20)
		assert treasure.sp in 0..(4*6*1*20)
		assert treasure.ep in 0..(3*6*20)
		assert treasure.gp in 0..(3*6*20)
		assert treasure.pp in 0..(1*6*20)
		assert treasure.values().sum() in (1*6*20)..(5*6*20)

		treasure = TreasureGenerator.build(5, 20);
		assert treasure.cp in 0..(4*6*100*20)
		assert treasure.sp in 0..(6*6*10*20)
		assert treasure.ep in 0..(3*6*10*20)
		assert treasure.gp in 0..(4*6*10*20)
		assert treasure.pp in 0..(3*6*20)
		assert treasure.values().sum() in (3*6*20)..(4*6*100*20)
		
		
	}

	/**
	 * Test method for {@link net.tsolval.dnd.treasure.TreasureGenerator#getMagicItemC()}.
	 */
	@Test
	public void testGetMagicItemC() throws Exception {
		println TreasureGenerator.getMagicItemA()
		println TreasureGenerator.getMagicItemB()
		println TreasureGenerator.getMagicItemC()
		println TreasureGenerator.getMagicItemD()
		println TreasureGenerator.getMagicItemE()
		println TreasureGenerator.getMagicItemF()
		println TreasureGenerator.getMagicItemG()
		println TreasureGenerator.getMagicItemH()
		println TreasureGenerator.getMagicItemI()
	}

	/**
	 * Test method for {@link net.tsolval.dnd.treasure.TreasureGenerator#buildHoardCR0()}.
	 */
	@Test
	public void testBuildHoardCR0() throws Exception {
		println TreasureGenerator.buildHoardCR0()
		println TreasureGenerator.buildHoardCR5()
		println TreasureGenerator.buildHoardCR11()
		println TreasureGenerator.buildHoardCR17()
	}

	/**
	 * Test method for {@link net.tsolval.dnd.treasure.TreasureGenerator#buildHoardCR17()}.
	 */
	@Test
	public void testBuildHoardCR17() throws Exception {
		def hoard = TreasureGenerator.buildHoardCR17()
		if (hoard.magic) {
			assert hoard.magic.size() in 1..8
		}
	}
}
