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
    <title>花木兰ICSA农场</title>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script type="text/javascript">
        function initialize() {
            var map;
            var myOptions;
            var marker;
            var lat = "${land.latitude}";
            var lng = "${land.longitude}";
            var zm = 7;

            if (zm == null || zm == "" || zm == "NaN") zm = 10;

            var latlng;
            if (lat != null && lat != "" && lng != null && lng != "") {
                latlng = new google.maps.LatLng(lat, lng);
                myOptions = {
                    zoom: zm,
                    center: latlng,
                    mapTypeControl: true,
                    navigationControl: true,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById("map"), myOptions);
                marker = new google.maps.Marker({
                    position: latlng,
                    map: map,
                    title:"${land.landName}"
                });
            } else {
                latlng = new google.maps.LatLng(35, 105);
                myOptions = {
                    zoom: 5,
                    center: latlng,
                    mapTypeControl: true,
                    navigationControl: true,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                map = new google.maps.Map(document.getElementById("map"), myOptions);
            }
        }
    </script>
</head>
<body onload="initialize()">
<div id="map" style="width: 345px; height: 250px;">map</div>
</body>
</html>