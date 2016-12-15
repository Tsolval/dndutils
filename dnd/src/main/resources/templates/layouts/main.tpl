yieldUnescaped '<!DOCTYPE html>'
html {
  head {
    title(pageTitle)
    // add css
    link(rel: 'stylesheet', href: '/css/bootstrap.min.css')
    // add jQuery
    //script(src: 'https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js')
    // add JavaScript
	//script(src: 'https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js')
	// set the character set
	//meta(charset: 'utf-8')
	// make mobile compatible
	//meta(name: 'viewport', content: 'width=device-width, initial-scale=1')
  }
  body {
    div(class: 'container') {
      div(class: 'navbar') {
        div(class: 'navbar-inner') {
          a(class: 'brand',
              href: 'http://groovy-lang.org/templating.html',
              'Groovy - Template Engine docs')
          a(class: 'brand',
              href: 'http://projects.spring.io/spring-boot/') {
            yield 'Spring Boot docs'
          }
        }
      }
      mainBody()
    }
  }
}