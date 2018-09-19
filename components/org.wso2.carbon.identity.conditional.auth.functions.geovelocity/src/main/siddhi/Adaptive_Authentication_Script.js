// Specify the Siddhi application name.
var siddhiApplication = 'GeoVelocityBasedLogin';
// Specify the Siddhi input stream name.
var siddhiInputStream = 'InputStream';

function onLoginRequest(context) {
    executeStep(1, {
        onSuccess: function (context) {
            riskOnGeoVelocity(context);
        }
    });
}

function riskOnGeoVelocity(context){
  			var username = context.steps[1].subject.username;
    		var loginTime = String(Date.now());
		  	var loginIp = context.request.ip;

		  	Log.info('username '+username+' loginTime '+loginTime+' loginIp '+loginIp);
            callAnalytics({'Application':siddhiApplication,'InputStream':siddhiInputStream},{'username':username, 'loginTime':loginTime, 'loginIp':loginIp}, {
                onSuccess : function(context, data) {
                    Log.info('--------------- Geo-velocity Based Risk: ' + data.event.risk);
                    if (data.event.risk==1.0) {
                        executeStep(2);
                    }
				    else{
					  if(data.event.risk>0.5){
						 	executeStep(3);
						 }
					}
                }, onFail : function(context, data) {
                    Log.info('--------------- Failed to call analytics engine');
                    executeStep(2);
                }
            });
}
