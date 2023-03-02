function submitPost(postID) {
  var input = document.getElementById("upvotePost");
  input.value = "1";
  var input = document.getElementById("postIteration")
  input.value = postID;
  var form = document.getElementById("upvotePosts");
  form.submit();
};