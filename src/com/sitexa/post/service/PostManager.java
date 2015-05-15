package com.sitexa.post.service;

import com.sitexa.farm.data.Farm;
import com.sitexa.farm.data.Land;
import com.sitexa.farm.service.FarmService;
import com.sitexa.farm.service.LandService;
import com.sitexa.post.PostCategory;
import com.sitexa.post.data.*;

/**
 * User: xnpeng
 * Date: 2010-1-9
 * Time: 15:22:32
 */
public class PostManager {

    public static void main(String[] args) {
        PostCategory cate = PostCategory.valueOf("LANDPOST");
        System.out.println("cate.name() = " + cate.name());
    }

    public static void createPost(Post post, PostType type, String... param) {

        PostCategory cate = PostCategory.valueOf(type.getObjectId().toUpperCase());

        switch (cate) {
            case ACTIVITY:
                throw new RuntimeException("PostCategory not implemented!");
            case AFFAIR:
                Affair aff = new Affair(null, type, post);
                AffairService as = new AffairService();
                as.create(aff);
                break;
            case ANNOUNCE:
                throw new RuntimeException("PostCategory not implemented!");
            case BUSINESS:
                BusinessService bs = new BusinessService();
                Business bus = new Business(null, type, post);
                bs.create(bus);
                break;
            case CASE:
                throw new RuntimeException("PostCategory not implemented!");
            case COMPANY:
                throw new RuntimeException("PostCategory not implemented!");
            case CSA:
                CsaService cs = new CsaService();
                Csa csa = new Csa(null, type, post);
                cs.create(csa);
                break;
            case EMPLOY:
                throw new RuntimeException("PostCategory not implemented!");
            case EXCHANGE:
                throw new RuntimeException("PostCategory not implemented!");
            case FARMPOST:
                if (param.length < 0) break;
                Farm farm = FarmService.getById(param[0]);
                FarmPostService fs = new FarmPostService();
                FarmPost fp = new FarmPost(post, farm, type);
                fs.create(fp);
                break;
            case FRIEND:
                throw new RuntimeException("PostCategory not implemented!");
            case HOUSE:
                break;
            case LANDPOST:
                if (param.length < 0) break;
                Land land = LandService.getById(param[0]);
                LandPostService ls = new LandPostService();
                LandPost lp = new LandPost(post, land, type);
                ls.create(lp);
                break;
            case MESSAGE:
                Message msg = new Message(null, type, post);
                MessageService ms = new MessageService();
                ms.create(msg);
                break;
            case POLL:
                break;
            case SCENERY:
                throw new RuntimeException("PostCategory not implemented!");
            case WELFARE:
                break;
            default:
                throw new RuntimeException("PostCategory not implemented!");
        }
    }

}
