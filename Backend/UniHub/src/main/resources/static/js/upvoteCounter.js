function submitForm() {
  var input = document.getElementById("threadUpvote");
  input.value = "1";
  var form = document.getElementById("upvoteThread");
  form.submit();
}

