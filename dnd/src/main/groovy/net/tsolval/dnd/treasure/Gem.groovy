package net.tsolval.dnd.treasure

/**
 * Collect gems
 * @author tsolval
 */
class Gem {
	def count = 0
	def gem = ''
	def desc = ''
	def value = 0
	def unit = 'gp'

	@Override
	public String toString() {
		"${count} ${gem?gem:'Gems'}${desc?' ('+desc+') ':' '}worth ${value}${unit} each.";
	}
}
