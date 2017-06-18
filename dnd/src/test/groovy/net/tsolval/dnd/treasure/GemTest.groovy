package net.tsolval.dnd.treasure;

import static org.junit.Assert.*

import org.junit.Test

/**
 * Test processing in Gem class
 * @author tsolval
 */
class GemTest {

	/**
	 * Test method for {@link net.tsolval.dnd.treasure.Gem#toString()}.
	 */
	@Test
	public void testToString() throws Exception {
		Gem tg1 = new Gem(value: 10)
		Gem tg2 = new Gem(value: 10, gem: 'Azurite' )
		Gem tg3 = new Gem(value: 10, gem: 'Azurite', desc: 'opaque mottled deep blue' )
		Gem tg4 = new Gem(value: 10, gem: 'Azurite', unit: 'cp' )
		assert 'Gems worth 10gp each.' == tg1.toString()
		assert 'Azurite worth 10gp each.' == tg2.toString()
		assert 'Azurite (opaque mottled deep blue) worth 10gp each.' == tg3.toString()
		assert 'Azurite worth 10cp each.' == tg4.toString()
	}
}
