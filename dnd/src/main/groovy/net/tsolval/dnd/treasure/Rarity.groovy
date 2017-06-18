package net.tsolval.dnd.treasure

/**
 * Rarity
 * @author tsolval
 */
enum Rarity {
	COMMON('Common'), 
	UNCOMMON('Uncommon'), 
	RARE('Rare'), 
	VERY_RARE('Very rare'), 
	LEGENDARY('Legendary')

	def desc

	Rarity(def desc){
		this.desc = desc
	}

	public String toString() {
		this.desc
	}
}