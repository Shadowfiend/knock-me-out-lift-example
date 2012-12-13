(function() {
  var searchResults = ko.observableArray(),
      perPage = 3,
      needsPagination = ko.computed(function() {
        return searchResults().length > 3;
      }),
      pages = ko.computed(function() {
        var pages = [],
            pageCount = Math.ceil(searchResults().length / 3);
        for (var i = 0; i < pageCount; ++i) {
          var page =
            {
              number: i + 1,
              changePage: function() { currentPage(this.number - 1) }
            };
          page.isCurrent = ko.computed(function() {
            return currentPage() == this.number - 1
          }, page),
          
          pages.push(page);
        }

        return pages;
      }),
      currentPage = ko.observable(0),
      currentPageResults = ko.computed(function() {
        return searchResults().slice(currentPage() * perPage, currentPage() * perPage + perPage);
      });

  window.kmo = {}
  kmo.showResults = function(results) {
    results.forEach(function(object) {
      object.logoURL = "/images/logos/" + object.name.split(/\W/)[0].toLowerCase() + ".jpg";

      object.connected = ko.observable(object.connected);
      object.connect = function() { this.connected(true) };
      object.disconnect = function() { this.connected(false) };

      object.actions.forEach(function(action) {
        action.run = function() { alert(action.response) }
      })
    });

    searchResults(results);
  }

  var bindings = {
    needsPagination: needsPagination,
    pages: pages,
    currentPageResults: currentPageResults,
    currentPage: currentPage
  }

  kmo.bindings = bindings;

  $(document).ready(function() {
    $('.clearable').remove()
    ko.applyBindings(bindings, $('#search-results')[0]);
  });
})();
