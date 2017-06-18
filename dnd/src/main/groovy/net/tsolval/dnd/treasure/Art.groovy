package net.tsolval.dnd.treasure

/**
 * Collect art
 * @author tsolval
 */
class Art {
	def count = 0
	def art = ''
	def desc = ''
	def value = 0
	def unit = 'gp'

	@Override
	public String toString() {
		"${count} ${art?art:'Art'}${desc?' ('+desc+') ':' '}worth ${value}${unit} each.";
	}
}
