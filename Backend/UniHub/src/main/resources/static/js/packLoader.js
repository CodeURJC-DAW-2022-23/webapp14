  $(document).ready(function() {
    var currentPage = 0;
    $('#load-more-btn').click(function() {
      currentPage++;
      $.ajax({
        url: "/packs?page=" + currentPage,
        success: function(data) {
          if (data.trim().length == 0) {
            $('#load-more-btn').hide();
          } else {
            $('#packContent').append(data);
          }
        }
      });
    });
  });