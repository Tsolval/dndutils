yieldUnescaped '<!DOCTYPE html>'
html {
  head {
	// set the character set
	meta(charset: 'utf-8', '')
	// make mobile compatible
	meta(name: 'viewport', content: 'width=device-width, initial-scale=1', '')
	// set the page title
    title(pageTitle)
    // add standard bootstrap css
    //link(rel: 'stylesheet', href: 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css')
    // add cyborg theme bootstrap css
    link(rel: 'stylesheet', href: 'https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/cyborg/bootstrap.min.css')
    // add custom css
    //link(rel: 'stylesheet', href: '/css/style.css')
    // add jquery capabilities
	script(src: 'https://code.jquery.com/jquery-3.1.1.min.js', integrity: 'sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=', crossorigin: 'anonymous', '')
	// add bootstrap javascript
	script(src: 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js', '')
  }
  body {
    nav(class: 'navbar navbar-static-top'){
      a(class: 'navbar-brand', href: '/'){
        b('Dungeons &amp; Dragons')
      }
    }
    section(class: 'text-primary'){
	  mainBody()
	}
    footer(class: 'navbar navbar-fixed-bottom'){
      div(class: 'container-fluid'){
        ul(class: 'nav navbar-nav'){
          li(class: 'action'){ a(href: 'http://groovy-lang.org/templating.html', 'Groovy - Template Engine docs') }
          li(class: 'action'){ a(href: 'http://projects.spring.io/spring-boot/', 'Spring Boot docs') }
        }
      }
    }
  }
}