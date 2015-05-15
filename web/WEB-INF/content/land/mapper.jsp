<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>Mapper</title>
    <link href="http://code.google.com/apis/maps/documentation/javascript/examples/default.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true&language=zh&region=CN"></script>
    <script type="text/javascript" src="http://code.google.com/apis/gears/gears_init.js"></script>
    <script type="text/javascript">
        var lat = ${lat};
        var lng = ${lng};
        var zoom = ${zoom};

        var changsha = new google.maps.LatLng(28.209197099353283, 112.9874431993164);
        var beijing = new google.maps.LatLng(40.09626105188113, 16.36711238876951);
        var browserSupportFlag = new Boolean();
        var initialLocation;
        var map;
        var myOptions;
        var marker;

        function initialize() {
            myOptions = {
                zoom: 11,
                scaleControl: true,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
            if (lat != 0 && lng != 0) {
                setLocation();
            } else {
                handleGeolotion();
            }
            onClick();
        }

        function onClick() {
            google.maps.event.addListener(map, 'click', function(event) {
                document.getElementById("latitude").value = event.latLng.lat();
                document.getElementById("longitude").value = event.latLng.lng();
                addMarker(event.latLng);
            });
        }

        function addMarker(position) {
            marker = new google.maps.Marker({
                position:position,
                map: map
            });
        }

        function setLocation() {
            initialLocation = new google.maps.LatLng(lat, lng);
            map.setCenter(initialLocation);
        }

        function handleGeolotion() {
            if (navigator.geolocation) {
                browserSupportFlag = true;
                navigator.geolocation.getCurrentPosition(function(position) {
                    initialLocation = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
                    map.setCenter(initialLocation);
                }, function() {
                    handleNoGeolocation(browserSupportFlag);
                });
            } else if (google.gears) {
                // Try Google Gears Geolocation
                browserSupportFlag = true;
                var geo = google.gears.factory.create('beta.geolocation');
                geo.getCurrentPosition(function(position) {
                    initialLocation = new google.maps.LatLng(position.latitude, position.longitude);
                    map.setCenter(initialLocation);
                }, function() {
                    handleNoGeolocation(browserSupportFlag);
                });
            } else {
                // Browser doesn't support Geolocation
                browserSupportFlag = false;
                handleNoGeolocation(browserSupportFlag);
            }
        }

        function handleNoGeolocation(errorFlag) {
            if (errorFlag) {
                initialLocation = beijing;
            } else {
                initialLocation = changsha;
            }
            map.setCenter(initialLocation);
        }

        function doReturn() {
            lat = document.getElementById("latitude").value;
            lng = document.getElementById("longitude").value;
            window.parent.returnValue = lat + ";" + lng;
            window.parent.close();
        }

    </script>

</head>
<body onload="initialize()">
<input id="latitude" name="latitude" type="text" value="" size="20"/>
<input id="longitude" name="longitude" type="text" value="" size="20"/>
<input type="button" value="确定" onclick="doReturn()"/>

<div id="map_canvas"></div>
</body>
</html>