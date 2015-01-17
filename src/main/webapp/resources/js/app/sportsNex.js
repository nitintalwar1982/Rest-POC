angular.module('sportsNex', ['ngRoute', 'ngCookies', 'sportsNex.services'])
	.config(
		[ '$routeProvider', '$locationProvider', '$resourceProvider', '$httpProvider', function($routeProvider, $locationProvider, $resourceProvider, $httpProvider) {
			
			$routeProvider.when('/register', {
				templateUrl: 'resources/partials/register.html',
				controller: RegisterController
			});
			
			/*$routeProvider.when('/edit/:id', {
				templateUrl: 'partials/edit.html',
				controller: EditController
			});

			$routeProvider.when('/login', {
				templateUrl: 'partials/login.html',
				controller: LoginController
			});*/
			
			$routeProvider.otherwise({
				templateUrl: 'resources/partials/register.html',
				controller: RegisterController
			});
			
			$locationProvider.hashPrefix('!');
			
			 // Don't strip trailing slashes from calculated URLs
			 /*$resourceProvider.defaults.stripTrailingSlashes = false;*/
			
			/* Register error provider that shows message on failed requests or redirects to login page on
			 * unauthenticated requests */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
			        return {
			        	'responseError': function(rejection) {
			        		var status = rejection.status;
			        		var config = rejection.config;
			        		var method = config.method;
			        		var url = config.url;
			      
			        		if (status == 401) {
			        			$location.path( "/register" );
			        		} else {
			        			$rootScope.error = method + " on " + url + " failed with status " + status;
			        		}
			              
			        		return $q.reject(rejection);
			        	}
			        };
			    }
		    );
		    
		    /* Registers auth token interceptor, auth token is either passed by header or by query parameter
		     * as soon as there is an authenticated user */
		    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
		        return {
		        	'request': function(config) {
		        		var isRestCall = config.url.indexOf('rest') == 0;
		        		if (isRestCall && angular.isDefined($rootScope.authToken)) {
		        			var authToken = $rootScope.authToken;
		        			if (sportsNexConfig.useAuthTokenHeader) {
		        				config.headers['X-Auth-Token'] = authToken;
		        			} else {
		        				config.url = config.url + "?token=" + authToken;
		        			}
		        		}
		        		return config || $q.when(config);
		        	}
		        };
		    }
	    );
		   
		} ]
		
	).run(function($rootScope, $location, $cookieStore, UserService) {
		
		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});
		
		$rootScope.hasRole = function(role) {
			
			if ($rootScope.user === undefined) {
				return false;
			}
			
			if ($rootScope.user.roles[role] === undefined) {
				return false;
			}
			
			return $rootScope.user.roles[role];
		};
		
		$rootScope.logout = function() {
			delete $rootScope.user;
			delete $rootScope.authToken;
			$cookieStore.remove('authToken');
			$location.path("/login");
		};
		
		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		$location.path("/login");
		var authToken = $cookieStore.get('authToken');
		if (authToken !== undefined) {
			$rootScope.authToken = authToken;
			UserService.get(function(user) {
				$rootScope.user = user;
				$location.path(originalPath);
			});
		}
		
		$rootScope.initialized = true;
	});


/*function IndexController($scope, NewsService) {
	
	$scope.newsEntries = NewsService.query();
	
	$scope.deleteEntry = function(newsEntry) {
		newsEntry.$remove(function() {
			$scope.newsEntries = NewsService.query();
		});
	};
};


function EditController($scope, $routeParams, $location, NewsService) {

	$scope.newsEntry = NewsService.get({id: $routeParams.id});
	
	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('/');
		});
	};
};


function CreateController($scope, $location, NewsService) {
	
	$scope.newsEntry = new NewsService();
	
	$scope.save = function() {
		$scope.newsEntry.$save(function() {
			$location.path('/');
		});
	};
};*/


function RegisterController($scope, $rootScope, $location, $cookieStore, UserService) {
	
	$scope.rememberMe = false;
	$scope.userEmailId = "fsdfdsfdsf";
	$scope.userPassword = "dsfdsfsdfdsfds";
	$scope.userTypePID = 'Individual';
	
	$scope.selectProfile = function(className, text) {
		$('.' + className).attr("data-placeholder", text);
		$scope.userTypePID = text;
		$('.reg-ul').css("z-index", "10");
		$('.reg-ul').css("display", "none");
	}
	
	$scope.popButton = function(obj) {
		obj = obj.target;
		$.each($('.nex-btn'), function(index, item) {
			$(item).css("z-index", "10");
			$(item).attr('placeholder', $(item).attr('data-placeholder'));
		});
		$(obj).css("z-index", "10000");
		$('.login-msg').text($(obj).attr('data-msg'));

		var regFlag = $(obj).hasClass('register-btn-reg');
		var ptFlag = $(obj).hasClass('register-btn-pt');
		$('.reg-ul').css("z-index", "10");
		$('.reg-ul').css("display", "none");
		if (!regFlag && !ptFlag) {
			$(obj).attr('placeholder', /* $(obj).attr('data-msg') */'...');
			$(obj).focus();
		} else if (ptFlag) {
			$('.reg-ul').css("z-index", "10001");
			$('.reg-ul').css("display", "block");
		} else {

		}

	}
	
	$scope.registerUser = function () {
		
		var data = {
			    "lastModifiedBy": "nitin talwar",
			    "sportsType": "Individual",
			    "userEmailAddress": $scope.userEmailId,
			    "userPassword": $scope.userPassword,
			    "userTypePID": $scope.userTypePID
			}; 
			$.ajax({
				url : 'http://localhost:9080/nSports/rest/sports/registerUser',
				dataType : 'json',
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(data),
				success : function(data, textStatus, jQxhr) {
					console.log(data);
				},
				error : function(jqXhr, textStatus, errorThrown) {
					console.log(errorThrown);
				}
			});
	}
	
	$scope.register = function() {
		UserService.registerUser($.param($scope.data), 
		function(authenticationResult) {
			var authToken = authenticationResult.token;
			$rootScope.authToken = authToken;
			if ($scope.rememberMe) {
				$cookieStore.put('authToken', authToken);
			}
			UserService.get(function(user) {
				$rootScope.user = user;
				$location.path("/");
			});
		});
	};
	
	/*$scope.login = function() {
		UserService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
			var authToken = authenticationResult.token;
			$rootScope.authToken = authToken;
			if ($scope.rememberMe) {
				$cookieStore.put('authToken', authToken);
			}
			UserService.get(function(user) {
				$rootScope.user = user;
				$location.path("/");
			});
		});
	};*/
};


var services = angular.module('sportsNex.services', ['ngResource']);

services.factory('UserService', function($resource) {
	
	var User = $resource('/userEmailAddress/:userEmailAddress/userPassword/:userPassword',
	 {userEmailAddress:123, userPassword:'@id'}, {
		 register: {method:'POST', params:{charge:true}}
	 });
	
	return $resource('/nSports/rest/sports/', {},
			{
				registerUser: {
					method: 'POST',
					params: {'userEmailAddress' : 'userEmailAddress'},
					headers : {'Content-Type': 'application/json'},
					responseType : 'json',
					interceptor: {
		                response: function (data) {
		                    console.log('response in interceptor', data);
		                },
		                responseError: function (data) {
		                    console.log('error in interceptor', data);
		                }
		            }
				},
			}
		);
});

/*services.factory('NewsService', function($resource) {
	
	return $resource('rest/news/:id', {id: '@id'});
});*/