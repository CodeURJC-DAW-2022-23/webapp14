$(document).ready(function() {
  // Adds click event to the load-more Button on the packs web.
  $('#load-more-btn').click(function() {
    var page = parseInt($(this).data('page')) + 1; // Gets the following page
    $.ajax({
      url: '/packs?page=' + page, // Controller URL
      type: 'GET',
      success: function(response) {
        // ADDS the extra packages
        $('#packContent').append(response);
        // Updates the page number to the counter
        $('#load-more-btn').data('page', page);
      }
    });
  });
});