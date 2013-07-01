hypercab.service('settingsService',['$http', function($http){

    var settings = {};

    this.getSettings = function(){
        if(!settings.settings){
            console.log('Loading hyperpin settings...');
            fetchSettings();
        }
        return settings;
    }

    function fetchSettings(){
        $http.get('../hypercab-rest/settings').success(function(data){
            settings.settings = data;
            console.log('Stored hyperpin settings');
        })
    }
}]);