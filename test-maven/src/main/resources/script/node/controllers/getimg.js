var request = require('request');
var fs = require('fs');
var url = 'http://1.255.144.56/movie/33600/M335635_src.jpg';

// http://1.255.144.54/getimg?src=http://1.255.144.56/movie/33600/M335635_src.jpg&dst=M335635_src.jpg
app.get('/getimg/*', function(req, res) {
	console.log('img');
	try {
		var src = req.query.url;
		var dst = req.query.dst;

		console.log("src : " + src);
		console.log("dst : " + dst);
		console.log(req.params[0]);

		var url = src;
		request(url, {
			encoding : 'binary'
		}, function(error, response, body) {
			fs.writeFile(dst, body, 'binary', function(err) {
			});
		});
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