function SelectController($scope, $http) {

        $http.get('http://localhost:8080/RESTfulBookstore/bookstoreService/customer/1').
        success(function(data) {
                $scope.destinations2 = data;
                Window.alert("Help!!!");

        });



        //Will eventually call RESTful service.
        $scope.destinations = [
          {
            "name": "Algarve",
              "departures": [
              {
                "name": "Birmingham",
              },
              {
                "name": "Bristol",
              },
              {
                "name": "East Midlands",
              }
            ]
            
          },

          {
            "name": "Majorca",
            "departures": [
              {
                "name": "London",
              },
              {
                "name": "Birmingham",
              },
              {
                "name": "Newcastle",
              }
            ]
            
          },

          {
            "name": "Barcelona",
              "departures": [
              {
                "name": "Bristol",
              },
              {
                "name": "Norwich",
              }
            ]
            
          }
        ];
    }
