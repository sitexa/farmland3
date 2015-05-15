<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>花木兰ICSA农场</title>
		<script type="text/javascript"
			src="http://www.google.com/jsapi?key=ABQIAAAAm5k4PC70DEaE48BDKO8vEhRSaoDlVm7re486ZD85Sj5u5dUmVxRdJ7gYJGZffMcswleR8o_MvJRRHg"></script>
		<script type="text/javascript">
		google.load("maps", "2", {"language" : "zh_CN"});

        var map;
        var lat = 35.460669951495305;
        var lng = 107.138671875;
        var zoom = 4;
        function initialize() {
            var latlng = new google.maps.LatLng(lat, lng);
            var location = "中国";

            if (google.loader.ClientLocation) {
            	lat = google.loader.ClientLocation.latitude;
            	lng = google.loader.ClientLocation.longitude;
                latlng = new google.maps.LatLng(lat,lng);
                location = getFormattedLocation();
            }

            map = new google.maps.Map2(document.getElementById('map'));
            map.setCenter(latlng, zoom);
            map.addControl(new GLargeMapControl());
            map.addControl(new GMapTypeControl());

            map.openInfoWindow(latlng,"用鼠标点击您的社区的位置,读出经纬度和缩放比.");

            GEvent.addListener(map, 'click', function(overlay, latlng) {
                if (latlng) {
                	lat = latlng.lat();
                	lng = latlng.lng();
                    var myHtml = "(纬度,经度): " + latlng.toUrlValue() + " <br>缩放比: " + map.getZoom();
                    myHtml += location;
                    map.openInfoWindow(latlng, myHtml);
                }
            });
        }

        function getFormattedLocation() {
            if (google.loader.ClientLocation.address.country_code == "US" && google.loader.ClientLocation.address.region) {
                return google.loader.ClientLocation.address.city + ", " + google.loader.ClientLocation.address.region.toUpperCase();
            } else {
                return  google.loader.ClientLocation.address.city + ", " + google.loader.ClientLocation.address.country_code;
            }
        }
        
        function importLatLng(){
        	window.opener.document.getElementById("frmBase_site_latitude").value = lat;
			window.opener.document.getElementById("frmBase_site_longitude").value = lng;
			window.close();
        }
    </script>
		<style type="text/css">
html body {
	margin: 0;
	padding: 0;
}
</style>
	</head>
	<body onload="initialize()" onunload="GUnload()">
		<input type="button" value="导入经纬度" onclick="importLatLng()" />
		<div id="map"
			style="width: 100%; height: 100%; margin: 0; padding: 0; border: 0;">
			map
		</div>
	</body>
</html>