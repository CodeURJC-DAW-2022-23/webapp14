const imgButton = document.getElementById('img-button')
const imgContainer = document.getElementById('img-container')
const images = imgContainer.getElementsByTagName('img')

imgButton.addEventListener('click', function () {
   if (imgContainer.style.display === 'none') {
      imgContainer.style.display = 'inline-block'
   } else {
      imgContainer.style.display = 'none'
   }
})

for (let i = 0; i < images.length; i++) {
   images[i].addEventListener('click', function () {
      const selectedImage = this.src
      imgContainer.style.display = 'none'
   })
}
