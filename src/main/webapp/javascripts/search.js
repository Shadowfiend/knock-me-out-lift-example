(function() {
  var searchResults = ko.observableArray();

  window.kmo = {}
  kmo.showResults = function(results) {
    searchResults(results);
  }

  var bindings = {
    searchResults: searchResults
  }

  kmo.bindings = bindings;

  $(document).ready(function() {
    $('.clearable').remove()
    ko.applyBindings(bindings, $('#search-results')[0]);
  });
})();
