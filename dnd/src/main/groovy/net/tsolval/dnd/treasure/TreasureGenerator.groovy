package net.tsolval.dnd.treasure

import net.tsolval.dnd.tools.Dice

class TreasureGenerator {
	
	static def build(def challengeRating, def numberOfCreatures) {
		def cr = challengeRating ? challengeRating.toInteger() : 0
		switch(cr) {
			case 0..4:
				buildCR0(numberOfCreatures)
				break
			case 5..10:
				buildCR5(numberOfCreatures)
				break
			case 11..16:
			    buildCR11(numberOfCreatures)
				break
			default:
				buildCR17(numberOfCreatures)
		}
	}
	
	static def buildHoard(def challengeRating) {
		def cr = challengeRating ? challengeRating.toInteger() : 0
		switch(cr) {
			case 0..4:
				buildHoardCR0()
				break
			case 5..10:
				buildHoardCR5()
				break
			case 11..16:
			    buildHoardCR11()
				break
			default:
				buildHoardCR17()
		}
	
	}
	
	static def buildCR0(def numberOfCreatures) {
		def treasure = [pp:0,gp:0,ep:0,sp:0,cp:0]
		def num = numberOfCreatures ? numberOfCreatures.toInteger() : 1

		(1..num).each{ it ->
			def roll = Dice.roll(1,100).sum()
			switch(roll){
			  case 1..30:
				 treasure.cp+=Dice.roll(5,6).sum()
				 break
			  case 31..60:
				 treasure.sp+=Dice.roll(4,6).sum()
				break
			  case 61..70:
				 treasure.ep+=Dice.roll(3,6).sum()
				break
			  case 71..95:
				 treasure.gp+=Dice.roll(3,6).sum()
				break
			  case 96..100:
				 treasure.pp+=Dice.roll(1,6).sum()
				 break
			}
		}
		treasure
	}
	
	static def buildCR5(def numberOfCreatures) {
		def treasure = [pp:0,gp:0,ep:0,sp:0,cp:0]
		def num = numberOfCreatures ? numberOfCreatures.toInteger() : 1

		(1..num).each{ it ->
			def roll = Dice.roll(1,100).sum()
			switch(roll){
			  case 1..30:
				 treasure.cp+=Dice.roll(4,6).sum()*100
				 treasure.ep+=Dice.roll(1,6).sum()*10
				 break
			  case 31..60:
				 treasure.sp+=Dice.roll(6,6).sum()*10
				 treasure.gp+=Dice.roll(2,6).sum()*10
				break
			  case 61..70:
				 treasure.ep+=Dice.roll(3,6).sum()*10
				 treasure.gp+=Dice.roll(2,6).sum()*10
				break
			  case 71..95:
				 treasure.gp+=Dice.roll(4,6).sum()*10
				break
			  case 96..100:
				 treasure.gp+=Dice.roll(2,6).sum()*10
				 treasure.pp+=Dice.roll(3,6).sum()
				 break
			}
		}
		treasure
	}
	
	static def buildCR11(def numberOfCreatures) {
		def treasure = [pp:0,gp:0,ep:0,sp:0,cp:0]
		def num = numberOfCreatures ? numberOfCreatures.toInteger() : 1

		(1..num).each{ it ->
			def roll = Dice.roll(1,100).sum()
			switch(roll){
			  case 1..20:
				 treasure.sp+=Dice.roll(4,6).sum()*100
				 treasure.gp+=Dice.roll(1,6).sum()*100
				 break
			  case 21..35:
				 treasure.ep+=Dice.roll(1,6).sum()*100
				 treasure.gp+=Dice.roll(1,6).sum()*100
				break
			  case 36..75:
				 treasure.gp+=Dice.roll(2,6).sum()*100
				 treasure.pp+=Dice.roll(1,6).sum()*10
				break
			  case 76..100:
				 treasure.gp+=Dice.roll(2,6).sum()*100
				 treasure.pp+=Dice.roll(2,6).sum()*10
				break
			}
		}
		treasure
	}
	
		static def buildCR17(def numberOfCreatures) {
		def treasure = [pp:0,gp:0,ep:0,sp:0,cp:0]
		def num = numberOfCreatures ? numberOfCreatures.toInteger() : 1

		(1..num).each{ it ->
			def roll = Dice.roll(1,100).sum()
			switch(roll){
			  case 1..15:
				 treasure.ep+=Dice.roll(2,6).sum()*1000
				 treasure.gp+=Dice.roll(8,6).sum()*100
				 break
			  case 16..55:
				 treasure.gp+=Dice.roll(1,6).sum()*1000
				 treasure.pp+=Dice.roll(1,6).sum()*100
				break
			  case 56..100:
				 treasure.gp+=Dice.roll(1,6).sum()*1000
				 treasure.pp+=Dice.roll(2,6).sum()*100
				break
			}
		}
		treasure
	}
	
	static def buildHoard(def coinBank, def gemBank, def artVault, def magicStock) {
		def hoard = [pp:0,gp:0,ep:0,sp:0,cp:0,gems:[count:0, value:0], art:[count:0,value:0] ,magic:[]]
		coinBank.each { hoard[it.key] += Dice.roll(it.value.dice).sum() * it.value.multi }
		def roll = Dice.roll(1,100).sum()
		def gemRoll = gemBank.find {it.key.contains(roll)}
		if (gemRoll) {
			hoard.gems.count=Dice.roll(gemRoll?.value.dice).sum()
			hoard.gems.value=gemRoll?.value.gp
		}
		def artRoll = artVault.find {it.key.contains(roll)}
		if (artRoll) {
			hoard.art.count=Dice.roll(artRoll?.value?.dice).sum()
			hoard.art.value=artRoll?.value.gp
		}
		def magicRoll = magicStock.find {it.key.contains(roll)}
		if (magicRoll) {
			println "magic: ${magicRoll}"
			println "value: ${magicRoll.value}"
			magicRoll.value.each{
				println "processing: ${it}"
				def getMagicItem = it.itemMethod
				println "method: ${getMagicItem}"
				def number = Dice.roll(it.dice).sum()
				println "rolled: ${it.dice} (${number})"
				(1..number).each {
					def item = this."$getMagicItem"()
					println "found ${item}"
					println hoard
					hoard.magic << item
					println hoard
				}
			}
		}
		hoard
	}

	static def buildHoardCR0() {
		def coins = [cp:[dice:"6d6",multi:100],sp:[dice:"3d6", multi:100],gp:[dice:"2d6", multi:10]]
		def gems = [ ( 7..16):[dice:"2d6", gp:10], (27..36):[dice:"2d6", gp:50], (37..44):[dice:"2d6", gp:10],
			(53..60):[dice:"2d6", gp:50], (61..65):[dice:"2d6", gp:10], (71..75):[dice:"2d6", gp:50],
			(76..78):[dice:"2d6", gp:10], (81..85):[dice:"2d6", gp:50], (93..97):[dice:"2d6", gp:50],
			(100..100):[dice:"2d6", gp:50] ]
		def art = [ (17..26):[dice:"2d4", gp:25], (45..52):[dice:"2d4", gp:25], (66..70):[dice:"2d4", gp:25],
			(79..80):[dice:"2d4", gp:25], (86..92):[dice:"2d4", gp:25], (98..99):[dice:"2d4", gp:25] ]
		def magic = [ (37..44):[[dice:"1d6", itemMethod:"getMagicItemA"]], (45..52):[[dice:"1d6", itemMethod:"getMagicItemA"]],
			(53..60):[[dice:"1d6", itemMethod:"getMagicItemA"]], (61..65):[[dice:"1d4", itemMethod:"getMagicItemB"]],
			(66..70):[[dice:"1d4", itemMethod:"getMagicItemB"]], (71..75):[[dice:"1d4", itemMethod:"getMagicItemB"]],
			(76..78):[[dice:"1d4", itemMethod:"getMagicItemC"]], (79..80):[[dice:"1d4", itemMethod:"getMagicItemC"]],
			(81..85):[[dice:"1d4", itemMethod:"getMagicItemC"]], (86..92):[[dice:"1d4", itemMethod:"getMagicItemF"]],
			(93..97):[[dice:"1d4", itemMethod:"getMagicItemF"]], (98..99):[[itemMethod:"getMagicItemG"]],
			(100..100):[[itemMethod:"getMagicItemG"]]]
		buildHoard(coins, gems, art, magic)
	}

	static def buildHoardCR5() {
		def coins = [cp:[dice:"2d6",multi:100],sp:[dice:"2d6", multi:1000],gp:[dice:"6d6", multi:100],pp:[dice:"3d6",multi:10]]
		def gems = [ (11..16):[dice:"3d6", gp:50], (17..22):[dice:"3d6", gp:100], (33..36):[dice:"3d6", gp:50],
			(37..40):[dice:"3d6", gp:100], (50..54):[dice:"3d6", gp:50], (55..59):[dice:"3d6", gp:100],
			(67..69):[dice:"3d6", gp:50], (70..72):[dice:"3d6", gp:100], (77..78):[dice:"3d6", gp:50],
			(79..79):[dice:"3d6", gp:100], (85..88):[dice:"3d6", gp:50], (89..91):[dice:"3d6", gp:100],
			(95..96):[dice:"3d6", gp:50], (99..99):[dice:"3d6", gp:50] ]
		def art = [ ( 5..10):[dice:"2d4", gp:25], (23..28):[dice:"2d4", gp:250], (29..32):[dice:"2d4", gp:25],
			(41..44):[dice:"2d4", gp:250], (45..49):[dice:"2d4", gp:25], (60..63):[dice:"2d4", gp:250], 
			(64..66):[dice:"2d4", gp:25], (73..74):[dice:"2d4", gp:250], (75..76):[dice:"2d4", gp:25], 
			(80..80):[dice:"2d4", gp:250], (81..84):[dice:"2d4", gp:25], (92..94):[dice:"2d4", gp:250], 
			(97..98):[dice:"2d4", gp:250], (100..100):[dice:"2d4", gp:250] ]
		def magic = [ (29..32):[[dice:"1d6", itemMethod:"getMagicItemA"]], (33..36):[[dice:"1d6", itemMethod:"getMagicItemA"]],
			(37..40):[[dice:"1d6", itemMethod:"getMagicItemA"]], (41..44):[[dice:"1d6", itemMethod:"getMagicItemA"]],
			(45..49):[[dice:"1d4", itemMethod:"getMagicItemB"]], (50..54):[[dice:"1d4", itemMethod:"getMagicItemB"]], 
			(55..59):[[dice:"1d4", itemMethod:"getMagicItemB"]], (60..63):[[dice:"1d4", itemMethod:"getMagicItemB"]], 
			(65..66):[[dice:"1d4", itemMethod:"getMagicItemC"]], (67..69):[[dice:"1d4", itemMethod:"getMagicItemC"]], 
			(70..72):[[dice:"1d4", itemMethod:"getMagicItemC"]], (73..74):[[dice:"1d4", itemMethod:"getMagicItemC"]],
			(75..76):[[itemMethod:"getMagicItemD"]], (77..78):[[itemMethod:"getMagicItemD"]],
			(79..79):[[itemMethod:"getMagicItemD"]], (80..80):[[itemMethod:"getMagicItemD"]], 
			(81..84):[[dice:"1d4", itemMethod:"getMagicItemF"]], (85..88):[[dice:"1d4", itemMethod:"getMagicItemF"]], 
			(89..91):[[dice:"1d4", itemMethod:"getMagicItemF"]], (92..94):[[dice:"1d4", itemMethod:"getMagicItemF"]], 
			(95..96):[[dice:"1d4", itemMethod:"getMagicItemG"]], (97..98):[[dice:"1d4", itemMethod:"getMagicItemG"]],
			(99..99):[[itemMethod:"getMagicItemH"]], (100..100):[[itemMethod:"getMagicItemH"]]]
		buildHoard(coins, gems, art, magic)
	}

	static def buildHoardCR11() {
		def coins = [gp:[dice:"4d6", multi:1000],pp:[dice:"5d6",multi:100]]
		def gems = [ (11..12):[dice:"3d6", gp:500], (13..15):[dice:"3d6", gp:1000], (24..26):[dice:"3d6", gp:500],
			(27..29):[dice:"3d6", gp:1000], (41..45):[dice:"3d6", gp:500], (46..50):[dice:"3d6", gp:1000],
			(59..62):[dice:"3d6", gp:500], (63..66):[dice:"3d6", gp:1000], (71..72):[dice:"3d6", gp:500],
			(73..74):[dice:"3d6", gp:1000], (79..80):[dice:"3d6", gp:500], (81..82):[dice:"3d6", gp:1000],
			(89..90):[dice:"3d6", gp:500], (91..92):[dice:"3d6", gp:1000], (97..98):[dice:"3d6", gp:500], 
			(99..100):[dice:"3d6", gp:1000] ]
		def art = [ ( 4..6 ):[dice:"2d4", gp:250], ( 7..9 ):[dice:"2d4", gp:750], (16..19):[dice:"2d4", gp:250],
			(20..23):[dice:"2d4", gp:750], (30..35):[dice:"2d4", gp:250], (36..40):[dice:"2d4", gp:750],
			(51..54):[dice:"2d4", gp:250], (55..58):[dice:"2d4", gp:750], (67..68):[dice:"2d4", gp:250],
			(69..70):[dice:"2d4", gp:750], (75..76):[dice:"2d4", gp:250], (77..78):[dice:"2d4", gp:750],
			(83..85):[dice:"2d4", gp:250], (86..88):[dice:"2d4", gp:750], (93..94):[dice:"2d4", gp:250], 
			(95..96):[dice:"2d4", gp:750] ]
		def magic = [ (29..32):[[dice:"1d4", itemMethod:"getMagicItemA"], [dice:"1d6", itemMethod:"getMagicItemB"]], 
			(33..36):[[dice:"1d4", itemMethod:"getMagicItemA"], [dice:"1d6", itemMethod:"getMagicItemB"]],
			(37..40):[[dice:"1d4", itemMethod:"getMagicItemA"], [dice:"1d6", itemMethod:"getMagicItemB"]], 
			(41..44):[[dice:"1d4", itemMethod:"getMagicItemA"], [dice:"1d6", itemMethod:"getMagicItemB"]],
			(45..49):[[dice:"1d6", itemMethod:"getMagicItemC"]], (50..54):[[dice:"1d6", itemMethod:"getMagicItemC"]],
			(55..59):[[dice:"1d6", itemMethod:"getMagicItemC"]], (60..63):[[dice:"1d6", itemMethod:"getMagicItemC"]],
			(65..66):[[dice:"1d4", itemMethod:"getMagicItemD"]], (67..69):[[dice:"1d4", itemMethod:"getMagicItemD"]],
			(70..72):[[dice:"1d4", itemMethod:"getMagicItemD"]], (73..74):[[dice:"1d4", itemMethod:"getMagicItemD"]],
			(75..76):[[itemMethod:"getMagicItemE"]], (77..78):[[itemMethod:"getMagicItemE"]], (79..79):[[itemMethod:"getMagicItemE"]], 
			(80..80):[[itemMethod:"getMagicItemE"]], (75..76):[[itemMethod:"getMagicItemF"],[dice:"1d4", itemMethod:"getMagicItemC"]], 
			(77..78):[[itemMethod:"getMagicItemF"],[dice:"1d4", itemMethod:"getMagicItemC"]],
			(79..79):[[itemMethod:"getMagicItemF"],[dice:"1d4", itemMethod:"getMagicItemC"]], 
			(80..80):[[itemMethod:"getMagicItemF"],[dice:"1d4", itemMethod:"getMagicItemC"]],
			(81..84):[[dice:"1d4", itemMethod:"getMagicItemH"]], (85..88):[[dice:"1d4", itemMethod:"getMagicItemH"]],
			(89..91):[[dice:"1d4", itemMethod:"getMagicItemH"]], (92..94):[[dice:"1d4", itemMethod:"getMagicItemH"]],
			(95..96):[[itemMethod:"getMagicItemI"]], (97..98):[[itemMethod:"getMagicItemI"]], (99..99):[[itemMethod:"getMagicItemI"]], 
			(100..100):[[itemMethod:"getMagicItemI"]] ]
		buildHoard(coins, gems, art, magic)
	}

		static def buildHoardCR17() {
		def coins = [gp:[dice:"12d6", multi:1000],pp:[dice:"8d6",multi:1000]]
		def gems = [ ( 3..5 ):[dice:"3d6", gp:1000], (12..14):[dice:"1d8", gp:5000], (15..22):[dice:"3d6", gp:1000],
			(39..46):[dice:"1d8", gp:5000], (47..52):[dice:"3d6", gp:1000], (64..68):[dice:"1d8", gp:5000],
			(69..69):[dice:"3d6", gp:1000], (72..72):[dice:"1d8", gp:5000], (73..74):[dice:"3d6", gp:1000],
			(79..80):[dice:"1d8", gp:5000], (79..85):[dice:"3d6", gp:1000], (96..100):[dice:"1d8", gp:5000] ]
		def art = [ ( 6..8 ):[dice:"1d10", gp:2500], ( 9..11):[dice:"1d4",  gp:7500], (23..30):[dice:"1d10", gp:2500],
			(31..38):[dice:"1d4",  gp:7500], (53..58):[dice:"1d10", gp:2500], (59..63):[dice:"1d4",  gp:7500],
			(70..70):[dice:"1d10", gp:2500], (71..71):[dice:"1d4",  gp:7500], (75..76):[dice:"1d10", gp:2500],
			(77..78):[dice:"1d4",  gp:7500], (86..90):[dice:"1d10", gp:2500], (91..95):[dice:"1d4",  gp:7500] ]
		def magic = [ ( 3..5 ):[[dice:"1d8", itemMethod:"getMagicItemC"]], ( 6..8 ):[[dice:"1d8", itemMethod:"getMagicItemC"]],
			( 9..11):[[dice:"1d8", itemMethod:"getMagicItemC"]], (12..14):[[dice:"1d8", itemMethod:"getMagicItemC"]],
			(15..22):[[dice:"1d6", itemMethod:"getMagicItemD"]], (23..30):[[dice:"1d6", itemMethod:"getMagicItemD"]],
			(31..38):[[dice:"1d6", itemMethod:"getMagicItemD"]], (39..46):[[dice:"1d6", itemMethod:"getMagicItemD"]],
			(47..52):[[dice:"1d6", itemMethod:"getMagicItemE"]], (53..58):[[dice:"1d6", itemMethod:"getMagicItemE"]],
			(59..63):[[dice:"1d6", itemMethod:"getMagicItemE"]], (64..68):[[dice:"1d6", itemMethod:"getMagicItemE"]],
			(69..69):[[dice:"1d4", itemMethod:"getMagicItemG"]], (70..70):[[dice:"1d4", itemMethod:"getMagicItemG"]],
			(71..71):[[dice:"1d4", itemMethod:"getMagicItemG"]], (72..72):[[dice:"1d4", itemMethod:"getMagicItemG"]],
			(73..74):[[dice:"1d4", itemMethod:"getMagicItemH"]], (75..76):[[dice:"1d4", itemMethod:"getMagicItemH"]],
			(77..78):[[dice:"1d4", itemMethod:"getMagicItemH"]], (79..80):[[dice:"1d4", itemMethod:"getMagicItemH"]],
			(81..85):[[dice:"1d4", itemMethod:"getMagicItemI"]], (86..90):[[dice:"1d4", itemMethod:"getMagicItemI"]],
			(91..95):[[dice:"1d4", itemMethod:"getMagicItemI"]], (96..100):[[dice:"1d4", itemMethod:"getMagicItemI"]] ]
		buildHoard(coins, gems, art, magic)
	}

	static def getMagicItemA() {
		def items = [(1..50):"Potion of Healing", (51..60):"Spell Scroll (Cantrip)", (61..70):"Potion of Climbing",
			(71..90):"Spell Scroll (1st Level)", (91..94):"Spell Scroll (2nd Level)", (95..98):"Potion of Greater Healing",
			(99..99):"Bag of Holding", (100..100):"Driftglobe" ]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}

	static def getMagicItemB() {
		def items = [(1..15):"Potion of Greater Healing", (16..22):"Potion of Fire Breath", (23..29):"Potion of Resistance",
			(30..34):"Ammunition +1", (35..39):"Potion of Animal Friendship", (40..44):"Potion of Hill Giant's Strength",
			(45..49):"Potion of Growth", (50..54):"Potion of Water Breathing", (55..59):"Spell Scroll (2nd Level)",
			(60..64):"Spell Scroll (3rd Level)", (65..67):"Bag of Holding", (68..70):"Keoghtom's Ointment",
			(71..73):"Oil of Slipperiness", (74..75):"Dust of Disappearance", (76..77):"Dust of Dryness",
			(78..79):"Dust of Sneezing and Choking", (80..81):"Elemental Gem", (82..83):"Philter of Love",
			(84..84):"Alchemy Jug", (85..85):"Cap of Water Breathing", (86..86):"Cloak of the Manta Ray",
			(87..87):"Driftglobe", (88..88):"Goggles of Night", (89..89):"Helm of Comprehending Languages",
			(90..90):"Immovable Rod", (91..91):"Lantern of Revealing", (92..92):"Mariner's Armor",
			(93..93):"Mithral Armor", (94..94):"Potion of Poison", (95..95):"Ring of Swimming",
			(96..96):"Robe of Useful Items", (97..97):"Rope of Climbing", (98..98):"Saddle of the Cavalier",
			(99..99):"Wand of Magic Detection", (100..100):"Wand of Secrets"
			]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemC() {
		def items = [(1..15): "Potion of Superior Healing", (16..22): "Spell Scroll (4th Level)", (23..27): "Ammunition +2",
			(28..32): "Potion of Clairvoyance", (33..37): "Potion of Diminution", (38..42): "Potion of Gaseous Form",
			(43..47): "Potion of Frost Giant Strength", (48..52): "Potion of Stone Giant Strength", (53..57): "Potion of Heroism",
			(58..62): "Potion of Invulnerability", (63..67): "Potion of Mind Reading", (68..72): "Spell Scroll (5th Level)",
			(73..75): "Elixir of Health", (76..78): "Oil of Etherealness", (79..81): "Potion of Fire Giant Strength",
			(82..84): "Quaal's Feather", (85..87): "Scroll of Protection", (88..89): "Bag of Beans",
			(90..91): "Bead of Force", (92..92): "Chime of Opening", (93..93): "Decanter of Endless Water",
			(94..94): "Eyes of Minute Seeing", (95..95): "Folding Boat", (96..96): "Heward's Handy Haversack",
			(97..97): "Horseshoes of Speed", (98..98): "Necklace of Fireballs", (99..99): "Periapt of Health",
			(100..100): "Sending Stones" ]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemD() {
		def items = [(1..20) :"Potion of Supreme Healing", (21..30):"Potion of Invisibility", (31..40):"Potion of Speed",
			(41..50):"Spell Scroll (6th Level)", (51..57):"Spell Scroll (7th Level)", (58..62):"Ammunition, +3",
			(63..67):"Oil of Sharpness", (68..72):"Potion of Flying", (73..77):"Potion of Cloud Giant Strength", 
			(78..82):"Potion of Longevity", (83..87):"Potion of Vitality", (88..92):"Spell Scroll (8th Level)",
			(93..95):"Horseshoes of a Zephyr", (96..98):"Nolzur's Marvelous Pigments", (99..99):"Bag of Devouring",
			(100..100):"Portable Hole"]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemE() {
		def items = [ (1..30):"Spell Scroll (8th Level)", (31..55):"Potion of Storm Giant Strength",
			(56..70):"Potion of Supreme Healing", (71..85):"Spell Scroll (9th Level)", (86..93):"Universal Solvent",
			(94..98):"Arrow of Slaying", (99..100):"Sovereign Glue"]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemF() {
		def items = [ (1..15):"Weapon, +1", (16..18):"Shield, +1", (19..21):"Sentinel Shield",
			(22..23):"Amulet of Proof Against Detection and Location", (24..25):"Boots of Elvenkind",
			(26..27):"Boots of Striding and Springing", (28..29):"Bracers of Archery", (30..31):"Brooch of Shielding",
			(32..33):"Broom of Flying", (34..35):"Cloak of Elvenkind", (36..37):"Cloak of Protection",
			(38..39):"Gauntles of Ogre Power", (40..41):"Hat of Disguise", (42..43):"Javelin of Lightning",
			(44..45):"Pearl of Power", (46..47):"Rod of the Pact Keeper, +1", (48..49):"Slippers of Spider Climbing",
			(50..51):"Staff of the Adder", (52..53):"Staff of the Python", (54..55):"Sword of Vengeance",
			(56..57):"Trident of Fish Command", (58..59):"Wand of Magic Missles", (60..61):"Wand of the War Mage, +1",
			(62..63):"Wand of Web", (64..65):"Weapon of Warning", (66..66):"Adamantine Armor (chain mail)",
			(67..67):"Adamantine Armor (chain shirt)", (68..68):"Adamantine Armor (scale male)", (69..69):"Bag of Tricks (gray)",
			(70..70):"Bag of Tricks (rust)", (71..71):"Bag of Tricks (tan)", (72..72):"Boots of the Winterlands",
			(73..73):"Circlet of Blasting", (74..74):"Deck of Illusions", (75..75):"Eversmoking Bottle",
			(76..76):"Eyes of Charming", (77..77):"Eyes of the Eagle", (78..78):"Figurine of Wonderous Power (silver raven)",
			(79..79):"Gem of Brightness", (80..80):"Gloves of Missile Snaring", (81..81):"Gloves of Swimming and Climbing",
			(82..82):"Gloves of Thievery", (83..83):"Headband of Intellect", (84..84):"Helm of Telepathy",
			(85..85):"Instrument of the Bards (Doss lute)", (86..86):"Instrument of the Bards (Fochlucan bandore)", 
         (87..87):"Instrument of the Bards (Mac-Fuimidh cittern)", (88..88):"Medallion of Thoughts",
			(89..89):"Necklace of Adaptation", (90..90):"Periapt of Wound Closure", (91..91):"Pipes of Haunting",
			(92..92):"Pipes of the Sewers", (93..93):"Ring of Jumping", (94..94):"Ring of Mind Shielding",
			(95..95):"Ring of Warmth", (96..96):"Ring of Water Walking", (97..97):"Quiver of Ehlonna",
			(98..98):"Stone of Good Luck", (99..99):"Wind Fan", (100..100):"Winged Boots" ]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemG() {
		def figurines = [ (1..1):"Bronze Griffon", (2..2):"Ebony Fly", (3..3):"Golden Lions", (4..4):"Ivory Goats",
				(5..5):"Marble Elephant", (6..7):"Onyx Dog", (8..8):"Serpentine Owl" ]
		def items =[(1..11):"Weapon, +2", (12..14):"Figurine of Wondrous Power", (15..15 ):"Adamantine Armor (breastplate)",
			(16..16 ):"Adamantine Armor (splint)", (17..17 ):"Amulet of Health", (18..18 ):"Armor of Vulnerability",
			(19..19 ):"Arrow-catching Shield", (20..20 ):"Belt of Dwarvenkind", (21..21 ):"Belt of Hill Giant Strength",
			(22..22 ):"Berserker Axe", (23..23 ):"Boots of Levitation", (24..24 ):"Boots of Speed",
			(25..25 ):"Bowl of Commanding Water Elementals", (26..26 ):"Bracers of Defense", (27..27 ):"Brazier of Commanding Fire Elementals",
			(28..28 ):"Cape of the Mountebank", (29..29 ):"Censer of Controlling Air Elementals", (30..30 ):"Armor, +1 (chain mail)",
			(31..31 ):"Armor of Resistance (chain mail)", (32..32 ):"Armor, +1 (chain shirt)", (33..33 ):"Armor of Resistance (chain shirt)",
			(34..34 ):"Cloak of Displacement", (35..35 ):"Cloak of the Bat", (36..36 ):"Cube of Force",
			(37..37 ):"Daern's Instant Fortress", (38..38 ):"Dagger of Venom", (39..39 ):"Dimensional Shackles",
			(40..40 ):"Dragon Slayer", (41..41 ):"Elven Chain", (42..42 ):"Flame Tongue", (43..43 ):"Gem of Seeing", (44..44 ):"Giant Slayer",
			(45..45 ):"Glamoured Studded Leather", (46..46 ):"Helm of Teleportation", (47..47 ):"Horn of Blasting",
			(48..48 ):"Horn of Valhalla (silver or brass)", (49..49 ):"Instrument of the Bards (Canaith mandolin)",
			(50..50 ):"Instrument of the Bards (Cli lyre)", (51..51 ):"Ioun Stone (awareness)", (52..52 ):"Ioun Stone (protection)",
			(53..53 ):"Ioun Stone (reserve)", (54..54 ):"Ioun Stone (sustenance)", (55..55 ):"Iron Band of Bilarro", (56..56 ):"Armor, +1 (leather)",
			(57..57 ):"Armor of Resistance (leather)", (58..58 ):"Mace of Disruption", (59..59 ):"Mace of Smiting", (60..60 ):"Mace of Terror",
			(61..61 ):"Mantle of Spell Resistance", (62..62 ):"Necklace of Prayer Beads", (63..63 ):"Periapt of Proof against Poison",
			(64..64 ):"Ring of Animal Influence", (65..65 ):"Ring of Evasion", (66..66 ):"Ring of Feather Falling", (67..67 ):"Ring of Free Action",
			(68..68 ):"Ring of Protection", (69..69 ):"Ring of Resistance", (70..70 ):"Ring of Spell Storing", (71..71 ):"Ring of the Ram",
			(72..72 ):"Ring of X-Ray Vision", (73..73 ):"Robe of Eyes", (74..74 ):"Rod of Rulership", (75..75 ):"Rod of the Pact Keeper, +2",
			(76..76 ):"Rope of Entanglement", (77..77 ):"Armor, +1 (scale mail)", (78..78 ):"Armor of Resistance (scale mail)", (79..79 ):"Shield, +2",
			(80..80 ):"Shield of Missile Attraction", (81..81 ):"Staff of Charming", (82..82 ):"Staff of Healing", (83..83 ):"Staff of Swarming Insects",
			(84..84 ):"Staff of the Woodlands", (85..85 ):"Staff of Withering", (86..86 ):"Stone of Controlling Earth Elementals", (87..87 ):"Sun Blade",
			(88..88 ):"Sword of Life Stealing", (89..89 ):"Sword of Wounding", (90..90 ):"Tentacle Rod", (91..91 ):"Vicious Weapon",
			(92..92 ):"Wand of Binding", (93..93 ):"Wand of Enemy Detection", (94..94 ):"Wand of Fear", (95..95 ):"Wand of Fireballs",
			(96..96 ):"Wand of Lightning Bolts", (97..97 ):"Wand of Paralysis", (98..98 ):"Wand of the War Mage, +2", (99..99 ):"Wand of Wonder",
			(100..100):"Wings of Flying" ]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		roll = Dice.roll(1,8).sum()
		def figurine = figurines.find{ it.key.contains(roll)}
		item.value.startsWith("Figurine") ? "${item.value}: ${figurine.value}" : item.value
	}
	
	static def getMagicItemH() {
		def items = [(1..10):"Weapon, +3", (11..12):"Amulet of the Planes", (13..14):"Carpet of Flying",
				(15..16):"Crystal Ball (very rare version)", (17..18):"Ring of Regeneration", (19..20):"Ring of Shooting Stars",
				(21..22):"Ring of Telekinesis", (23..24):"Robe of Scintillating Colors", (25..26):"Robe of Stars",
				(27..28):"Rod of Absorption", (29..30):"Rod of Alertness", (31..32):"Rod of Security",
				(33..34):"Rod of the Pact Keeper, +3", (35..36):"Scimitar of Speed", (37..38):"Shield, +3", (39..40):"Staff of Fire",
				(41..42):"Staff of Frost", (43..44):"Staff of Power", (45..46):"Staff of Striking", (47..48):"Staff of Thunder and Lightning",
				(49..50):"Sword of Sharpness", (51..52):"Wand of Polymorph", (53..54):"Wand of the War Mage, +3",
				(55..55 ):"Adamantine Armor (half plate)", (56..56 ):"Adamantine Armor (plate)", (57..57 ):"Animated Shield",
				(58..58 ):"Belt of Fire Giant Strength", (59..59 ):"Belt of Frost (or Stone) Giant Strength", (60..60 ):"Armor, +1 (breastplate)",
				(61..61 ):"Armor of Resistance (breastplate)", (62..62 ):"Candle of Invocation", (63..63 ):"Armor, +2 (chain mail)",
				(64..64 ):"Armor, +2 (chain shirt)", (65..65 ):"Cloak of Arachnida", (66..66 ):"Dancing Sword",
				(67..67 ):"Demon Armor", (68..68 ):"Dragon Scale Mail", (69..69 ):"Dwarven Plate",
				(70..70 ):"Dwarven Thrower", (71..71 ):"Efreeti Bottle", (72..72 ):"Figurine of Wondrous Power: Obsidian Steed",
				(73..73 ):"Frost Brand", (74..74 ):"Helm of Brilliance", (75..75 ):"Horn of Valhalla (bronze)",
				(76..76 ):"Instrument of the Bards (Anstruth harp)", (77..77 ):"Ioun Stone (absorption)", (78..78 ):"Ioun Stone (agility)",
				(79..79 ):"Ioun Stone (fortitude)", (80..80 ):"Ioun Stone (insight)", (81..81 ):"Ioun Stone (intellect)",
				(82..82 ):"Ioun Stone (leadership)", (83..83 ):"Ioun Stone (strength)", (84..84 ):"Armor, +2 (leather)",
				(85..85 ):"Manual of Bodily Health", (86..86 ):"Manual of Gainful Exercise", (87..87 ):"Manual of Golems",
				(88..88 ):"Manual of Quickness of Action", (89..89 ):"Mirror of Life Trapping", (90..90 ):"Nine Lives Stealer",
				(91..91 ):"Oathbow", (92..92 ):"Armor, +2 (scale mail)", (93..93 ):"Spellguard Shield", (94..94 ):"Armor, +1 (splint)",
				(95..95 ):"Armor of Resistance (splint)", (96..96 ):"Armor, +1 (studded leather)", (97..97 ):"Armor of Resistance (studded leather)",
				(98..98 ):"Tome of Clear Thought", (99..99 ):"Tome of Leadership and Influence", (100..100):"Tome of Understanding"]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		item.value
	}
	
	static def getMagicItemI() {
		def armors = [(1..2):"+2 (half plate)", (3..4):"+2 (plate)", (5..6):"+3 (studded leather)", (7..8): "+3 (breastplate)", 
				(9..10): "+3 (splint)", (11..11): "+3 (half plate)", (12..12):"+3 (plate)"]
		def items = [ (1..5 ):"Defender", (6..10):"Hammer of Thunderbolts", (11..15):"Luck Blade", (16..20):"Sword of Answering",
				(21..23):"Holy Avenger", (24..26):"Ring of Djinni Summoning", (27..29):"Ring of Invisibility", (30..32):"Ring of Spell Turning",
				(33..35):"Rod of Lordly Might", (36..38):"Staff of the Magi", (39..41):"Vorpal Sword", (42..43):"Belt of Cloud Giant Strength",
				(44..45):"Armor, +2 (breastplate)", (46..47):"Armor, +3 (chain mail)", (48..49):"Armor, +3 (chain shirt)", (50..51):"Cloak of Invisibility",
				(52..53):"Crystal Ball (legendary version)", (54..55):"Armor, +1 (half plate)", (56..57):"Iron Flask", (58..59):"Armor, +3 (leather)",
				(60..61):"Armor, +1 (plate)", (62..63):"Robe of the Archmagi", (64..65):"Rod of Ressurrection", (66..67):"Armor, +1 (scale mail)",
				(68..69):"Scarab of Protection", (70..71):"Armor, +2 (splint)", (72..73):"Armor, +2 (studded leather)", (74..75):"Well of Many Words",
				( 76..76 ):"Armor", ( 77..77 ):"Apparatus of Kwalish", ( 78..78 ):"Armor of Invulnerability", ( 79..79 ):"Belt of Storm Giant Strength",
				( 80..80 ):"Cubic Gate", ( 81..81 ):"Deck of Many Things", ( 82..82 ):"Efreeti Chain", ( 83..83 ):"Armor of Resistance (half plate)",
				( 84..84 ):"Horn of Valhalla (iron)", ( 85..85 ):"Instrument of the Bards (Ollamh harp)", ( 86..86 ):"Ioun Stone (greater absorption)",
				( 87..87 ):"Ioun Stone (mastery)", ( 88..88 ):"Ioun Stone (regeneration)", ( 89..89 ):"Plate Armor of Etherealness",
				( 90..90 ):"Plate Armor of Resistance", ( 91..91 ):"Ring of Air Elemental Command", ( 92..92 ):"Ring of Earth Elemental Command",
				( 93..93 ):"Ring of Fire Elemental Command", ( 94..94 ):"Ring of Three Wishes", ( 95..95 ):"Ring of Water Elemental Command",
				( 96..96 ):"Sphere of Annihilation", ( 97..97 ):"Talisman of Pure Good", ( 98..98 ):"Talisman of the Sphere",
				( 99..99 ):"Talisman of Ultimate Evil", (100..100):"Tome of the Stilled Tongue" ]
		def roll = Dice.roll(1,100).sum()
		def item = items.find{ it.key.contains(roll)}
		roll = Dice.roll(1,12).sum()
		def armor = armors.find{ it.key.contains(roll)}
		item.key == [76] ? "${item.value}, ${armor.value}" : item.value
	}
}
