package com.sitexa.service;

import com.sitexa.site.data.Site;
import com.sitexa.site.data.SiteType;
import com.sitexa.site.service.SiteService;
import com.sitexa.user.data.Member;

/**
 * User: xnpeng
 * Date: 2009-6-16
 * Time: 16:17:22
 */
public class RightService {

    public static boolean checkRight(Member profile, Site site) {
        try {
            if (profile == null) {
                return false;
            }
            if (profile.getSite() == null) {
                return false;
            }

            Site home = SiteService.getSite(profile.getSite().getSiteId());
            if (home == null) {
                return false;
            }

            if (site == null) {
                return false;
            }

            SiteType homeType = home.getType();
            String htId = homeType.getTypeId();
            int hType = Integer.parseInt(htId);

            //如果是省级以上会员,true;
            if ("1".equals(htId) || "2".equals(htId)) {
                return true;
            }

            SiteType siteType = site.getType();
            String stId = siteType.getTypeId();
            int sType = Integer.parseInt(stId);

            //如果是市级会员, 则对低级别社区有权限
            if (hType < 4 && hType < sType) {
                return true;
            }

            //如果是社区管理员,有权限
            Member governor = site.getGovernor();
            if (governor != null && governor.getMemberId().equals(profile.getMemberId())) {
                return true;
            }

            //如果是上一级,上二级,上三级社区的管理员,则有权限.
            Site pSite = site.getParent();
            if (pSite != null) {
                Member pGovernor = pSite.getGovernor();
                if (pGovernor != null && pGovernor.getMemberId().equals(profile.getMemberId())) {
                    return true;
                }
                Site ppSite = pSite.getParent();
                if (ppSite != null) {
                    Member ppGovernor = ppSite.getGovernor();
                    if (ppGovernor != null && ppGovernor.getMemberId().equals(profile.getMemberId())) {
                        return true;
                    }
                    Site pppSite = ppSite.getParent();
                    if (pppSite != null) {
                        Member pppGovernor = pppSite.getGovernor();
                        if (pppGovernor != null && pppGovernor.getMemberId().equals(profile.getMemberId())) {
                            return true;
                        }
                    }
                }
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
