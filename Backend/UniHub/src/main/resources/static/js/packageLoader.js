document.addEventListener("DOMContentLoaded", function() {
  var button = document.getElementById("load-more-btn");
  var limit = 2;
  var offset = limit;
  button.addEventListener("click", function() {
    loadPacks(limit, offset);
    offset += limit;
  });
});

function loadPacks(limit, offset) {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "/load-more-packs?limit=" + limit + "&offset=" + offset);
  xhr.onload = function() {
    if (xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      var packList = response.packList;
      var packContent = document.getElementById("packContent");
      var packExtra = document.getElementById("pack-extra");
      for (var i = 0; i < packList.length; i++) {
        var pack = packList[i];
        var packDiv = document.createElement("div");
        packDiv.className = "product-column col-md-3";
        var packLink = document.createElement("a");
        packLink.className = "packInfo-link";
        packLink.href = "/packInfo/" + pack.id;
        var packImg = document.createElement("img");
        packImg.src = "/static/" + pack.packImage;
        var packSection = document.createElement("section");
        packSection.className = "p-3";
        var packTitle = document.createElement("h3");
        packTitle.innerText = pack.packTitle;
        var packDescription = document.createElement("p");
        packDescription.innerText = pack.packDescription_short;
        var packTags = pack.tags;
        for (var j = 0; j < packTags.length; j++) {
          var tag = packTags[j];
          var tagSpan = document.createElement("span");
          tagSpan.className = "tag " + tag.tagType;
          tagSpan.innerText = tag.tagName;
          packSection.appendChild(tagSpan);
        }
        var purchaseSpan = document.createElement("span");
        purchaseSpan.className = "purchase-span";
        var purchaseLogo = document.createElement("object");
        purchaseLogo.className = "purchase-logo";
        purchaseLogo.data = "/static/img/Loaders/purchase.svg";
        purchaseLogo.type = "image/svg+xml";
        purchaseSpan.appendChild(purchaseLogo);
        packSection.appendChild(packTitle);
        packSection.appendChild(packDescription);
        packSection.appendChild(purchaseSpan);
        packLink.appendChild(packImg);
        packLink.appendChild(packSection);
        packDiv.appendChild(packLink);
        packExtra.appendChild(packDiv);
      }
      if (response.hasMorePacks === false) {
        var button = document.getElementById("load-more-btn");
        button.style.display = "none";
      }
    }
  };
  xhr.send();
}