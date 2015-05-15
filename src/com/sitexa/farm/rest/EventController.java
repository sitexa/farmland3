package com.sitexa.farm.rest;

import com.sitexa.framework.Constants;
import com.sitexa.framework.struts.BaseAction;
import com.sitexa.site.data.Site;
import com.sitexa.site.service.SiteService;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: xnpeng
 * Date: 2010-2-24
 * Time: 9:41:09
 */
public class EventController extends BaseAction {

    public void index(){
        System.out.println("EventController.index");
        HttpServletRequest req = ServletActionContext.getRequest();
        HttpServletResponse res = ServletActionContext.getResponse();

        String uid = req.getParameter("uid");

        System.out.println("uid = " + uid);

        String result = getData(uid);

        System.out.println("result = " + result);

        try{
            res.getWriter().print(result);
        } catch(Exception e){
            e.printStackTrace();
        }

    }


    private String getData(String uid){
        String s = "<data>\n" +
                "    <event id=\"2\">\n" +
                "        <start_date><![CDATA[2009-05-24 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-08 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[French Open]]></text>\n" +
                "        <details><![CDATA[Philippe-Chatrier Court Paris, FRA]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"3\">\n" +
                "        <start_date><![CDATA[2009-06-10 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-13 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Aegon Championship]]></text>\n" +
                "        <details><![CDATA[The Queens Club London, ENG]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"4\">\n" +
                "        <start_date><![CDATA[2009-06-21 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-05 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Wimbledon]]></text>\n" +
                "        <details><![CDATA[Wimbledon June 21, 2009 - July 5, 2009]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"5\">\n" +
                "        <start_date><![CDATA[2009-07-18 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-27 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Indianapolis Tennis Championships]]></text>\n" +
                "        <details><![CDATA[Indianapolis Tennis Center Indianapolis, IN]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"8\">\n" +
                "        <start_date><![CDATA[2009-07-27 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-02 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Countrywide Classic Tennis]]></text>\n" +
                "        <details><![CDATA[Los Angeles Tennis Center. Los Angeles, CA]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"7\">\n" +
                "        <start_date><![CDATA[2009-05-11 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-18 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[ATP Master Tennis]]></text>\n" +
                "        <details><![CDATA[La Caja Magica.Madrid, Spain]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"9\">\n" +
                "        <start_date><![CDATA[2009-08-01 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-11 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Legg Mason Tennis Classic]]></text>\n" +
                "        <details><![CDATA[Fitzgerald Tennis Center Washington D.C.]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"10\">\n" +
                "        <start_date><![CDATA[2009-08-07 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-17 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Western & Southern Financial Group Women's Open]]></text>\n" +
                "        <details><![CDATA[Lindner Family Tennis Center Mason, OH]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"11\">\n" +
                "        <start_date><![CDATA[2009-08-15 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-24 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Rogers Cup Women's Tennis]]></text>\n" +
                "        <details><![CDATA[Rexall Centre Toronto, ON]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"12\">\n" +
                "        <start_date><![CDATA[2009-08-29 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-09-14 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[US Open Tennis Championship]]></text>\n" +
                "        <details><![CDATA[Arthur Ashe Stadium Flushing, NY]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"13\">\n" +
                "        <start_date><![CDATA[2009-11-22 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-28 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Barclays ATP World Tour Finals]]></text>\n" +
                "        <details><![CDATA[O2 Dome London, ENG]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"14\">\n" +
                "        <start_date><![CDATA[2009-08-17 00:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-24 00:00:00]]></end_date>\n" +
                "        <text><![CDATA[Western & Southern Financial Group Masters Tennis]]></text>\n" +
                "        <details><![CDATA[Lindner Family Tennis Center\n" +
                "Mason, OH]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"15\">\n" +
                "        <start_date><![CDATA[2009-05-16 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-16 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Parc Izvor ]]></text>\n" +
                "        <details><![CDATA[ Bucharest, Romania ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"16\">\n" +
                "        <start_date><![CDATA[2009-05-21 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-21 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Arena Zagreb ]]></text>\n" +
                "        <details><![CDATA[ Zagreb, Croatia ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"17\">\n" +
                "        <start_date><![CDATA[2009-05-23 11:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-23 14:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Gwardia Stadium ]]></text>\n" +
                "        <details><![CDATA[ Warsaw, Poland ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"18\">\n" +
                "        <start_date><![CDATA[2009-05-25 19:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-25 22:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Skonto Stadium - Riga ]]></text>\n" +
                "        <details><![CDATA[ Riga, Latvia ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"19\">\n" +
                "        <start_date><![CDATA[2009-05-27 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-27 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Zalgirio Stadionas ]]></text>\n" +
                "        <details><![CDATA[ Vilnius, Lithuania ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"20\">\n" +
                "        <start_date><![CDATA[2009-05-30 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-30 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ O2 Dome ]]></text>\n" +
                "        <details><![CDATA[ London, ENG ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"21\">\n" +
                "        <start_date><![CDATA[2009-05-31 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-05-31 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Evenemententerrein Megaland ]]></text>\n" +
                "        <details><![CDATA[ Landgraaf, NL ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"22\">\n" +
                "        <start_date><![CDATA[2009-06-02 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-02 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ HSH Nordbank Arena (formerly AOL Arena) ]]></text>\n" +
                "        <details><![CDATA[ Hamburg, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"23\">\n" +
                "        <start_date><![CDATA[2009-06-04 11:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-04 14:00:00]]></end_date>\n" +
                "        <text><![CDATA[ LTU Arena ]]></text>\n" +
                "        <details><![CDATA[ Dusseldorf, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"24\">\n" +
                "        <start_date><![CDATA[2009-06-05 12:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-05 15:00:00]]></end_date>\n" +
                "        <text><![CDATA[ LTU Arena ]]></text>\n" +
                "        <details><![CDATA[ Dusseldorf, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"25\">\n" +
                "        <start_date><![CDATA[2009-06-07 20:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-07 23:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Zentralstadion - Leipzig ]]></text>\n" +
                "        <details><![CDATA[ Leipzig, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"26\">\n" +
                "        <start_date><![CDATA[2009-06-08 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-08 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Zentralstadion - Leipzig ]]></text>\n" +
                "        <details><![CDATA[ Leipzig, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"27\">\n" +
                "        <start_date><![CDATA[2009-06-10 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-10 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Olympiastadion - Berlin ]]></text>\n" +
                "        <details><![CDATA[ Berlin, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"28\">\n" +
                "        <start_date><![CDATA[2009-06-12 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-12 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Commerz Bank Arena ]]></text>\n" +
                "        <details><![CDATA[ Frankfurt, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"29\">\n" +
                "        <start_date><![CDATA[2009-06-13 11:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-13 14:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Olympic Stadium - Munich ]]></text>\n" +
                "        <details><![CDATA[ Munich, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"30\">\n" +
                "        <start_date><![CDATA[2009-06-16 19:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-16 22:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Stadio Olimpico ]]></text>\n" +
                "        <details><![CDATA[ Rome, Italy ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"31\">\n" +
                "        <start_date><![CDATA[2009-06-18 20:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-18 23:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Comunale Giuseppe Meazza - San Siro ]]></text>\n" +
                "        <details><![CDATA[ Milan, Italy ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"32\">\n" +
                "        <start_date><![CDATA[2009-06-22 19:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-22 22:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Inter Stadion Slovakia ]]></text>\n" +
                "        <details><![CDATA[ Bratislava, Slovakia ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"33\">\n" +
                "        <start_date><![CDATA[2009-06-23 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-23 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Puskas Ferenc Stadium ]]></text>\n" +
                "        <details><![CDATA[ Budapest, Hungary ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"34\">\n" +
                "        <start_date><![CDATA[2009-06-25 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-25 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Slavia Stadion ]]></text>\n" +
                "        <details><![CDATA[ Prague, Czech Republic ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"35\">\n" +
                "        <start_date><![CDATA[2009-06-27 19:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-27 22:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Stade de France - Paris ]]></text>\n" +
                "        <details><![CDATA[ Paris, FRA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"36\">\n" +
                "        <start_date><![CDATA[2009-06-30 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-06-30 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Parken Stadium ]]></text>\n" +
                "        <details><![CDATA[ Copenhagen, DK ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"37\">\n" +
                "        <start_date><![CDATA[2009-07-02 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-02 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Koengen ]]></text>\n" +
                "        <details><![CDATA[ Bergen, Norway ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"38\">\n" +
                "        <start_date><![CDATA[2009-07-03 11:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-03 14:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Folkets Park ]]></text>\n" +
                "        <details><![CDATA[ Malmo, SE ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"39\">\n" +
                "        <start_date><![CDATA[2009-07-08 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-08 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Estadio Jose Zorila ]]></text>\n" +
                "        <details><![CDATA[ Valladolid, Spain ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"40\">\n" +
                "        <start_date><![CDATA[2009-07-11 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-11 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Bessa Stadium ]]></text>\n" +
                "        <details><![CDATA[ Porto, Portugal ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"41\">\n" +
                "        <start_date><![CDATA[2009-07-12 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-12 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Estadio Olimpico - Seville ]]></text>\n" +
                "        <details><![CDATA[ Seville, Spain ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"42\">\n" +
                "        <start_date><![CDATA[2009-07-24 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-24 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Molson Amphitheatre ]]></text>\n" +
                "        <details><![CDATA[ Toronto, ON ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"43\">\n" +
                "        <start_date><![CDATA[2009-07-25 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-25 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Bell Centre ]]></text>\n" +
                "        <details><![CDATA[ Montreal, QC ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"44\">\n" +
                "        <start_date><![CDATA[2009-07-28 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-28 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Nissan Pavilion ]]></text>\n" +
                "        <details><![CDATA[ Bristow, VA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"45\">\n" +
                "        <start_date><![CDATA[2009-07-31 12:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-07-31 15:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Comcast Center - MA (formerly Tweeter Center) ]]></text>\n" +
                "        <details><![CDATA[ Mansfield, MA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"46\">\n" +
                "        <start_date><![CDATA[2009-08-01 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-01 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Borgata Hotel Casino & Spa ]]></text>\n" +
                "        <details><![CDATA[ Atlantic City, NJ ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"47\">\n" +
                "        <start_date><![CDATA[2009-08-03 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-03 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Madison Square Garden ]]></text>\n" +
                "        <details><![CDATA[ New York, NY ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"48\">\n" +
                "        <start_date><![CDATA[2009-08-04 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-04 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Madison Square Garden ]]></text>\n" +
                "        <details><![CDATA[ New York, NY ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"49\">\n" +
                "        <start_date><![CDATA[2009-08-10 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-10 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Key Arena ]]></text>\n" +
                "        <details><![CDATA[ Seattle, WA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"50\">\n" +
                "        <start_date><![CDATA[2009-08-12 11:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-12 14:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Shoreline Amphitheatre ]]></text>\n" +
                "        <details><![CDATA[ Mountain View, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"51\">\n" +
                "        <start_date><![CDATA[2009-08-14 19:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-14 22:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Cricket Wireless Amphitheatre ]]></text>\n" +
                "        <details><![CDATA[ Chula Vista, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"52\">\n" +
                "        <start_date><![CDATA[2009-08-16 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-16 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Hollywood Bowl ]]></text>\n" +
                "        <details><![CDATA[ Los Angeles, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"53\">\n" +
                "        <start_date><![CDATA[2009-08-17 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-17 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Hollywood Bowl ]]></text>\n" +
                "        <details><![CDATA[ Los Angeles, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"54\">\n" +
                "        <start_date><![CDATA[2009-08-19 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-19 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Honda Center ]]></text>\n" +
                "        <details><![CDATA[ Anaheim, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"55\">\n" +
                "        <start_date><![CDATA[2009-08-20 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-20 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Santa Barbara Bowl ]]></text>\n" +
                "        <details><![CDATA[ Santa Barbara, CA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"56\">\n" +
                "        <start_date><![CDATA[2009-08-22 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-22 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Palms Casino-the Pearl ]]></text>\n" +
                "        <details><![CDATA[ Las Vegas, NV ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"57\">\n" +
                "        <start_date><![CDATA[2009-08-23 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-23 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ US Airways Center ]]></text>\n" +
                "        <details><![CDATA[ Phoenix, AZ ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"58\">\n" +
                "        <start_date><![CDATA[2009-08-25 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-25 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ E Center ]]></text>\n" +
                "        <details><![CDATA[ West Valley City, UT ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"59\">\n" +
                "        <start_date><![CDATA[2009-08-27 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-27 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Red Rocks Amphitheatre ]]></text>\n" +
                "        <details><![CDATA[ Morrison, CO ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"60\">\n" +
                "        <start_date><![CDATA[2009-08-29 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-29 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Superpages.com Center ]]></text>\n" +
                "        <details><![CDATA[ Dallas, TX ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"61\">\n" +
                "        <start_date><![CDATA[2009-08-30 18:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-08-30 21:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Cynthia Woods Mitchell Pavilion ]]></text>\n" +
                "        <details><![CDATA[ Houston, TX ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"62\">\n" +
                "        <start_date><![CDATA[2009-09-01 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-09-01 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Lakewood Amphitheatre ]]></text>\n" +
                "        <details><![CDATA[ Atlanta, GA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"63\">\n" +
                "        <start_date><![CDATA[2009-09-04 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-09-04 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Ford Amphitheatre at the Florida State Fairgrounds ]]></text>\n" +
                "        <details><![CDATA[ Tampa Bay, FL ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"64\">\n" +
                "        <start_date><![CDATA[2009-09-05 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-09-05 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ BankAtlantic Center ]]></text>\n" +
                "        <details><![CDATA[ Sunrise, FL ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"65\">\n" +
                "        <start_date><![CDATA[2009-10-31 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-10-31 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Konig Pilsener Arena ]]></text>\n" +
                "        <details><![CDATA[ Oberhausen, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"66\">\n" +
                "        <start_date><![CDATA[2009-11-01 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-01 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Awd Dome ]]></text>\n" +
                "        <details><![CDATA[ Bremen, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"67\">\n" +
                "        <start_date><![CDATA[2009-11-03 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-03 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ TUI Arena (formerly Preussag Arena) ]]></text>\n" +
                "        <details><![CDATA[ Hanover, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"68\">\n" +
                "        <start_date><![CDATA[2009-11-07 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-07 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ SAP Arena ]]></text>\n" +
                "        <details><![CDATA[ Mannheim, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"69\">\n" +
                "        <start_date><![CDATA[2009-11-08 12:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-08 15:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Schleyerhalle ]]></text>\n" +
                "        <details><![CDATA[ Stuttgart, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"70\">\n" +
                "        <start_date><![CDATA[2009-11-10 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-10 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Stade De Geneve ]]></text>\n" +
                "        <details><![CDATA[ Geneva, CH ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"71\">\n" +
                "        <start_date><![CDATA[2009-11-12 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-12 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Recinto Ferial - Valencia ]]></text>\n" +
                "        <details><![CDATA[ Valencia, Spain ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"72\">\n" +
                "        <start_date><![CDATA[2009-11-20 12:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-20 15:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Palau Sant Jordi ]]></text>\n" +
                "        <details><![CDATA[ Barcelona, Spain ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"73\">\n" +
                "        <start_date><![CDATA[2009-11-23 20:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-11-23 23:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Halle Tony Garnier ]]></text>\n" +
                "        <details><![CDATA[ Lyon, FRA ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"74\">\n" +
                "        <start_date><![CDATA[2009-12-01 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-01 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Arena Nurnberg ]]></text>\n" +
                "        <details><![CDATA[ Nuremberg, GER ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"75\">\n" +
                "        <start_date><![CDATA[2009-12-03 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-03 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Stadthalle ]]></text>\n" +
                "        <details><![CDATA[ Vienna, Austria ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"76\">\n" +
                "        <start_date><![CDATA[2009-12-04 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-04 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Stadthalle Graz ]]></text>\n" +
                "        <details><![CDATA[ Graz, AT ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"77\">\n" +
                "        <start_date><![CDATA[2009-12-06 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-06 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Hallenstadion ]]></text>\n" +
                "        <details><![CDATA[ Zurich, CH ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"78\">\n" +
                "        <start_date><![CDATA[2009-12-07 10:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-07 13:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Hallenstadion ]]></text>\n" +
                "        <details><![CDATA[ Zurich, CH ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"79\">\n" +
                "        <start_date><![CDATA[2009-12-10 17:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-10 20:00:00]]></end_date>\n" +
                "        <text><![CDATA[ The O2 - Dublin ]]></text>\n" +
                "        <details><![CDATA[ Dublin, IE ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"80\">\n" +
                "        <start_date><![CDATA[2009-12-12 14:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-12 17:00:00]]></end_date>\n" +
                "        <text><![CDATA[ Scottish Exhibition & Conference Center ]]></text>\n" +
                "        <details><![CDATA[ Glasgow, Scotland ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"81\">\n" +
                "        <start_date><![CDATA[2009-12-13 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-13 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ LG Arena ]]></text>\n" +
                "        <details><![CDATA[ Birmingham, ENG ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"82\">\n" +
                "        <start_date><![CDATA[2009-12-15 13:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-15 16:00:00]]></end_date>\n" +
                "        <text><![CDATA[ O2 Dome ]]></text>\n" +
                "        <details><![CDATA[ London, ENG ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"83\">\n" +
                "        <start_date><![CDATA[2009-12-16 15:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-16 18:00:00]]></end_date>\n" +
                "        <text><![CDATA[ O2 Dome ]]></text>\n" +
                "        <details><![CDATA[ London, ENG ]]></details>\n" +
                "    </event>\n" +
                "    <event id=\"84\">\n" +
                "        <start_date><![CDATA[2009-12-18 16:00:00]]></start_date>\n" +
                "        <end_date><![CDATA[2009-12-18 19:00:00]]></end_date>\n" +
                "        <text><![CDATA[ MEN Arena Manchester ]]></text>\n" +
                "        <details><![CDATA[ Manchester, ENG ]]></details>\n" +
                "    </event>\n" +
                "</data>";

        return s;
    }

    public Site getMyCity() {
        String cityId = (String) getSession().get(Constants.CITY_KEY);
        if (cityId != null)
            return SiteService.getSite(cityId);
        else
            return SiteService.getRoot();
    }
}
