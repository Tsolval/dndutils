package net.tsolval.dnd.tools

import java.util.regex.Pattern

class Dice {
	static def roll(int num, int sides) {
		def results = []
		Random rand = new Random()
		(1..num).each {
			results << rand.nextInt(sides) + 1
		}
		results
	}
	
	static def roll(String dice){
		if (!dice) return 1
		def group = (dice =~ /(\d+)d(\d+)/)
		roll(group[0][1].toInteger(), group[0][2].toInteger())
	}
}
