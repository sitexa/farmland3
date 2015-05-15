<%--
  User: xnpeng
  Date: 2009-4-17
  Time: 16:23:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>map</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true_or_false&amp;key=ABQIAAAAm5k4PC70DEaE48BDKO8vEhRI_mVITh0n0XeF5G38e-KjGGEB2xSqdnuNnr7WqTFabF_OkcFnVB1IZQ"
            type="text/javascript"></script>
<%--
    <script src="http://www.google.com/jsapi?key=ABQIAAAAm5k4PC70DEaE48BDKO8vEhRI_mVITh0n0XeF5G38e-KjGGEB2xSqdnuNnr7WqTFabF_OkcFnVB1IZQ&hl=zh&sensor=true"
            type="text/javascript"></script>
--%>
    <script type="text/javascript">
        google.load("maps", "2.x", {"language":"zh_CN"});
        function initialize() {
            try{
            var lat = "${lat}";
            var lng = "${lng}";
            var zoom = parseInt("${zoom}");
            if (zoom == null || zoom == "" || zoom == "NaN") zoom = 13;
            if (GBrowserIsCompatible()) {
                if (lat == "" && lng == "") {
                    if (google.loader.ClientLocation) {
                        var cl = google.loader.ClientLocation;
                        createMap(cl.latitude, cl.longitude, 13);
                    }
                } else {
                    createMap(lat, lng, zoom);
                }
            }
            }catch(e){}
        }

        function createMap(lat, lng, zoom) {
            try{
            var mapElement = document.getElementById("map");
            mapElement.style.display = 'block';
            var map = new google.maps.Map2(mapElement);
            map.addControl(new GSmallMapControl());
            map.addControl(new GMapTypeControl());
            map.setCenter(new google.maps.LatLng(lat, lng), zoom);
            GEvent.addListener(map, "click", function(latlng) {
                var sUrl = "<%=request.getContextPath()%>/map?lat="+lat+"&lng="+lng;
                var win=window.open(sUrl,"",'width=800,height=600');
            });
            }catch(e){}
        }
    </script>
</head>
<body onload="initialize()" onunload="GUnload()">
<div id="map" style="width: 380px; height: 280px;">map</div>
</body>
</html>