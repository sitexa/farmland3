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
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript">
        var map;
        var lat = "${lat}";
        var lng = "${lng}";
        var zm = parseInt("${zoom}");
        var positions = ${landPositions};
        var names = ${landNames};

        function initialize() {
            var myOptions;

            if (zm == null || zm == "" || zm == "NaN") zm = 5;

            if (lat == null || lat == "" || lng == null || lng == "") {
                lat = 35.000;
                lng = 105.000;
                zm = 3;
            }

            myOptions = {
                zoom: zm,
                center: new google.maps.LatLng(lat, lng),
                mapTypeControl: true,
                navigationControl: true,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map"), myOptions);
            addPosition();
        }

        function addPosition() {
            try {
                for (var i = 0; i < positions.length; i++) {
                    var marker = new google.maps.Marker({
                        position:positions[i],
                        map: map,
                        clickable:true,
                        title:names[i]["landName"]
                    });

                    var url = "/land/land/" + names[i]["landId"];
                    addListener(marker, url);
                }
            } catch(e) {
                alert(e);
            }
        }

        function addListener(marker, url) {
            google.maps.event.addListener(marker, 'click', function() {
                location.href = url;
            });
        }
    </script>
</head>
<body onload="initialize()">
<div id="map" style="width: 380px; height: 280px;">map</div>
</body>
</html>