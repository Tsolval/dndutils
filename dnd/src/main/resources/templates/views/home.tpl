layout 'layouts/main.tpl', 
   pageTitle: 'Dungeons &amp; Dragons', 
   mainBody: contents {
      header(class: 'page-header'){
        h3(class: 'text-success', 'Dungeons & Dragons Utilities')
      }
      div(class: 'row'){
        div(class: 'col-xs-3'){
          div(class: 'panel panel-primary'){
            div(class: 'panel-heading'){
              h4(class: 'panel-title', 'Generate Loot')
            }
            div(class: 'panel-body') {
              a(href: '/loot'){p('Generate Loot')}
              a(href: '/hoard'){p('Generate Hoard')}
            }
          }
        }
      }
      

      div(class: 'clearfix float-xs-right', "This is an application using Boot $bootVersion and Groovy templates $groovyVersion")
   }
