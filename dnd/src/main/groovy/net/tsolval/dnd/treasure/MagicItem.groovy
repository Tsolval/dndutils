package net.tsolval.dnd.treasure

/**
 * Collect art
 * @author tsolval
 */
class MagicItem {
	def item = ''
	def desc = ''
	def rarity = Rarity.COMMON
	def value = '50-100'
	def unit = 'gp'
	def createdBy = ''
	def history = ''
	def property = ''
	def quirk = ''

	@Override
	public String toString() {
		"Magical Item: ${item}.";
	}
}
