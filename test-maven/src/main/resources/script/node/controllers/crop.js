/**
 * Created by zinnaworks-w on 2016. 6. 1..
 */
var fs = require('fs');
var cv = require('opencv');
// mcs.log.2016-09-26
// /data/img/still/20060270/c_kobis_still_20060270_10
app.get('/crop/*', function(req, res){
    console.log('get crop request!!');
    // var fpath = app.imgroot + req.params[0];
    try{
        var src = req.query.src;
        var dst = req.query.dst;

        console.log("src : " + src );
        console.log("dst : " + dst );

        crop(src,dst,function(err,result){
            var msg = {};
            if(err){
                msg = {result:'error'};
                res.json(msg);
            }
            else{
                msg = {result:result};
                res.json(msg);
            }
        })
    }catch(error){
        res.send({ state: 'NOK', msg: 'Could not find image from' });
    }
});

crop = function(src,dst,cb){
    console.log("crop start!!");
    try{

        cv.readImage(src, function(err, im){
            if(err) console.log(err);
            var imgW = im.width();
            var imgH = im.height();
            im.detectObject(cv.FACE_CASCADE, {}, function(err, faces){
                if(err) {
                    console.log("face detect err : " + err);
                    normalCrop(im,imgW,imgH,dst,cb);
                }else{
                    console.log("case length : " + faces.length);
                    if(faces.length == 0){
                        normalCrop(im,imgW,imgH,dst,cb);
                    }else if(faces.length == 1){

                        console.log("CASE2!!");
                        var fc = faces[0];
                        var fcX = fc.x;
                        var fcY = fc.y;
                        var fcW = fc.width;
                        var fcH = fc.height;
                        var fxCX = fcX + (fcW)/2;
                        var fxCY = fcY + (fcH)/2;
                        var s;
                        var fcS;
                        var x;
                        var y;
                        if(imgW > imgH){
                            s = imgH;
                            fcS = fxCX;
                        }else{
                            s = imgW;
                            fcS = fxCY;
                        }
                        var l = s == imgW ? imgH : imgW;
                        var c = s/2;

                        if(fcS <= c){ // 왼쪽으로 치우침.
                            console.log("left");
                            x = 0;
                            y = 0;

                        }else if(fcS >= l-c){ // 오르쪽으로 치우침.
                            console.log("right");
                            if(s == imgW){
                                x = 0;
                                y = l-s;
                            }else{
                                x = l-s;
                                y = 0;
                            }
                        }else{
                            console.log("center");
                            if(s == imgW){
                                x = 0;
                                y = fcS - c;
                            }else{
                                x = fcS - c;
                                y = 0;
                            }

                        }
                        console.log("imgW : " + imgW);
                        console.log("imgH : " + imgH);
                        console.log("fcX : " + fcX);
                        console.log("fcY : " + fcY);
                        console.log("fcW : " + fcW);
                        console.log("fcH : " + fcH);
                        console.log("fxCX : " + fxCX);
                        console.log("fxCY : " + fxCY);
                        console.log("l : " + l);
                        console.log("c : " + c);
                        console.log("s : " + s);
                        console.log("fcS : " + fcS);
                        console.log("x : " + x);
                        console.log("y : " + y);

                        var img_crop = im.crop(x,y,s,s);
                        img_crop.save(dst,function(err,result){
                            if (err) {
                                console.log("Image crop error : " + err.message);
                                cb("error",null);
                            }
                            console.log("Image crop success !!" + dst);
                            return cb(null,"OK");
                        });


                    }else{
                        console.log("CASE3!!");
                        var type;
                        if(imgW > imgH){
                            type = 'W';
                        }else{
                            type = 'H';
                        }
                        console.log("type : " + type);
                        var minFace = null;
                        var maxFace = null;
                        for (var i=0;i<faces.length; i++){
                            var fc = faces[i]
                            //im.ellipse(x.x + x.width/2, x.y + x.height/2, x.width/2, x.height/2);
                            if(type == 'W'){
                                if(minFace == null){
                                    minFace = fc;
                                }else{
                                    if(minFace.x > fc.x){
                                        minFace = fc;
                                    }
                                }

                                if(maxFace == null){
                                    maxFace = fc;
                                }else{
                                    if(maxFace.x+maxFace.width < fc.x+ fc.width){
                                        maxFace = fc;
                                    }
                                }
                            }else{
                                if(minFace == null){
                                    minFace = fc;
                                }else{
                                    if(minFace.y > fc.y){
                                        minFace = fc;
                                    }
                                }

                                if(maxFace == null){
                                    maxFace = fc;
                                }else{
                                    if(maxFace.y+maxFace.height < fc.y+ fc.height){
                                        maxFace = fc;
                                    }
                                }
                            }
                        }

                        console.log("imgW : " + imgW);
                        console.log("imgH : " + imgH);
                        console.log("minFace.x : " + minFace.x);
                        console.log("minFace.y : " + minFace.y);
                        console.log("minFace.width : " + minFace.width);
                        console.log("minFace.height : " + minFace.height);

                        console.log("maxFace.x : " + maxFace.x);
                        console.log("maxFace.y : " + maxFace.y);
                        console.log("maxFace.width : " + maxFace.width);
                        console.log("maxFace.height : " + maxFace.height);

                        var s;
                        var l;
                        var c;
                        var len;

                        if(type == 'W'){
                            console.log("type : " + type);
                            s = imgH;
                            l = imgW;
                            len = maxFace.x+maxFace.width-minFace.x;

                        }else{
                            console.log("type : " + type);
                            s = imgW;
                            l = imgH;
                            len = maxFace.y+maxFace.height-minFace.y;
                        }
                        console.log("s : " + s);
                        console.log("l : " + l);
                        c = s/2;
                        console.log("len : " + len + " >> s : " + s);
                        if(len > s){ // 일반
                            console.log("distance long!!");
                            normalCrop(im,imgW,imgH,dst,cb);
                        }else{
                            if(type == 'W'){
                                if(minFace.x + len/2 - c <= 0){ //왼쪽 쏠림
                                    x = 0;
                                }else if(minFace.x + len/2 - c + s > imgW){ //오른쪽 쏠림
                                    x = imgW-s;
                                }else{
                                    x = minFace.x + len/2 - c; //중앙
                                }
                                y = 0;
                            }else{
                                x = 0;
                                if(minFace.y + len/2 - c <= 0){ //왼쪽 쏠림
                                    y = 0;
                                }else if(minFace.y + len/2 - c + s > imgH){ //오른쪽 쏠림
                                    y = imgH-s;
                                }else{
                                    y = minFace.y + len/2 - c; //중앙
                                }
                            }
                            console.log("xys : " + x,y,s);
                            var img_crop = im.crop(x,y,s,s);
                            img_crop.save(dst,function(err,result){
                                if (err) {
                                    console.log("Image crop error : " + err.message);
                                    cb("error",null);
                                }
                                console.log("Image crop success !!" + dst);
                                return cb(null,"OK");
                            });
                        }
                    }
                }
            });
        });
    }catch(e){
        console.log("Image crop error !!" + e.message);
    }
}

function normalCrop(im,w,h,dst,cb){
    var l = w > h ? h : w;

    var x, y;

    if (l == w) {
        x = 0;
        y = (h - l) / 2;
    } else {
        x = (w - l) / 2;
        y = 0;
    }
    console.log("x,y,l : " + x,y,l);
    var img_crop = im.crop(x,y,l,l);
    img_crop.save(dst,function(errr,result){
        if (errr) {
            console.log("Image crop error : " + errr.message);
            return cb("error",null);
        }
        console.log("Image crop success !!" + dst);
        return cb(null,"OK");
    });
}