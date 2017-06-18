layout 'layouts/main.tpl', pageTitle: 'Loot Generator', mainBody: contents {
  header(class: 'page-header') {
    h3(class: 'text-success', 'Dungeons & Dragons Loot Generator')
  }
  form(class: 'form-horizontal', action: '/loot', method: 'post') {
    fieldset {
      legend('Individual Treasure')
      div(class: 'form-group') {
        label(for: 'challengeRating', class: 'col-xs-2 control-label', 'Challenge Rating')
        div(class: 'col-xs-2') {
          input(type: 'text', class: 'form-control', id: 'challengeRating') {}
        }
      }
      div(class: 'form-group') {
        label(for: 'creatureCount', class: 'col-xs-2 control-label', 'Creature Count')
        div(class: 'col-xs-2') {
          input(type: 'text', class: 'form-control', id: 'creatureCount') {}
        }
      }
      div(class: 'form-group') {
        div(class: 'col-xs-offset-2 col-xs-1') {
          button(type: 'reset', class: 'btn btn-default', 'Cancel')
        }
        div(class: 'col-xs-1') {
          button(type: 'submit', class: 'btn btn-primary', 'Submit')
        }
      }
    }
  }
  hr{}
  section {
    div(class: 'container') {
      div(class: 'panel panel-primary'){
        table(class: 'table table-bordered') {
          tr(class: 'bg-primary') {
            th(class: 'text-center', 'PP')
            th(class: 'text-center', 'GP')
            th(class: 'text-center', 'EP')
            th(class: 'text-center', 'SP')
            th(class: 'text-center', 'CP')
          } 
          tr(class: 'text-center') {
            td(loot.pp)
            td(loot.gp)
            td(loot.ep)
            td(loot.sp)
            td(loot.cp)
          }
        }
      }
      div(class: 'panel panel-primary'){
        table(class: 'table table-bordered') {
          tr(class: 'bg-primary') {
            th(class: 'text-center', 'Gems')
            th(class: 'text-center', 'Art')
          } 
          tr(class: 'text-center') {
            td('Gems')
            td('Art')
          } 
        }
      }
    }
  }
}
