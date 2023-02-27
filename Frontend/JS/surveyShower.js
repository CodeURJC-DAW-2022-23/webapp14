function toggleSurvey(id) {
   var content = document.getElementById('survey' + id + '-content')
   if (content.style.display === 'none') {
      content.style.display = 'block'
   } else {
      content.style.display = 'none'
   }
}
