/***********************************************
 * DHTML slideshow script-  © Dynamic Drive DHTML code library (www.dynamicdrive.com)
 * This notice must stay intact for legal use
 * Visit http://www.dynamicdrive.com/ for full source code
 ***********************************************/

var photos = new Array();
var photoslink = new Array();
var which = 0;
//Specify whether images should be linked or not (1=linked)
var linkornot = 0;

var titles = new Array();
var descriptions = new Array();

function showPic(id) {
    which = id;
    applyeffect();
    document.getElementById("photoslider").src = photos[which];
    document.getElementById("photodescription").innerHTML = descriptions[which];
    playeffect();
    keeptrack();
}

function showWin(aid) {
    var winNode = $("#picbox");
    winNode.css("display", "block")
    	   .css("top",getScrollTop());
    applyeffect();
    $("#title").text(titles[aid]);
    $("#photoslider").width("auto").height("auto").attr("src", photos[aid]);
    playeffect();
    keeptrack();
}

function closeWin() {
    var winNode = $("#picbox");
    winNode.css("display", "none");
}

function next() {
    if (which < photos.length - 1) {
        which++;
    } else {
        which = 0;
    }
    applyeffect();
    $("#title").text(titles[which]);
    $("#photoslider").attr("src", photos[which]);
    playeffect();
    keeptrack();
}

function applyeffect() {
    if (document.all && photoslider.filters) {
        photoslider.filters.revealTrans.Transition = Math.floor(Math.random() * 23);
        photoslider.filters.revealTrans.stop();
        photoslider.filters.revealTrans.apply();
    }
}

function playeffect() {
    if (document.all && photoslider.filters)
        photoslider.filters.revealTrans.play();
}

function keeptrack() {
    window.status = "Image " + (which + 1) + " of " + photos.length;
}

function backward() {
    if (which > 0) {
        which--;
        applyeffect();
        document.images.photoslider.src = photos[which];
        document.getElementById("photodescription").innerHTML = descriptions[which];

        playeffect();
        keeptrack();
    }
}

function forward() {
    if (which < photos.length - 1) {
        which++;
    } else {
        which = 0;
    }
    applyeffect();
    document.images.photoslider.src = photos[which];
    document.getElementById("photodescription").innerHTML = descriptions[which];
    playeffect();
    keeptrack();
}

function transport() {
    window.location = photoslink[which];
}

