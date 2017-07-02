var express = require('express');
app = exports.module = express();
app.port = 3000;
app.imgroot = '/svc/img/';

require('./controllers/img');
require('./controllers/getimg');
//require('./controllers/crop');

if (!module.parent) {
	app.listen(app.port);
	console.log('app is start with ' + app.port + ' port..');
}