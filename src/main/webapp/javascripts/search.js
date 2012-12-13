(function() {
  var searchResults = ko.observableArray();

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
    searchResults: searchResults
  }

  kmo.bindings = bindings;

  $(document).ready(function() {
    $('.clearable').remove()
    ko.applyBindings(bindings, $('#search-results')[0]);
  });
})();
