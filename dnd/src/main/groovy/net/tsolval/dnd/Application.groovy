package net.tsolval.dnd

import org.springframework.boot.Banner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

import groovy.xml.MarkupBuilder
import net.tsolval.dnd.treasure.Art
import net.tsolval.dnd.treasure.Gem
import net.tsolval.dnd.treasure.MagicItem
import net.tsolval.dnd.treasure.TreasureGenerator

@RestController
@EnableAutoConfiguration
class Application {

	@RequestMapping("/")
	def home() {
		new ModelAndView("views/home", [bootVersion: Banner.package.implementationVersion, groovyVersion: GroovySystem.version])
	}

	@GetMapping("loot")
	def loot() {
		def treasure = "treasure"
		
		println treasure

		new ModelAndView("views/loot", "loot", treasure)
	}

	@PostMapping("/loot")
	def getLoot(@ModelAttribute('challengeRating') String cr, @ModelAttribute('creatureCount') String num) {
		def writer = new StringWriter()
		def markup = new MarkupBuilder(writer)
		markup.html{
			body{
				p('hello')
				p(cr)
				p(num)
				p("forward:/loot/${cr}/${num}")
			}
		}
		writer.toString()
	}

	@RequestMapping("/loot/{challengeRating}/{numberOfCreatures}")
	def treasure(@PathVariable def challengeRating, @PathVariable def numberOfCreatures) {
		def treasure = TreasureGenerator.build(challengeRating, numberOfCreatures)

		def writer = new StringWriter()
		def markup = new MarkupBuilder(writer)

		markup.html{
			body{
				h2("Generating Treasure for ${numberOfCreatures} creatures of CR: ${challengeRating}!")
				p("${treasure}")
			}
		}

		writer.toString()
	}

	@RequestMapping("/horde/{challengeRating}")
	def horde(@PathVariable def challengeRating) {
		def treasure = TreasureGenerator.buildHoard(challengeRating)
		def writer = new StringWriter()
		def markup = new MarkupBuilder(writer)

		markup.html{
			body{
				h2("Generating Horde of CR: ${challengeRating}!")
				p("${treasure}")
			}
		}
		writer.toString()
	}

	static main(args) {
		SpringApplication.run(Application.class, args)
	}
}
