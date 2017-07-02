var fs = require('fs');

app.get('/img/*', function(req, res) {
	console.log('img');
	try {
		console.log(req.params[0]);
		var img = fs.readFileSync(req.params[0]);
		res.writeHead(200, {
			'Content-Type' : 'image/jpeg'
		});
		res.end(img, 'binary');

	} catch (error) {
		res.send({
			state : 'NOK',
			msg : 'Could not find image from'
		});
	}
});